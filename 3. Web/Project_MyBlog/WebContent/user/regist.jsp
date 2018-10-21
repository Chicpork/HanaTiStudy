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
  <div class="logo_area text-center">
    <label class="yummy-logo">R e g i s t</label>
  </div>

  <%-- ****** 메인 바디 작성 시작 ****** --%>
  <div class="user-info">
    <form class="user-info-form" action="/user/regist_action.jsp"  method="post" id="registForm">
      <dl>
        <dt>아이디</dt>
        <dd>
          <input type="text" name="id" id="id" placeholder="아이디" title="3 ~ 10자 길이의 영문자, 숫자로 이루어진 아이디를 사용해주세요">
          <iframe src="/user/checkDupleId.jsp" class="check-box" id="checkId" scrolling="no"></iframe>
        </dd>
      </dl>
      <dl>
        <dt>비밀번호</dt>
        <dd>
            <input type="password" name="passwd" id="passwd" placeholder="비밀번호" title="8자 이상의 영문자, 숫자, 특수문자로 이루어진 비밀번호를 사용해주세요">
            <label class="check-box">비밀번호를 입력해주세요</label>
          </dd>
      </dl>
      <dl>
        <dt>비밀번호 확인</dt>
        <dd>
            <input type="password" name="passwd2" placeholder="비밀번호 확인">
            <label class="check-box">비밀번호를 입력해주세요</label>
          </dd>
      </dl>
      <dl>
        <dt>이름</dt>
        <dd>
            <input type="text" name="name" placeholder="이름" title="영문자, 한글, 숫자만 사용가능합니다.">
            <label class="check-box">이름을 입력해주세요</label>
          </dd>
      </dl>
      <dl>
        <dt>이메일</dt>
        <dd>
            <input type="text" name="email" id="email" placeholder="이메일" title="이메일 형식으로 입력해주세요!">
            <iframe src="/user/checkDupleEmail.jsp" class="check-box" id="checkEmail" scrolling="no"></iframe>
          </dd>
      </dl>
      <div class="user-submit">
        <input type="button" value="가입" id="registMe">
        <a href="/"><input type="button" value="취소"></a>
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

  <script src="/js/regist.js"></script>

  <script src="/js/validator.js"></script>
</body>