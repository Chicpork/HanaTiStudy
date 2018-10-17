package kr.or.kosta.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Index extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
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
		request.setCharacterEncoding("utf-8");

		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		IndexHTML indexHtml = IndexHTML.getInstance();
		String html = null;

		Cookie[] cookies = request.getCookies();
		Cookie cookieId = null;
		Cookie cookiePw = null;
		Cookie cookieCount = null;
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
		if (cookieId == null || cookiePw == null) {
			html = indexHtml.loginHTML();
			out.println(html);
			return;
		}

		for (Cookie cookie : cookies) {
			if (cookie.getName().equals("Count&" + cookieId.getValue())) {
				cookieCount = cookie;
			}
		}

		if (cookieCount == null) {
			cookieCount = new Cookie("Count&" + cookieId.getValue(), "0");
		}

		increaseCount(cookieCount);
		response.addCookie(cookieCount);

		html = indexHtml.logoutHTML(cookieId.getValue(), cookieCount.getValue());
		out.println(html);
	}

	private void increaseCount(Cookie cookie) {
		cookie.setValue(String.valueOf(Integer.parseInt(cookie.getValue()) + 1));
		cookie.setMaxAge(60 * 60 * 24);
	}

}
