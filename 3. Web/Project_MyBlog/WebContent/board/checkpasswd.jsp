<%@page import="kr.or.kosta.blog.article.domain.Article"%>
<%@page import="kr.or.kosta.blog.article.dao.ArticleDao"%>
<%@page import="kr.or.kosta.blog.common.dao.DaoFactory"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%
    request.setCharacterEncoding("utf-8");
	String pageNum = request.getParameter("pageNum");
%>

<!DOCTYPE html>
<html lang="ko" class="background-gray">

<head>
	<meta charset="UTF-8">
	<meta name="description" content="">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<!-- The above 4 meta tags *must* come first in the head; any other head content must come *after* these tags -->

<%
	String articleId = null;
	articleId = request.getParameter("articleId");
	if(articleId == null) {
%>
		<script>window.history.back();</script>
<%
		return;
	}
%>
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
	<%-- <jsp:include page="/include/top_header_area.jsp" /> --%>
	<%@ include file="/include/top_header_area.jsp"%>
	<!-- ****** Top Header Area End ****** -->

	<!-- ****** Header Area Start ****** -->
	<%-- <jsp:include page="/include/header_area.jsp" /> --%>
	<%@ include file="/include/header_area.jsp"%>
	<!-- ****** Header Area End ****** -->

<%
	if (userId == null) {
		request.setAttribute("loginMessage", "로그인이 필요합니다.");
		response.sendRedirect("/user/loginfail.jsp");
		return;
	}
%>

	<%-- ****** 메인 바디 작성 시작 ****** --%>
	<%
	DaoFactory factory = (DaoFactory) application.getAttribute("factory");
	ArticleDao dao = factory.getArticleDao();

	String dbWriter = dao.getWriter(articleId);
	String editType = request.getParameter("type");
	String searchType = request.getParameter("searchType");
	String searchInput = request.getParameter("searchInput");
	if (searchType == null || searchInput == null) {
	if(editType.equals("updateArticle")) {
		if (!userId.equals(dbWriter)) {
			
    %>
	<div class="info-page">
		<form action="/board/post.jsp" method="post">
			<span>자신의 게시글만 수정할 수 있습니다.</span>
			<input type="submit" value="뒤로가기" class="button-my">
			<input type="hidden" name="articleId" value="<%=articleId%>">
			<input type="hidden" name="pageNum" value="<%=pageNum%>">
		</form>
	</div>
	<%			
		} else {
		Article article = dao.read(articleId);
	%>
	<div class="check-password">
		<form action="/board/editArticle.jsp" method="post">
			<span>비밀번호를 입력하세요.</span>
			<input type="password" name="passwd">
			<input type="submit" value="확인" class="button-my">
			<input type="hidden" name="articleId" value="<%=articleId%>">
			<input type="hidden" name="pageNum" value="<%=pageNum%>">
		</form>
	</div>
	<%
		}
	} else {
		if (!userId.equals(dbWriter)) {
	%>
	<div class="info-page">
		<form action="/board/post.jsp" method="post">
			<span>자신의 게시글만 삭제할 수 있습니다.</span>
			<input type="submit" value="뒤로가기" class="button-my">
			<input type="hidden" name="articleId" value="<%=articleId%>">
			<input type="hidden" name="pageNum" value="<%=pageNum%>">
		</form>
	</div>
	<%
		} else {
	%>
	<div class="check-password">
		<form action="/board/deleteArticle.jsp" method="post">
			<span>비밀번호를 입력하세요.</span>
			<input type="password" name="passwd">
			<input type="submit" value="확인" class="button-my">
			<input type="hidden" name="articleId" value="<%=articleId%>">
			<input type="hidden" name="pageNum" value="<%=pageNum%>">
		</form>
	</div>
	<%
		}
	}
} else {
	if(editType.equals("updateArticle")) {
		if (!userId.equals(dbWriter)) {
    %>
	<div class="info-page">
	<form action="/board/post.jsp?searchType=<%=searchType%>&searchInput=<%=searchInput%>" method="post">
			<span>자신의 게시글만 수정할 수 있습니다.</span>
			<input type="submit" value="뒤로가기" class="button-my">
			<input type="hidden" name="articleId" value="<%=articleId%>">
			<input type="hidden" name="pageNum" value="<%=pageNum%>">
		</form>
	</div>
	<%			
		} else {
		Article article = dao.read(articleId);
	%>
	<div class="check-password">
		<form action="/board/editArticle.jsp?searchType=<%=searchType%>&searchInput=<%=searchInput%>" method="post">
			<span>비밀번호를 입력하세요.</span>
			<input type="password" name="passwd">
			<input type="submit" value="확인" class="button-my">
			<input type="hidden" name="articleId" value="<%=articleId%>">
			<input type="hidden" name="pageNum" value="<%=pageNum%>">
		</form>
	</div>
	<%
		}
	} else {
		if (!userId.equals(dbWriter)) {
	%>
	<div class="info-page">
		<form action="/board/post.jsp?searchType=<%=searchType%>&searchInput=<%=searchInput%>" method="post">
			<span>자신의 게시글만 삭제할 수 있습니다.</span>
			<input type="submit" value="뒤로가기" class="button-my">
			<input type="hidden" name="articleId" value="<%=articleId%>">
			<input type="hidden" name="pageNum" value="<%=pageNum%>">
		</form>
	</div>
	<%
		} else {
	%>
	<div class="check-password">
		<form action="/board/deleteArticle.jsp?searchType=<%=searchType%>&searchInput=<%=searchInput%>" method="post">
			<span>비밀번호를 입력하세요.</span>
			<input type="password" name="passwd">
			<input type="submit" value="확인" class="button-my">
			<input type="hidden" name="articleId" value="<%=articleId%>">
			<input type="hidden" name="pageNum" value="<%=pageNum%>">
		</form>
	</div>
	<%
		}
	}
	}
	%>
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