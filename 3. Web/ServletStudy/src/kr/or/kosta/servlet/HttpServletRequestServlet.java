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
public class HttpServletRequestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");
		response.getWriter().append("Served at: ").append(request.getContextPath());

		// 출력하기 위한 클래스 로드
		PrintWriter out = response.getWriter();

		String clientId = request.getRemoteAddr();
		String method = request.getMethod();
		String uri = request.getRequestURI();
		String protocol = request.getProtocol();
		String query = request.getQueryString();
		String userName = request.getParameter("name");
		String applicationName = request.getContextPath();
		String PathName = request.getServletPath();

		Enumeration<String> headerNames = request.getHeaderNames();

		String html = "";
		html += "<html>";
		html += "	<head>";
		html += "		<title>Servlet Programming</title>";
		html += "		<meta charset=\"utf-8\">";
		html += "	</head>";
		html += "	<body>";
		html += "	<ul>";
		html += "		<li>" + clientId + "</li>";
		html += "		<li>" + method + "</li>";
		html += "		<li>" + uri + "</li>";
		html += "		<li>" + protocol + "</li>";

		while (headerNames.hasMoreElements()) {
			String name = headerNames.nextElement();
			String value = request.getHeader(name);
			html += "		<li>" + name + " : " + value + "</li>";
		}

		html += "		<li>" + query + "</li>";
		html += "		<li>" + userName + "</li>";
		html += "		<li>" + applicationName + "</li>";
		html += "		<li>" + PathName + "</li>";
		html += "	</ul>";
		html += "	</body>";
		html += "</html>";

		out.println(html);
	}

}
