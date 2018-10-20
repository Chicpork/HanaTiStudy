<%@page import="kr.or.kosta.blog.guestbook.domain.GuestBook"%>
<%@page import="java.util.List"%>
<%@page import="kr.or.kosta.blog.common.dao.JdbcDaoFactory"%>
<%@page import="kr.or.kosta.blog.common.dao.DaoFactory"%>
<%@page import="kr.or.kosta.blog.guestbook.dao.GuestBookDao"%>
<%@ page contentType="text/html; charset=utf-8"%>

<%
request.setCharacterEncoding("utf-8");
DaoFactory factory = (DaoFactory)application.getAttribute("factory");
GuestBookDao dao = factory.getGuestBookDao();
List<GuestBook> lists = null;
if(request.getParameter("putMessage") != null && request.getParameter("putMessage").equals("putMessage")){
  %>
  <%@ include file="/user/getUserCookie.jsp"%>
  <%
	String content = request.getParameter("message");
  if (content != null) {
	  dao.create(new GuestBook(userId, content, null));
  }
}
lists = dao.listAll();
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
  <%-- <jsp:include page="/include/top_header_area.jsp" /> --%>
  <%@ include file="/include/top_header_area.jsp"%>
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
        <textarea cols="1" rows="1" name="message" required></textarea>
        <button>등록</button>
        <input type="hidden" value="putMessage" name="putMessage">
      </form>
    <%
    } else {
    %>
      <form action="" method="post">
        <textarea cols="1" rows="1" name="message" disabled>로그인 시만 이용할 수 있습니다.</textarea>
        <button disabled style="cursor:default;">등록</button>
        <input type="hidden" value="putMessage" name="putMessage">
      </form>
    <%
    }
    %>
    </div>
    <table>
      <thead>
        <tr>
          <th>작성자</th>
          <th>내용</th>
          <th>작성일</th>
        </tr>
      </thead>
      <tbody>
      <%
		if(lists != null) {
			for(GuestBook guestBook : lists) {
      %>
        <tr>
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