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
String id = request.getParameter("id");
pageContext.setAttribute("checkParam", "false");
if (id == null) {
} else if(!Validator.isValidId(id)) {
%>
    <label style="color:red;">아이디 형식을 맞춰주세요</label>
    <script>
        parent.document.getElementById('checkId').style.display = 'block'
        parent.document.getElementById('id').focus();
    </script>
<%    
} else if(id.length() == 0) {
%>
    <label style="color:red;">아이디를 입력해주세요</label>
    <script>
        parent.document.getElementById('checkId').style.display = 'block'
        parent.document.getElementById('id').focus();
    </script>
<%
} else if (dao.read(id) != null) {
%>
    <label style="color:red;">중복된 아이디가 존재합니다.</label>
    <script>
        parent.document.getElementById('checkId').style.display = 'block'
        parent.document.getElementById('id').focus();
    </script>
<%
} else {
    pageContext.setAttribute("checkParam", "true");
%>
    <label style="color:mediumblue;">좋은 아이디예요!</label>
    <script>
        parent.document.getElementById('checkId').style.display = 'block';
        setTimeout(() => {
            parent.document.getElementById('checkId').style.display = 'none'
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