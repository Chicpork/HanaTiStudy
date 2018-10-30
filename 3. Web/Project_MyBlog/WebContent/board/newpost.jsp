<%@page import="kr.or.kosta.blog.article.domain.Article"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%
	request.setCharacterEncoding("utf-8");
	String groupNo = null;
	String levelNo = null;
	String orderNo = null;
	groupNo = request.getParameter("groupNo");
	if (groupNo != null) {
		levelNo = request.getParameter("levelNo");
		orderNo = request.getParameter("orderNo");
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
<title>Jiwon Blog - New Article</title>

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
	<div class="new-post">
		<form action="/board/uploadArticle.jsp" method="post">
			<div class="my-border">
				<div class="upper">
					<div>
					<dl>
						<dt>제목</dt>
						<dd><input type="text" name="subject" required autofocus maxlength="400"></dd>
					</dl>
					<dl>
						<dt>비밀번호</dt>
						<dd><input type="password" name="passwd" required maxlength="8"></dd>
					</dl>
					</div>
					<div>
					<dl>
						<dt>작성자</dt>
						<dd><input type="text" name="writer" value="<%=userId%>" disabled readonly></dd>
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
						<dd><textarea cols="1" rows="1" name="content" required maxlength="4000"></textarea></dd>
					</dl>
				</div>
			</div>
			<div class="bottom">
				<input type="submit" value="올리기" class="button-my">
				<%
			    String searchType = request.getParameter("searchType");
			    String searchInput = request.getParameter("searchInput");
			    String pageNum = request.getParameter("pageNum");
				String articleId = null;
				articleId = request.getParameter("articleId");
				if (articleId == null) {
				%>
				<a href="/board/freeboard.jsp"><input type="button" value="취소" class="button-my"></a>
				<%
				} else if (pageNum == null) {
				%>
				<a href="/board/post.jsp?articleId=<%=articleId%>"><input type="button" value="취소" class="button-my"></a>
				<%
				} else if (searchType == null || searchInput == null) {
				%>
				<a href="/board/post.jsp?articleId=<%=articleId%>&pageNum=<%=pageNum%>"><input type="button" value="취소" class="button-my"></a>
				<%	
				} else {
				%>
				<a href="/board/post.jsp?articleId=<%=articleId%>&pageNum=<%=pageNum%>&searchType=<%=searchType%>&searchInput=<%=searchInput%>"><input type="button" value="취소" class="button-my"></a>
				<%	
				}
				%>
			</div>
			<div class="hidden" style="display: hidden;">
				<input type="hidden" name="groupNo" value="<%=groupNo%>">
				<input type="hidden" name="levelNo" value="<%=levelNo%>">
				<input type="hidden" name="orderNo" value="<%=orderNo%>">
			<%
			if (pageNum != null) {
			%>
				<input type="hidden" name="pageNum" value="<%=pageNum%>">
			<%
			}
			if (searchType != null && searchInput != null) {
			%>
				<input type="hidden" name="searchType" value="<%=searchType%>">
				<input type="hidden" name="searchInput" value="<%=searchInput%>">
			<%
			}
			%>
			</div>
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