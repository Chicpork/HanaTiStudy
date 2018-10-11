package kr.or.kosta.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 서블릿 생명주기 테스트를 위한 서블릿
 */
public class LifecycleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	int count;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LifecycleServlet() {
		System.out.println("LifecycleServlet() Called...");
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		System.out.println("init(ServletConfig config) Called...");
		count = 0;
		super.init(config);
	}
	
	@Override
	public void init() throws ServletException {
		System.out.println("init() Called...");
	}

	@Override
	public void destroy() {
		System.out.println("destroy() Called...");
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		count++;
		System.out.println("service() Called...");
		super.service(req, resp);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("doGet() Called..");
		System.out.println(request);
		System.out.println(response);
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		String html = "";

		html += "<html>";
		html += "	<head>";
		html += "		<title>Servlet 카운터</title>";
		html += "		<meta charset=\"utf-8\">";
		html += "	</head>";
		html += "	<body>";
		html += "		<h2>당신은 " + count + "번째 방문자입니다. </h2>";
		html += "	</body>";
		html += "</html>";

		out.println(html);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("doPost() Called...");
	}

}
