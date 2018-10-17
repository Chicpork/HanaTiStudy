package kr.or.kosta.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		process(request, response);
	}

	private void process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String userId = request.getParameter("userid");
		String userPw = request.getParameter("userpw");

		if (userId == null || userPw == null) {
			if (userId.trim().isEmpty() || userPw.trim().isEmpty()) {
				response.sendRedirect("/servlet/");
				return;
			}
		}

		// UserDao를 이용한 회원 가입여부 체크
		// 지금은 무조건 연결

		Cookie cookieId = new Cookie("userId", userId);
		cookieId.setPath("/");
		Cookie cookiePw = new Cookie("userPw", userPw);
		cookiePw.setPath("/");

		response.addCookie(cookieId);
		response.addCookie(cookiePw);

		response.sendRedirect("/servlet/");
	}
}
