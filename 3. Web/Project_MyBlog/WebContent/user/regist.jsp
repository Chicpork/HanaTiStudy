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

  <!-- ****** Header Area Start ****** -->
  <jsp:include page="/include/header_area.jsp" />
  <%-- <%@ include file="/include/header_area.jsp"%> --%>
  <!-- ****** Header Area End ****** -->


  <%-- ****** 메인 바디 작성 시작 ****** --%>
  <div class="user_info">
    <form>
      <dl>
        <dt>아이디</dt>
        <dd>안녕!</dd>
      </dl>
      <dl>
        <dt>비밀번호</dt>
        <dd>안녕!</dd>
      </dl>
      <dl>
        <dt>비밀번호 확인</dt>
        <dd>안녕!</dd>
      </dl>
      <dl>
        <dt>이름</dt>
        <dd>안녕!</dd>
      </dl>
      <dl>
        <dt>이메일</dt>
        <dd>안녕!</dd>
      </dl>
      <input type="submit" value="가입">
      <input type="submit" value="취소">
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