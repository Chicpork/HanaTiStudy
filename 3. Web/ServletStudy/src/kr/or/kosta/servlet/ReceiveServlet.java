package kr.or.kosta.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class HelloServlet2
 */
public class ReceiveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		process(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		process(request, response);
	}

	public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");

		// 출력하기 위한 클래스 로드
		PrintWriter out = response.getWriter();

		// 요청 파라미터 수신
		String userId = request.getParameter("userid");
		String userPw = request.getParameter("userpw");
		String teams = request.getParameter("teams");
		String[] hobbys = request.getParameterValues("hobby");

		Enumeration<String> paramNames = request.getParameterNames();
		while (paramNames.hasMoreElements()) {
			String name = paramNames.nextElement();
			String value = request.getParameter(name);
			System.out.println(name + "=" + value);
		}

		String html = "";
		html += "<html>";
		html += "	<head>";
		html += "		<title>Servlet Programming</title>";
		html += "		<meta charset=\"utf-8\">";
		html += "	</head>";
		html += "	<body style='font-size:20pt;'>";
		html += "		<h3>이름 " + userId + "</h3>";
		html += "		<h3>비번 " + userPw + "</h3>";
		html += "		<h3>팀 " + teams + "</h3>";
		if (hobbys != null) {
			for (String string : hobbys) {
				html += "		<h3>취미 " + string + "</h3>";
			}
		}
		html += "	</body>";
		html += "</html>";

		out.println(html);
	}
}
