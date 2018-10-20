<%@page import="kr.or.kosta.blog.article.dao.ArticleDao"%>
<%@page import="kr.or.kosta.blog.common.dao.DaoFactory"%>
<%@page import="kr.or.kosta.blog.article.domain.Article"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/user/getUserCookie.jsp"%>
<%
	request.setCharacterEncoding("utf-8");
	if (userId == null) {
		response.sendRedirect("/user/loginfail.jsp");
		return;
	}
	String articleId = null;
    articleId = request.getParameter("articleId");
    System.out.println(articleId);
    if (articleId == null) {
        response.sendRedirect("/");
        return;
    }
    DaoFactory factory = (DaoFactory) application.getAttribute("factory");
    ArticleDao dao = factory.getArticleDao();
%>
<!DOCTYPE html>
<html lang="ko">

<head>
<meta charset="UTF-8">
<meta name="description" content="">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
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
	<%
	String srcURIBack = request.getParameter("srcURI");
	System.out.println(srcURIBack);
	String dbWriter = dao.getWriter(articleId);
	if (!userId.equals(dbWriter)) {
        response.sendRedirect(srcURIBack);
        System.out.println("1");
		return;
	}
	String passwd = request.getParameter("passwd");
	if (!dao.certify(articleId, userId, passwd)) {
	%>
	<div class="wrong-page">
		<form action="<%=srcURIBack%>" method="post">
			<span>잘못된 비밀번호를 입력하셨습니다.</span>
			<input type="submit" value="뒤로가기">
			<input type="hidden" name="articleId" value="<%=articleId%>">
		</form>
	</div>
	<%
	} else {
	Article article = dao.read(articleId);
	%>
	<div class="new-post">
		<form action="/board/updateArticle.jsp" method="post">
			<div class="upper">
				<div class="title">
					<span>제목</span> <input type="text" name="subject"
						value="<%=article.getSubject()%>">
				</div>
				<div>
					<span>작성자</span> <input type="text" name="writer"
						value="<%=userId%>" disabled readonly>
				</div>
				<div class="passwd">
					<span>비밀번호</span> <input type="password" name="passwd">
				</div>
			</div>
			<div class="main">
				<textarea cols="1" rows="1" name="content"><%=article.getContent()%></textarea>
			</div>
			<div class="bottom">
				<input type="submit" value="올리기">
                <a href="/board/freeboard.jsp"><input type="button" value="취소"></a>
                <input type="hidden" name="articleId" value="<%=articleId%>">
                <input type="hidden" name="srcURI" value="<%=srcURIBack%>">
			</div>
		</form>
	</div>
	<%
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