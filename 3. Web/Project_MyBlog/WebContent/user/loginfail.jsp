<%@ page contentType="text/html; charset=utf-8"%>
<!DOCTYPE html>
<html lang="ko" class="background-gray">

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
  <script>
    function saveIdCookie2() {
      if (document.getElementById('saveId2').checked) {
        setCookie('saveId', document.getElementById('userId2').value, 30);
      } else {
        setCookie('saveId', '', -1);
      }
    }
  </script>
  <%
  String saveId = null;
  if (cookies != null) {
    for (Cookie cookie : cookies) {
      if (cookie.getName().equals("saveId")) {
        saveId = cookie.getValue();
        break;
      }
    }
  }
  System.out.println(request.getAttribute("loginMessage"));
  String loginMessage = (String)request.getAttribute("loginMessage");
  if (loginMessage == null) {
    if (saveId == null) {
    %>
    <div class="login-fail">
      <div class="message">
        아이디와 비밀번호를 확인하고<br>다시 로그인 해주세요!
      </div>
      <form action="/user/login_action.jsp" method="post">
        <input type="text" name="id" id="userId2" placeholder="Your id">
        <input type="password" name="passwd" id="userPw2" placeholder="Password">
        <input type="submit" id="login-submit2" value="Login" class="button-my" onclick="saveIdCookie2()">
        <input type="checkbox" value="saveId" id="saveId2">
        <label>Save ID</label>
      </form>
    </div>
    <%
    } else { 
    %>
    <div class="login-fail">
        <div class="message">
          아이디와 비밀번호를 확인하고<br>다시 로그인 해주세요!
        </div>
        <form action="/user/login_action.jsp" method="post">
          <input type="text" name="id" id="userId2" placeholder="Your id" value="<%=saveId%>">
          <input type="password" name="passwd" id="userPw2" placeholder="Password">
          <input type="submit" id="login-submit2" value="Login" class="button-my" onclick="saveIdCookie2()">
          <input type="checkbox" value="saveId" id="saveId2" checked>
          <label>Save ID</label>
        </form>
      </div>
    <%
    }
  } else {
    if (saveId == null) {
    %>
    <div class="login-fail">
      <div class="message">
        <%=loginMessage%>
      </div>
      <form action="/user/login_action.jsp" method="post">
        <input type="text" name="id" id="userId2" placeholder="Your id">
        <input type="password" name="passwd" id="userPw2" placeholder="Password">
        <input type="submit" id="login-submit2" value="Login" class="button-my" onclick="saveIdCookie2()">
        <input type="checkbox" value="saveId" id="saveId2">
        <label>Save ID</label>
      </form>
    </div>
    <%
    } else { 
    %>
    <div class="login-fail">
        <div class="message">
          <%=loginMessage%>
        </div>
        <form action="/user/login_action.jsp" method="post">
          <input type="text" name="id" id="userId2" placeholder="Your id" value="<%=saveId%>">
          <input type="password" name="passwd" id="userPw2" placeholder="Password">
          <input type="submit" id="login-submit2" value="Login" class="button-my" onclick="saveIdCookie2()">
          <input type="checkbox" value="saveId" id="saveId2" checked>
          <label>Save ID</label>
        </form>
      </div>
  <%
    }
  }
  %>
  <script>
  	document.getElementById('userId2').focus();
  </script>
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