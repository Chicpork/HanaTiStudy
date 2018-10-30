<%@ page contentType="text/html; charset=utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<link rel="stylesheet" type="text/css" href="<%=application.getContextPath()%>/css/basic.css">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
</head>
<body>

  <div class="header">
    <h1>My Website</h1>
    <p>Resize the browser window to see the effect.</p>
  </div>

  <%-- 탑메뉴 시작 --%>
  <jsp:include page="/include/topnav.jsp" />
  <%-- 탑메뉴 종료 --%>

  <div class="row">
    <div class="leftcolumn">
      <div class="w3-content card">
        <div class="w3-container" id="contact">
          <h2>회원가입</h2>
          <p>※ 좋은 것 서비스 받을려면 회원가입혀~~^^</p>
          <form action="${pageContext.request.contextPath}/user/regist_action.mall" method="post">
            <p>
              <input class="w3-input w3-padding-16 w3-border"
                type="text" placeholder="아이디" required name="id">
            </p>
            <p>
              <input class="w3-input w3-padding-16 w3-border"
                type="text" placeholder="이름" required name="name">
            </p>
            <p>
              <input class="w3-input w3-padding-16 w3-border"
                type="password" placeholder="비밀번호" required
                name="passwd">
            </p>
            <p>
              <input class="w3-input w3-padding-16 w3-border"
                type="text" placeholder="이메일" required name="email">
            </p>
            <p>
              <button class="w3-button w3-red w3-padding-large"
                type="submit">가입하기</button>
              <button class="w3-button w3-blue w3-padding-large"
                type="reset">취소하기</button>
            </p>
          </form>
        </div>

      </div>
    </div>

    <%-- 사이드메뉴 시작 --%>
    <jsp:include page="/include/aside.jsp" />
    <%-- 사이드메뉴 종료 --%>

  </div>

  <%-- footer 시작 --%>
  <jsp:include page="/include/footer.jsp" />
  <%-- footer 종료 --%>

</body>
</html>
