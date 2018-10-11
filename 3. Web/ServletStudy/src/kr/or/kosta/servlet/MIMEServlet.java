package kr.or.kosta.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 마임 타입 이해를 위한 서블릿
 */
public class MIMEServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/plain; charset=utf-8");
		// Content-Type:text/plain; charset=utf-8
		PrintWriter out = response.getWriter();
		out.println("야 이거 아무렇게나 적어도 돼???");
		out.println("야 이거 아무렇게나 적어도 돼");
		out.println("일반 플레인 텍스트야");
		
	}

}
