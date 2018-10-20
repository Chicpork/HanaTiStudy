<%@page import="kr.or.kosta.blog.article.domain.Article"%>
<%@page import="kr.or.kosta.blog.article.dao.ArticleDao"%>
<%@page import="kr.or.kosta.blog.common.dao.DaoFactory"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%
String articleId = null;
articleId = request.getParameter("articleId");
if(articleId == null || articleId.trim().isEmpty()){
	response.sendRedirect("/");
	return;
}
DaoFactory factory = (DaoFactory) application.getAttribute("factory");
ArticleDao dao = factory.getArticleDao();
dao.increaseHitCount(articleId);
Article article = dao.read(articleId);
%>
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

  <!-- ****** Top Header Area Start ****** -->
  <jsp:include page="/include/top_header_area.jsp" />
  <%-- <%@ include file="/include/top_header_area.jsp"%> --%>
  <!-- ****** Top Header Area End ****** -->

  <!-- ****** Header Area Start ****** -->
  <jsp:include page="/include/header_area.jsp" />
  <%-- <%@ include file="/include/header_area.jsp"%> --%>
  <!-- ****** Header Area End ****** -->


  <%-- ****** 메인 바디 작성 시작 ****** --%>
  <div class="post">
    <div class="my-border">
      <div class="upper">
        <dl>
          <dt>제목</dt>
          <dd><%=article.getSubject()%></dd>
        </dl>
        <div>
          <dl>
            <dt>작성자</dt>
            <dd><%=article.getWriter()%></dd>
          </dl>
          <dl>
            <dt>작성일</dt>
            <dd><%=article.getRegdate()%></dd>
          </dl>
        </div>
        <div>
          <dl>
            <dt>아이피</dt>
            <dd><%String[] ips = article.getIp().split("\\.");%><%=ips[0] + "." + ips[1]%>.xxx.xxx</dd>
          </dl>
          <dl>
            <dt>조회수</dt>
            <dd><%=article.getHitcount()%></dd>
          </dl>
        </div>
      </div>
      <div class="main">
        <dl>
          <dt>내용</dt>
          <dd><%=article.getContent()%></dd>
        </dl>
      </div>
    </div>
    <div class="bottom">
      <input type="button" value="글목록">
      
      <form action="/board/newpost.jsp" method="post">
     	<input type="submit" value="답글쓰기">
      	<input type="hidden" name="groupNo" value="<%=article.getGroupNo()%>">
        <input type="hidden" name="levelNo" value="<%=article.getLevelNo()%>">
        <input type="hidden" name="orderNo" value="<%=article.getOrderNo()%>">
      </form>
      <span>
        <form action="/board/checkpasswd.jsp" method="post">
          <input type="submit" value="글수정">
          <input type="hidden" name="type" value="updateArticle">
          <input type="hidden" name="articleId" value="<%=articleId%>">
          <input type="hidden" name="srcURI" value="<%=request.getRequestURI()%>">
        </form>
        <form action="/board/checkpasswd.jsp" method="post">
          <input type="submit" value="글삭제">
          <input type="hidden" name="type" value="deleteArticle">
          <input type="hidden" name="articleId" value="<%=articleId%>">
          <input type="hidden" name="srcURI" value="<%=request.getRequestURI()%>">
        </form>
      </span>
    </div>
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