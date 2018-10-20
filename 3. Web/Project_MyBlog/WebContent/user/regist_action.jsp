<%@page import="kr.or.kosta.blog.common.dao.JdbcDaoFactory"%>
<%@page import="kr.or.kosta.blog.common.dao.DaoFactory"%>
<%@page import="kr.or.kosta.blog.user.dao.UserDao"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%request.setCharacterEncoding("utf-8"); %>
<jsp:useBean id="user" class="kr.or.kosta.blog.user.domain.User"/>
<jsp:setProperty property="*" name="user"/>

<%
DaoFactory factory = (DaoFactory)application.getAttribute("factory");
UserDao dao = factory.getUserDao();
dao.create(user);
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

  <script src="/js/login.js"></script>
</head>

<body>
  <!-- Preloader Start -->
  <div id="preloader">
    <div class="yummy-load"></div>
  </div>

  <!-- Background Pattern Swither -->
  <div id="pattern-switcher">
    Bg Pattern
  </div>
  <div id="patter-close">
    <i class="fa fa-times" aria-hidden="true"></i>
  </div>

  <!-- ****** Top Header Area Start ****** -->
  <jsp:include page="/include/top_header_area.jsp" />
  <%-- <%@ include file="/include/top_header_area.jsp"%> --%>
  <!-- ****** Top Header Area End ****** -->
  <h2></h2>
  <div class="logo_area text-center">
    <label class="yummy-logo">R e g i s t</label>
  </div>

  <%-- ****** 메인 바디 작성 시작 ****** --%>
  <div class="user-info">
    <p>회원 가입이 완료되었습니다.</p>
    <p>가입하신 회원 정보는 아래와 같습니다.</p>
    <p>아이디 : <%=user.getId()%></p>
    <p>아이디 : <%=user.getName()%></p>
    <p>아이디 : <%=user.getEmail()%></p>
    <p>아래 홈으로 버튼을 누르면 홈으로 이동합니다.</p>
    <a href="/"><input type="button" value="홈으로"></a>
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