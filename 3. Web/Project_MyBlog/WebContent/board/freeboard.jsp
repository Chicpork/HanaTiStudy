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
	String searchType = null;
	String searchInput = null;
	searchType = request.getParameter("searchType");
	searchInput = request.getParameter("searchInput");

	// 전체 게시글 개수 가져오기
	int totalArticles = dao.countArticles(searchType, searchInput);
	
	int maxPageRowNum = 5; // 한 페이지 게시글 최대 개수(기본 5개)
	int maxArticleColNum = 10; // 한 페이지 게시판 목록 최대 개수(기본 10개)
	String tempNum = request.getParameter("maxArticleColNum");
	if (tempNum != null) {
		maxArticleColNum = Integer.parseInt(tempNum);
	}
	
	int totalPageNum = (int) Math.ceil((double)totalArticles/maxArticleColNum);	// 게시판 전체 목록 개수
	
	String pageNum = request.getParameter("pageNum");
	if (pageNum == null || Integer.parseInt(pageNum) <= 0) {
		pageNum = "1";
	}
	int pageNumInt = Integer.parseInt(pageNum);
	
	// 한 페이지 게시판 목록 시작과 마지막 값 계산
	int startPageRowNum = maxPageRowNum*((pageNumInt-1)/maxPageRowNum) + 1;
	int lastPageRowNum =  maxPageRowNum*((pageNumInt-1)/maxPageRowNum + 1) > totalPageNum ? totalPageNum : maxPageRowNum*((pageNumInt-1)/maxPageRowNum + 1);
	
	
	// 페이지 목록 얻어오기
	List<Board> lists = dao.listByPage(pageNum, String.valueOf(maxArticleColNum), searchType, searchInput);
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


	<%-- ****** 메인 바디 작성 시작 ****** --%>
	<div class="free-board">
		<div class="search-board">
			<form>
				<select name="searchType">
					<option name="subject" value="subject">제목</option>
					<option name="writer" value="writer">작성자</option>
				</select>
				<input type="search" name="searchInput" class="search-anything" placeholder="search" title="2글자 이상 입력하셔야 합니다.">
				<button><i class="fa fa-search" aria-hidden="true"></i></button>
			</form>
			<span class="page-row-num">
				<span>게시글 개수</span>
				<select id="articleColNum">
				<%
				for(int i=5;i<=20;i += 5) {
					if(i != maxArticleColNum) {
				%>
					<option><%=i%></option>
				<%
					} else {
				%>
					<option selected><%=i%></option>
				<%
					}
				}
				%>
				</select>
			</span>
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
					for (Board board : lists) {
				%>
				<tr class="article">
					<td style="display: none;">
						<%=board.getArticleId()%>
					</td>
					<td>1</td>
					<td>
						<%
					if(board.getLevelNo() != 0) {
						for (int i=0; i < board.getLevelNo(); i++) {
					%>&emsp;&emsp;
						<%
						}
					%>&#8618;
						<%
					}
					%>
						<%=board.getSubject()%>
					</td>
					<td>
						<%=board.getWriter()%>
					</td>
					<td>
						<%=board.getRegdate()%>
					</td>
					<td>
						<%String[] ips = board.getIp().split("\\.");%>
						<%=ips[0] + "." + ips[1]%>.xxx.xxx</td>
					<td>
						<%=board.getHitcount()%>
					</td>
				</tr>
				<%
					}
				%>
			</tbody>
		</table>
		<div class="board-bottom">
			<span>총 페이지 수 :
				<%=totalArticles%>
			</span>
			<div class="bottom-buttons">
				<a href="/"><input type="button" value="홈으로"></a> <a href="/board/newpost.jsp"><input type="button" value="글 쓰기"></a>
			</div>
			<div class="page-number">
				<%
			if (startPageRowNum != 1) {
			%>
				<form action="" method="post">
					<input type="hidden" name="pageNum" value="<%=startPageRowNum-1%>">
					<input type="hidden" name="maxArticleColNum" value="<%=maxArticleColNum%>">
					<button><i class="fa fa-angle-double-left nav-link"></i></button>
				</form>
				<%
			}
			if (pageNumInt != 1) {
			%>
				<form action="" method="post">
					<input type="hidden" name="pageNum" value="<%=pageNumInt-1%>">
					<input type="hidden" name="maxArticleColNum" value="<%=maxArticleColNum%>">
					<button><i class="fa fa-angle-left nav-link"></i></button>
				</form>
				<%
			}
			for (int i=startPageRowNum ; i<=lastPageRowNum ; i++) {
				if (i != pageNumInt) {
			%>
				<form action="" method="post">
					<input type="hidden" name="pageNum" value="<%=i%>">
					<input type="hidden" name="maxArticleColNum" value="<%=maxArticleColNum%>">
					<button><span class="nav-link"><%=i%></span></button>
				</form>
				<%
				} else {
			%>
				<form action="" method="post">
					<input type="hidden" name="pageNum" value="<%=i%>">
					<input type="hidden" name="maxArticleColNum" value="<%=maxArticleColNum%>">
					<button><span class="nav-link active"><%=i%></span></button>
				</form>
				<%		
				}
			}
			%>
				<%
			if (pageNumInt != totalPageNum) {
			%>
				<form action="" method="post">
					<input type="hidden" name="pageNum" value="<%=pageNumInt+1%>">
					<input type="hidden" name="maxArticleColNum" value="<%=maxArticleColNum%>">
					<button><i class="fa fa-angle-right nav-link"></i></button>
				</form>
				<%
			}
			if (lastPageRowNum != totalPageNum) {
			%>
				<form action="" method="post">
					<input type="hidden" name="pageNum" value="<%=lastPageRowNum+1%>">
					<input type="hidden" name="maxArticleColNum" value="<%=maxArticleColNum%>">
					<button><i class="fa fa-angle-double-right nav-link"></i></button>
				</form>
				<%
			}
			%>
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

	<script src="/js/mycommon.js"></script>

	<script src="/js/board.js"></script>
</body>