<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="rightcolumn">
  <div class="card">
    <c:choose>
      <c:when test="${empty cookie.userId}">
        <div>
          <form
            action="${pageContext.request.contextPath}/user/loginaction.mall"
            method="post">
            <c:choose>
              <c:when test="${isLogin == false}">
                <input type="text" id="userid" name="id" placeholder="Identifier..." required autofocus>
              </c:when>
              <c:otherwise>
                <input type="text" id="userid" name="id" placeholder="Identifier..." required>
              </c:otherwise>
            </c:choose>
            <input type="password" id="userpw" name="passwd" placeholder="Password..." required>
            <input type="submit" value="Login">
          </form>
        </div>
      </c:when>
      <c:otherwise>
        <div>
          <form
            action="${pageContext.request.contextPath}/user/logoutaction.mall"
            method="post">
            <div>${cookie.userId.value}님이입장하셧습니다.</div>
            <div>안녕</div>
            <input type="hidden" name="logout" value="logout"> <input
              type="submit" value="Logout">
          </form>
        </div>
      </c:otherwise>
    </c:choose>
  </div>

  <div class="card">
    <h3>Popular Post</h3>
    <div class="fakeimg">
      <p>Image</p>
    </div>
    <div class="fakeimg">
      <p>Image</p>
    </div>
    <div class="fakeimg">
      <p>Image</p>
    </div>
  </div>
  <div class="card">
    <h3>Follow Me</h3>
    <p>Some text..</p>
  </div>
</div>