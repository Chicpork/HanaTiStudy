<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title></title>
</head>
<body>
<h2>임시 회원 리스트 화면입니다..</h2>
<table border="1">
    <tr>
      <th>번호</th>
      <th>아이디</th>
      <th>이름</th>
      <th>비밀번호</th>
      <th>이메일</th>
      <th>가입일자</th>
    </tr>
    <c:choose>
      <c:when test="${not empty list}">
        <c:forEach var="user" items="${list}" varStatus="status">
          <tr>
            <td>${status.count}</td>
            <td>${user.id}</td>
            <td>${user.name}</td>
            <td>${user.passwd}</td>
            <td>${user.email}</td>
            <td>${user.regdate}</td>
          </tr>
        </c:forEach>
      </c:when>
      <c:otherwise>
        <tr>
          <td colspan="5">회원이 존재하지 않습니다.</td>
        </tr>
      </c:otherwise>
    </c:choose>
  </table>

</body>
</html>