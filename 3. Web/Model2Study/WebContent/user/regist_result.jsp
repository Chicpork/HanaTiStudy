<%@ page contentType="text/html; charset=utf-8"%>
<jsp:useBean id="user" class="kr.or.kosta.jsp.dao.User" scope="request"/>
<!DOCTYPE html>
<html>
<head>
<title>회원가입 결과 화면</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
</head>
<body class="w3-light-grey">

  <!-- Page content -->
  <div class="w3-content" style="max-width: 500px;">
    <div
      class="w3-container w3-padding-32 w3-black w3-opacity w3-card-2 w3-hover-opacity-off"
      style="margin: 32px 0;">
      <h2>쌩유! 좋은 시간 보내~~~</h2>
      <h4>가입하신 정보는 아래와 같습니다.</h4>
      <p>아이디 : <%=user.getId()%></p>
      <p>이름 : <%=user.getName()%></p>
      <p>이메일 : <%=user.getEmail()%></p>
      <button type="button" class="w3-button w3-red w3-margin-top" onclick="location.href = '<%=application.getContextPath()%>/'">홈으로</button>
    </div>
   </div>
</body>
</html>