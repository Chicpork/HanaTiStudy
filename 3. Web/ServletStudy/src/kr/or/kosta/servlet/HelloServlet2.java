package kr.or.kosta.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class HelloServlet2
 */
public class HelloServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");
		// 웹클라이언트로 동적 출력하고자 하는 데이터 생성
		Calendar now = Calendar.getInstance();
		String nowString = String.format("%1$tF %1$tT", now);

		// 출력하기 위한 클래스 로드
		PrintWriter out = response.getWriter();
		String html = "";

		html += "<html>";
		html += "	<head>";
		html += "		<title>Servlet Programming</title>";
		html += "		<meta charset=\"utf-8\">";
		html += "	</head>";
		html += "	<body>";
		html += "		<h2>오늘은 " + nowString + " 입니다. </h2>";
		html += "		<h2>username : " + request.getSession().getAttribute("userName") + " </h2>";
		String cookieValue = null;
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				String cookieName = cookie.getName();
				if (cookieName.equals("loginId")) {
					cookieValue = cookie.getValue();
				}
			}
		}
		cookieValue = URLDecoder.decode(cookieValue, "utf-8");
		html += "		<h2>username : " + cookieValue + " </h2>";
		html += "	</body>";
		html += "</html>";

		out.println(html);
	}

}
