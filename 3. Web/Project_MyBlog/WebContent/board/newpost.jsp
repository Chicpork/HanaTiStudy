<%@page import="kr.or.kosta.blog.article.domain.Article"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/user/getUserCookie.jsp"%>
<%
	request.setCharacterEncoding("utf-8");
	if (userId == null) {
		response.sendRedirect("/user/loginfail.jsp");
	}
	String groupNo = null;
	String levelNo = null;
	String orderNo = null;
	groupNo = request.getParameter("groupNo");
	if (groupNo != null) {
		levelNo = request.getParameter("levelNo");
		orderNo = request.getParameter("orderNo");
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

	<!-- Background Pattern Swither -->
	<div id="pattern-switcher">Bg Pattern</div>
	<div id="patter-close">
		<i class="fa fa-times" aria-hidden="true"></i>
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
	<div class="new-post">
		<form action="/board/uploadArticle.jsp" method="post">
			<div class="upper">
				<div class="title">
					<span>제목</span> <input type="text" name="subject">
				</div>
				<div>
					<span>작성자</span> <input type="text" name="writer" value="<%=userId%>" disabled>
				</div>
				<div class="passwd">
					<span>비밀번호</span> <input type="password" name="passwd">
				</div>
			</div>
			<div class="main">
				<textarea cols="1" rows="1" name="content"></textarea>
			</div>
			<div class="bottom">
				<input type="submit" value="올리기">
				<a href="/board/freeboard.jsp"><input type="button" value="취소"></a>
			</div>
			<div class="hidden" style="display: hidden;">
				<input type="hidden" name="groupNo" value="<%=groupNo%>">
				<input type="hidden" name="levelNo" value="<%=levelNo%>">
				<input type="hidden" name="orderNo" value="<%=orderNo%>">
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