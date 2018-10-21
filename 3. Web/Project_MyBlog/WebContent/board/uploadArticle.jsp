<%@page import="kr.or.kosta.blog.article.dao.ArticleDao"%>
<%@page import="kr.or.kosta.blog.common.dao.DaoFactory"%>
<%@page import="kr.or.kosta.blog.article.domain.Article"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/user/getUserCookie.jsp"%>
<%
	request.setCharacterEncoding("utf-8");
	if (userId == null) {
		response.sendRedirect("/user/loginfail.jsp");
	}
	DaoFactory factory = (DaoFactory)application.getAttribute("factory");
	ArticleDao dao = factory.getArticleDao();
	Article article = new Article();
	String groupNo = null;
	String levelNo = null;
	String orderNo = null;
	groupNo = request.getParameter("groupNo");
	if (groupNo != null && !groupNo.equals("null")) {
		levelNo = request.getParameter("levelNo");
		orderNo = request.getParameter("orderNo");
		orderNo = dao.buildOrderNo(groupNo, levelNo, orderNo);
		levelNo = String.valueOf((Integer.parseInt(levelNo) + 1));
		article.setGroupNo(groupNo);
		article.setLevelNo(levelNo);
		article.setOrderNo(orderNo);
	} else {
		article.setGroupNo(null);
	}
	article.setWriter(userId);
	article.setSubject(request.getParameter("subject"));
	article.setContent(request.getParameter("content"));

	String ip = request.getHeader("X-Forwarded-For");
	if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
		ip = request.getHeader("Proxy-Client-IP");
	}
	if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
		ip = request.getHeader("WL-Proxy-Client-IP");
	}
	if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
		ip = request.getHeader("HTTP_CLIENT_IP");
	}
	if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
		ip = request.getHeader("HTTP_X_FORWARDED_FOR");
	}
	if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
		ip = request.getHeader("X-Real-IP");
	}
	if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
		ip = request.getHeader("X-RealIP");
	}
	if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
		ip = request.getRemoteAddr();
	}

	article.setIp(ip);
	article.setPasswd(request.getParameter("passwd"));
	
	dao.create(article);
	response.sendRedirect("/board/freeboard.jsp");
%>