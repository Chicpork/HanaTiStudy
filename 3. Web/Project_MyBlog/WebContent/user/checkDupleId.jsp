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
System.out.println(id);
System.out.println(dao.read(id));
if (id == null) {
} else if(id.length() == 0) {
%>
    <label style="color:red;">아이디를 입력해주세요</label>
    <script>
        parent.document.getElementById('checkId').style.display = 'grid'
        parent.document.getElementById('id').focus();
    </script>
<%
} else if (dao.read(id) != null) {
%>
    <label style="color:red;">중복된 아이디가 존재합니다.</label>
    <script>
        parent.document.getElementById('checkId').style.display = 'grid'
        parent.document.getElementById('id').focus();
    </script>
<%
} else {
%>
    <label style="color:mediumblue;">좋은 아이디예요!</label>
    <script>
    parent.document.getElementById('checkId').style.display = 'grid';
    setTimeout(() => {
        parent.document.getElementById('checkId').style.display = 'none'
    }, 50000);
    </script>
<%
}
%>
</body>