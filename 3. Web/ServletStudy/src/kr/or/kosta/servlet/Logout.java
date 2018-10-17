package kr.or.kosta.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Logout extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		process(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		process(request, response);
	}

	private void process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Cookie[] cookies = request.getCookies();
		Cookie cookieId = null;
		Cookie cookiePw = null;
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("userId")) {
					cookieId = cookie;
				}
				if (cookie.getName().equals("userPw")) {
					cookiePw = cookie;
				}
			}
		}
		cookieId.setPath("/");
		cookieId.setMaxAge(0);
		cookiePw.setPath("/");
		cookiePw.setMaxAge(0);
		response.addCookie(cookieId);
		response.addCookie(cookiePw);
		response.sendRedirect("/servlet/");
	}
}
