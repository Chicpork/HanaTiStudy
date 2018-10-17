package kr.or.kosta.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServletContextServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String message = "서블릿간의 데이터 공유입니다..";
		
		
		ServletContext context = getServletContext();
		
		System.out.println(context.getServerInfo());
		System.out.println(context.getContextPath());
	
		context.setAttribute("message", message);
		response.sendRedirect(context.getContextPath()+"/hello.do");
		
		String location = context.getInitParameter("Location");
		System.out.println(location);
	}
}
