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

public class LoginServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String userId = request.getParameter("userid");
		String userpw = request.getParameter("userpw");

		if (userId.trim().length() == 0 || userpw.trim().length() == 0) {
			request.getRequestDispatcher("index.html").forward(request, response);
			return;
		}

		Cookie cookieId = new Cookie("userId", userId);
		Cookie cookiePw = new Cookie("userpw", userpw);
		
		response.addCookie(cookieId);
		response.addCookie(cookiePw);
		
		request.getRequestDispatcher("index2.html").forward(request, response);
	}
}
