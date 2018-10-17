package kr.or.kosta.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.Calendar;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CookieServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

//		String id = "jiwon";
		String id = "한글";
		
		id = URLEncoder.encode(id, "utf-8");
		
		Cookie cookie = new Cookie("loginId", id);
		cookie.setMaxAge(60*60*24*365*100);
//		cookie.setDomain("httpL//www.naver.com");
//		cookie.setPath("/");
		
		response.addCookie(cookie);
		
		response.sendRedirect("hello2");
	}
}
