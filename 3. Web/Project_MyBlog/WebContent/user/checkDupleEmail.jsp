<%@page import="kr.or.kosta.blog.common.Validator"%>
<%@page import="kr.or.kosta.blog.user.dao.UserDao"%>
<%@page import="kr.or.kosta.blog.common.dao.DaoFactory"%>
<%@ page contentType="text/html; charset=utf-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
</head>
<body style="margin:0px;">
<%
request.setCharacterEncoding("utf-8");
DaoFactory factory = (DaoFactory) application.getAttribute("factory");
UserDao dao = factory.getUserDao();
String email = request.getParameter("email");
pageContext.setAttribute("checkParam", "false");
if (email == null) {
} else if (!Validator.isValidEmail(email)) {
%>
    <label style="color:red;">이메일 형식에 맞춰 입력해주세요</label>
    <script>
        parent.document.getElementById('checkEmail').style.display = 'block'
        parent.document.getElementById('email').focus();
    </script>
<%
} else if(email.length() == 0) {
%>
    <label style="color:red;">이메일을 입력해주세요</label>
    <script>
        parent.document.getElementById('checkEmail').style.display = 'block'
        parent.document.getElementById('email').focus();
    </script>
<%
} else if (dao.isExistEmail(email)) {
%>
    <label style="color:red;">중복된 이메일이 존재합니다.</label>
    <script>
        parent.document.getElementById('checkEmail').style.display = 'block'
        parent.document.getElementById('email').focus();
    </script>
<%
} else {
    pageContext.setAttribute("checkParam", "true");
%>
    <label style="color:mediumblue;">감사해요!</label>
    <script>
        parent.document.getElementById('checkEmail').style.display = 'block';
        setTimeout(() => {
            parent.document.getElementById('checkEmail').style.display = 'none'
        }, 5000);
    </script>
<%
}
%>
    <script>
    Object.defineProperty(window, 'getCheckParam', { 
        writable: false,
        value: '${checkParam}'
    });
    </script>
</body>