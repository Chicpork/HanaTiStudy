<%@page import="kr.or.kosta.blog.article.dao.ArticleDao"%>
<%@page import="kr.or.kosta.blog.common.dao.DaoFactory"%>
<%@page import="kr.or.kosta.blog.article.domain.Article"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/user/getUserCookie.jsp"%>
<%
	request.setCharacterEncoding("utf-8");
	String srcURIBack = request.getParameter("srcURI");
	if (userId == null) {
		response.sendRedirect("/user/loginfail.jsp");
	}
	DaoFactory factory = (DaoFactory)application.getAttribute("factory");
	ArticleDao dao = factory.getArticleDao();
	String articleId = request.getParameter("articleId");
	if(articleId == null) {
        response.sendRedirect("board/freeboard.jsp");
        return;
    }
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

	String subject = request.getParameter("subject");
	String writer = request.getParameter("writer");
	String passwd = request.getParameter("passwd");
	String content = request.getParameter("content");
	dao.update(articleId, subject, passwd, ip, content);
	response.sendRedirect(srcURIBack);
%>
<form action="<%=srcURIBack%>" method="post">
	<input type="hidden" name="articleId" value="<%=articleId%>">
</form>