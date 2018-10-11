package kr.or.kosta.servlet;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 마임 타입 이해를 위한 서블릿
 */
public class MIMEServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final String path = "C:\\KOSTA187\\workspace\\ServletStudy\\WebContent\\assets\\";
//	private String file = "music.mp3";
	private String file = "sample.pptx";

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		response.setContentType("audio/mpeg");
		response.setContentType("application/vnd.ms-powerpoint");

		// 바이트입력스트림 생성
	     InputStream in = new FileInputStream(path + file);
	     
	     // response가 제공하는 바이트입력스트림 취득
	     OutputStream out = response.getOutputStream();
	     byte[] buffer = new byte[1024];
	     int count = 0;
	     try{
	          while( (count = in.read(buffer)) != -1){
	               out.write(buffer, 0, count);
	          }
	     }finally{
	          if(out != null) out.close();
	          if(in != null) in.close();
	     }
		
	}

}
