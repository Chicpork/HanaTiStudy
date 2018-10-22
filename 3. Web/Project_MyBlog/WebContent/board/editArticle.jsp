<%@page import="kr.or.kosta.blog.article.dao.ArticleDao"%>
<%@page import="kr.or.kosta.blog.common.dao.DaoFactory"%>
<%@page import="kr.or.kosta.blog.article.domain.Article"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%
	request.setCharacterEncoding("utf-8");
	String pageNum = request.getParameter("pageNum");
	String articleId = null;
    articleId = request.getParameter("articleId");
    if (articleId == null) {
        response.sendRedirect("/");
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
<title>Jiwon Blog - Game Review</title>

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
		request.getRequestDispatcher("/user/loginfail.jsp").forward(request, response);
		return;
	}
%>

	<%-- ****** 메인 바디 작성 시작 ****** --%>
	<%
	String srcURIBack = request.getParameter("srcURI");
	String dbWriter = dao.getWriter(articleId);
	if (!userId.equals(dbWriter)) {
        response.sendRedirect("/board/freeboard.jsp");
		return;
	}
	String passwd = request.getParameter("passwd");
	String searchType = request.getParameter("searchType");
	String searchInput = request.getParameter("searchInput");
	if (searchType == null || searchInput == null) {
	if (!dao.certify(articleId, userId, passwd)) {
	%>
	<div class="info-page">
		<form action="/board/post.jsp" method="post">
			<span>잘못된 비밀번호를 입력하셨습니다.</span>
			<input type="submit" value="뒤로가기" class="button-my">
			<input type="hidden" name="articleId" value="<%=articleId%>">
			<input type="hidden" name="pageNum" value="<%=pageNum%>">
		</form>
	</div>
	<%
	} else {
	Article article = dao.read(articleId);
	%>
	<div class="new-post">
		<form action="/board/updateArticle.jsp" method="post">
			<div class="my-border">
				<div class="upper">
					<div>
					<dl>
						<dt>제목</dt>
						<dd><input type="text" name="subject" value="<%=article.getSubject()%>" required autofocus></dd>
					</dl>
					<dl>
						<dt>비밀번호</dt>
						<dd><input type="password" name="passwd" required></dd>
					</dl>
					</div>
					<div>
					<dl>
						<dt>작성자</dt>
						<dd><input type="text" name="writer" value="<%=userId%>" value="<%=userId%>" disabled readonly></dd>
					</dl>
					<dl>
						<dt>아이피</dt>
						<dd><div><%=ip%></div></dd>
					</dl>
					</div>
				</div>
				<div class="main">
					<dl>
						<dt>내용</dt>
						<dd><textarea cols="1" rows="1" name="content" required><%=article.getContent()%></textarea></dd>
					</dl>
					
				</div>
			</div>
			<div class="bottom">
				<input type="submit" value="올리기" class="button-my">
				<a href="/board/freeboard.jsp"><input type="button" value="취소" class="button-my"></a>
				<input type="hidden" name="articleId" value="<%=articleId%>">
				<input type="hidden" name="pageNum" value="<%=pageNum%>">
			</div>
		</form>
	</div>
	<%
	}
	} else {
		if (!dao.certify(articleId, userId, passwd)) {
	%>
	<div class="info-page">
		<form action="/board/post.jsp?searchType=<%=searchType%>&searchInput=<%=searchInput%>" method="post">
			<span>잘못된 비밀번호를 입력하셨습니다.</span>
			<input type="submit" value="뒤로가기" class="button-my">
			<input type="hidden" name="articleId" value="<%=articleId%>">
			<input type="hidden" name="pageNum" value="<%=pageNum%>">
		</form>
	</div>
	<%
	} else {
	Article article = dao.read(articleId);
	%>
	<div class="new-post">
		<form action="/board/updateArticle.jsp?searchType=<%=searchType%>&searchInput=<%=searchInput%>" method="post">
			<div class="my-border">
				<div class="upper">
					<div>
					<dl>
						<dt>제목</dt>
						<dd><input type="text" name="subject" value="<%=article.getSubject()%>" required></dd>
					</dl>
					<dl>
						<dt>비밀번호</dt>
						<dd><input type="password" name="passwd" required></dd>
					</dl>
					</div>
					<div>
					<dl>
						<dt>작성자</dt>
						<dd><input type="text" name="writer" value="<%=userId%>" value="<%=userId%>" disabled readonly></dd>
					</dl>
					<dl>
						<dt>아이피</dt>
						<dd><div><%=ip%></div></dd>
					</dl>
					</div>
				</div>
				<div class="main">
					<dl>
						<dt>내용</dt>
						<dd><textarea cols="1" rows="1" name="content" required><%=article.getContent()%></textarea></dd>
					</dl>
					
				</div>
			</div>
			<div class="bottom">
				<input type="submit" value="올리기" class="button-my">
				<a href="/board/freeboard.jsp"><input type="button" value="취소" class="button-my"></a>
				<input type="hidden" name="articleId" value="<%=articleId%>">
				<input type="hidden" name="pageNum" value="<%=pageNum%>">
			</div>
		</form>
	</div>
	<%
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