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
%>
<!DOCTYPE html>
<html lang="ko">

<head>
	<meta charset="UTF-8">
	<meta name="description" content="">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<!-- The above 4 meta tags *must* come first in the head; any other head content must come *after* these tags -->

	<!-- Title -->
	<title>Yummy Blog - Food Blog Template</title>

	<!-- Favicon -->
	<link rel="icon" href="/img/core-img/favicon.ico">

	<!-- Core Stylesheet -->
	<link href="/style.css" rel="stylesheet">

	<!-- Responsive CSS -->
	<link href="/css/responsive/responsive.css" rel="stylesheet">

	<script src="/js/mycommon.js"></script>
</head>

<body>
	<!-- Preloader Start -->
	<div id="preloader">
		<div class="yummy-load"></div>
	</div>

	<!-- ****** Top Header Area Start ****** -->
	<jsp:include page="/include/top_header_area.jsp" />
	<%-- <%@ include file="/include/top_header_area.jsp"%> --%>
	<!-- ****** Top Header Area End ****** -->

	<!-- ****** Header Area Start ****** -->
	<jsp:include page="/include/header_area.jsp" />
	<%-- <%@ include file="/include/header_area.jsp"%> --%>
	<!-- ****** Header Area End ****** -->


	<%-- ****** 메인 바디 작성 시작 ****** --%>
	<div class="info-page">
        <form action="<%=srcURIBack%>" method="post">
            <span>게시글 업데이트 완료!</span>
            <input type="submit" value="뒤로가기">
            <input type="hidden" name="articleId" value="<%=articleId%>">
        </form>
	</div>
	<%-- ****** 메인 바디 작성 끝 ****** --%>


	<!-- ****** Footer Menu Area Start ****** -->
	<jsp:include page="/include/footer_area.jsp" />
	<!-- ****** Footer Menu Area End ****** -->

	<!-- Jquery-2.2.4 js -->
	<script src="/js/jquery/jquery-2.2.4.min.js"></script>
	<!-- Popper js -->
	<script src="/js/bootstrap/popper.min.js"></script>
	<!-- Bootstrap-4 js -->
	<script src="/js/bootstrap/bootstrap.min.js"></script>
	<!-- All Plugins JS -->
	<script src="/js/others/plugins.js"></script>
	<!-- Active JS -->
	<script src="/js/active.js"></script>
</body>