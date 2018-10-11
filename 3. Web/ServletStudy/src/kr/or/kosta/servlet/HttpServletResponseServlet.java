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
public class HttpServletResponseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

//		response.setStatus(400);
//		response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		String name = request.getParameter("name");
		if (name != null && name.length() != 0) {
			if (name.equals("bangry")) {
				response.sendError(HttpServletResponse.SC_FORBIDDEN);
				return;
			}
		}

		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();

		String html = "";
		html += "<html>";
		html += "	<head>";
		html += "		<title>Servlet Programming</title>";
		html += "		<meta charset=\"utf-8\">";
		html += "	</head>";
		html += "	<body>";
		html += "	<ul>";
		html += "		<li> ??? </li>";
		html += "	</ul>";
		html += "	</body>";
		html += "</html>";

		out.println(html);
	}

}
