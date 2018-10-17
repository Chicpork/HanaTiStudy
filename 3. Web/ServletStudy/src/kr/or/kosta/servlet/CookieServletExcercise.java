package kr.or.kosta.servlet;

import java.io.IOException;
import java.io.PrintWriter;
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

public class CookieServletExcercise extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		Cookie cookie = null;
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookieTemp : cookies) {
				String cookieName = cookieTemp.getName();
				if (cookieName.equals("counter")) {
					cookie = cookieTemp;
					break;
				}
			}
		}
		
		int countI = 1;
		String count = null;
		if(cookie == null) {
			count = "1";
			cookie = new Cookie("counter", count);
		} else {
			count = cookie.getValue();
			countI = Integer.parseInt(count) + 1;
			count = Integer.toString(countI);
			cookie.setValue(count);
		}
		cookie.setMaxAge(60*60*24*30);
		response.addCookie(cookie);
		
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		String html = "";
		html += "<html>";
		html += "	<head>";
		html += "		<title>Servlet Programming</title>";
		html += "		<meta charset=\"utf-8\">";
		html += "	</head>";
		html += "	<body>";
		html += "		<h2> 당신의 접속 횟수 : " + count + "</h2>";
		html += "	</body>";
		html += "</html>";
		out.println(html);
	}
}
