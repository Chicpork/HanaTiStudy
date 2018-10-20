<%@page import="kr.or.kosta.blog.user.dao.UserDao"%>
<%@page import="kr.or.kosta.blog.common.dao.JdbcDaoFactory"%>
<%@page import="kr.or.kosta.blog.common.dao.DaoFactory"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%!
public void jspInit() {
	DaoFactory factory = new JdbcDaoFactory();
	getServletContext().setAttribute("factory", factory);
}
%>