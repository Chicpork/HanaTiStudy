<%@page import="kr.or.kosta.blog.common.MyPageBuilder"%>
<%@page import="kr.or.kosta.blog.guestbook.domain.GuestBook"%>
<%@page import="java.util.List"%>
<%@page import="kr.or.kosta.blog.common.dao.JdbcDaoFactory"%>
<%@page import="kr.or.kosta.blog.common.dao.DaoFactory"%>
<%@page import="kr.or.kosta.blog.guestbook.dao.GuestBookDao"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/user/getUserCookie.jsp"%>
<%
request.setCharacterEncoding("utf-8");
DaoFactory factory = (DaoFactory)application.getAttribute("factory");
GuestBookDao dao = factory.getGuestBookDao();
List<GuestBook> lists = null;
if(request.getParameter("putMessage") != null && request.getParameter("putMessage").equals("putMessage")){
	String content = request.getParameter("message");
	if (content != null) {
		dao.create(new GuestBook(userId, content, null));
	}
}

//전체 게시글 개수 가져오기
int totalGuestBooks = dao.countGuestBooks();

int maxPageRowNum = 5; // 한 페이지 게시글 최대 개수(기본 5개)
int maxArticleColNum = 10; // 한 페이지 게시판 목록 최대 개수(기본 10개)
String tempNum = request.getParameter("maxArticleColNum");
if (tempNum != null) {
	maxArticleColNum = Integer.parseInt(tempNum);
}

MyPageBuilder pageBuilder = new MyPageBuilder(totalGuestBooks, maxPageRowNum, maxArticleColNum);

String pageNum = request.getParameter("pageNum");
if (pageNum == null || Integer.parseInt(pageNum) <= 0) {
	pageNum = "1";
}
pageBuilder.build(Integer.parseInt(pageNum));

int totalPageNum = pageBuilder.getTotalPageNum(); // 게시판 전체 목록 개수
int pageNumInt = Integer.parseInt(pageNum);

// 한 페이지 게시판 목록 시작과 마지막 값 계산
int startPageRowNum = pageBuilder.getStartPageRowNum();
int lastPageRowNum = pageBuilder.getLastPageRowNum();
int guestBookNum = pageBuilder.getArticleNum();	

lists = dao.listAll(pageNum, String.valueOf(maxArticleColNum));
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
  <title>Jiwon Blog - Guest Book</title>

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
  <%-- <jsp:include page="/include/header_area.jsp" /> --%>
  <%@ include file="/include/header_area.jsp"%>
  <!-- ****** Header Area End ****** -->

  <%-- ****** 메인 바디 작성 시작 ****** --%>
  <div class="guest-board">
    <div class="guest-message">
    <%
    if (userId != null) {
    %>
      <form action="" method="post">
        <textarea cols="1" rows="1" name="message" required autofocus></textarea>
        <button>등록</button>
        <input type="hidden" value="putMessage" name="putMessage">
      </form>
    <%
    } else {
    %>
      <form>
        <textarea cols="1" rows="1" name="message" disabled>로그인 시만 이용할 수 있습니다.</textarea>
        <button disabled style="cursor:default;">등록</button>
        <input type="hidden" value="putMessage" name="putMessage">
      </form>
    <%
    }
    %>
    </div>
    <div>
      <span class="page-row-num" style="padding-right: 2px;">
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
          <th>작성자</th>
          <th>내용</th>
          <th>작성일</th>
        </tr>
      </thead>
      <tbody>
      <%
		if(lists == null || lists.size() == 0) {
    } else {
      int count = 0;
			for(GuestBook guestBook : lists) {
      %>
        <tr>
          <td><%=guestBookNum - count++%></td>
          <td><%=guestBook.getWriter()%></td>
          <td><%=guestBook.getContent()%></td>
          <td><%=guestBook.getRegdate()%></td>
        </tr>
      <%
			}
		}
      %>
      </tbody>
    </table>
    <div class="board-bottom">
			<span>총 방명록 수 :
				<%=totalGuestBooks%>
			</span>
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
			if (pageNumInt < totalPageNum) {
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

  <script src="/js/board.js"></script>
</body>