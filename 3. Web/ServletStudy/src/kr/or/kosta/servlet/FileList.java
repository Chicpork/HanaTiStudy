package kr.or.kosta.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * Apache 파일 업로드 API를 이용한 파일 업로드 처리 서블릿
 */
public class FileList extends HttpServlet {

	private String fileRepository;

	@Override
	public void init() throws ServletException {
		fileRepository = getServletContext().getInitParameter("Location");
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}
	
	private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		File source = new File(fileRepository);
		File[] files = source.listFiles();
		
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		int count = 0;
		String html = null;
		
		html =  "<!DOCTYPE html>\r\n" + 
				"<html>\r\n" + 
				"<head>\r\n" + 
				"<meta charset=\"utf-8\">\r\n" + 
				"<script src=\"js/filelist.js\" charset=\"UTF-8\"></script>\r\n" + 
				"<style>\r\n" + 
				"input[type=submit], input[type=button] {\r\n" + 
				"	width: 100%;\r\n" + 
				"	background-color: #4CAF50;\r\n" + 
				"	color: white;\r\n" + 
				"	padding: 14px 20px;\r\n" + 
				"	margin: 8px 0;\r\n" + 
				"	border: none;\r\n" + 
				"	border-radius: 4px;\r\n" + 
				"	cursor: pointer;\r\n" + 
				"}\r\n" + 
				"\r\n" + 
				"table {\r\n" + 
				"	text-align: center;\r\n" + 
				"	margin: 0px auto;\r\n" + 
				"	width: 100%;\r\n" + 
				"}\r\n" + 
				"\r\n" + 
				"input[type=submit]:hover {\r\n" + 
				"	background-color: #45a049;\r\n" + 
				"}\r\n" + 
				"\r\n" + 
				"div {\r\n" + 
				"	border-radius: 5px;\r\n" + 
				"	background-color: #f2f2f2;\r\n" + 
				"	padding: 20px;\r\n" + 
				"	margin: 0px auto;\r\n" + 
				"	width: 50%;\r\n" + 
				"}\r\n" + 
				"\r\n" + 
				"h1 {\r\n" + 
				"	text-align: center;\r\n" + 
				"}\r\n" + 
				"</style>\r\n" + 
				"</head>\r\n" + 
				"<body>\r\n" + 
				"  <h1>자 료 실</h1>\r\n" + 
				"  <div>\r\n" + 
				"    <table>\r\n" + 
				"      <thead>\r\n" + 
				"        <tr>\r\n" + 
				"          <th>번호</th>\r\n" + 
				"          <th>파일명</th>\r\n" + 
				"          <th>사이즈</th>\r\n" + 
				"          <th>다운로드</th>\r\n" + 
				"        </tr>\r\n" + 
				"      </thead>\r\n" + 
				"      <tbody>\r\n";
		for (File file : files) {
			count++;
			html += "        <tr>\r\n" + 
					"          <td>"+count+"</td>\r\n" + 
					"          <td>"+file.getName()+"</td>\r\n" + 
					"          <td>"+file.length()+"</td>\r\n" + 
					"          <td><input type=\"checkbox\" name=\"file\"></td>\r\n" + 
					"        </tr>\r\n";
		}	
		html += "      </tbody>\r\n" + 
				"    </table>\r\n" + 
				"    <input type=\"submit\" value=\"다운로드\" id=\"download\">\r\n" + 
				"  </div>\r\n" + 
				"</body>\r\n" + 
				"</html>";
		
		out.println(html);
	}

}
