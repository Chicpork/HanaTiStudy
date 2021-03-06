package kr.or.kosta.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 파일 다운로드 처리 서블릿
 */
public class FileDownloadServlet extends HttpServlet {

	private String fileRepository;

	@Override
	public void init() throws ServletException {
		fileRepository = getServletContext().getInitParameter("Location");
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

	public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
//		request.setCharacterEncoding("euc-kr");
		String fileNames = request.getParameter("file");
		System.out.println(fileNames);
		if(fileNames == null) {
			response.sendRedirect("list.do");
			return;
		}
		String[] files = fileNames.split("/");
		if (files == null || files.length == 0) {
			response.sendRedirect("list.do");
			return;
		}
		String leftFile = null;
		for (int i = 1; i < files.length; i++) {
			leftFile += files[i];
		}
		request.setAttribute("file",leftFile);
		String fileName = files[0].trim();

		String filePath = fileRepository + fileName;
		File file = new File(filePath);

		// HTTP 버전별 브라우저 캐시 사용 않도록 응답헤더 설정
		String httpVersion = request.getProtocol();
		if (httpVersion.equals("HTTP/1.0")) {
			response.setDateHeader("Expires", 0);
			response.setHeader("Pragma", "no-cache");
		} else if (httpVersion.equals("HTTP/1.1")) {
			response.setHeader("Cache-Control", "no-cache");
		}

		// 파일 다운로드 처리를 위한 응답헤더에 마임타입 설정
		response.setContentType("application/octet-stream");
		fileName = URLEncoder.encode(fileName, "utf-8");
		response.setHeader("Content-Disposition", "attachment;filename=" + fileName + ";");
		response.setHeader("Content-Length", "" + file.length());
		
		FileInputStream in = new FileInputStream(file);
		OutputStream out = response.getOutputStream();
		try {
			byte[] buffer = new byte[1024];
			int count = 0;
			while ((count = in.read(buffer)) != -1) {
				out.write(buffer, 0, count);
			}
			
		} finally {
			out.flush();
			in.close();
			out.close();
		}
		
//		request.getRequestDispatcher("download.do").forward(request, response);
	}
}
