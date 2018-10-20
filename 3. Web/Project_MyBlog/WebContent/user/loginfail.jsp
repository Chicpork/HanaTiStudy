<%@ page contentType="text/html; charset=utf-8"%>
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
    <div class="login-form">
        <form action="/user/login_action.jsp" method="post">
            <input type="text" name="id" id="userId" placeholder="Your id">
            <input type="password" name="passwd" id="userPw" placeholder="Password">
            <input type="submit" id="login-submit" value="Login">
            <input type="checkbox" value="saveId">
            <label>Save ID</label>
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