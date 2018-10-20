<%@page import="kr.or.kosta.blog.board.domain.Board"%>
<%@page import="java.util.List"%>
<%@page import="kr.or.kosta.blog.board.dao.BoardDao"%>
<%@page import="kr.or.kosta.blog.common.dao.JdbcDaoFactory"%>
<%@page import="kr.or.kosta.blog.common.dao.DaoFactory"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%
	request.setCharacterEncoding("utf-8");
	DaoFactory factory = (DaoFactory) application.getAttribute("factory");
	BoardDao dao = factory.getBoardDao();
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
<script src="/js/board.js"></script>
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
	<div class="free-board">
		<div class="search-board">
			<form>
				<select>
					<option>아이디</option>
					<option>제목</option>
				</select>
				<input type="text" name="" id="search-anything" placeholder="search">
				<button><i class="fa fa-search" aria-hidden="true"></i></button>
			</form>
		</div>
		<table>
			<thead>
				<tr>
					<th>번호</th>
					<th>제목</th>
					<th>작성자</th>
					<th>작성일</th>
					<th>아이피</th>
					<th>조회수</th>
				</tr>
			</thead>
			<tbody>
				<%
					List<Board> lists = dao.listByPage("1", "15", null, null);
					for (Board board : lists) {
				%>
				<tr class="article">
					<td style="display: none;"><%=board.getArticleId()%></td>
					<td>1</td>
					<td><%
					if(board.getLevelNo() != 0) {
						for (int i=0; i < board.getLevelNo(); i++) {
					%>&emsp;&emsp;<%
						}
					%>&#8618;<%
					}
					%><%=board.getSubject()%></td>
					<td><%=board.getWriter()%></td>
					<td><%=board.getRegdate()%></td>
					<td><%String[] ips = board.getIp().split("\\.");%><%=ips[0] + "." + ips[1]%>.xxx.xxx</td>
					<td><%=board.getHitcount()%></td>
				</tr>
				<%
					}
				%>
			</tbody>
		</table>
		<div class="board-bottom">
			<div class="bottom-buttons">
				<a href="/"><input type="button" value="홈으로"></a> <a
					href="/board/newpost.jsp"><input type="button" value="글 쓰기"></a>
			</div>
			<div class="page-number">
				<a href="" class="page-button"><i
					class="fa fa-angle-double-left"></i></a> <a href="" class="page-button"><i
					class="fa fa-angle-left"></i></a> <a href="" class="page-button">1</a>
				<a href="" class="page-button">2</a> <a href="" class="page-button">3</a>
				<a href="" class="page-button">4</a> <a href="" class="page-button">5</a>
				<a href="" class="page-button">6</a> <a href="" class="page-button"><i
					class="fa fa-angle-right"></i></a> <a href="" class="page-button"><i
					class="fa fa-angle-double-right"></i></a>
			</div>
		</div>

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