<%@page import="kr.or.kosta.blog.guestbook.domain.GuestBook"%>
<%@page import="kr.or.kosta.blog.guestbook.dao.GuestBookDao"%>
<%@page import="kr.or.kosta.blog.board.domain.Board"%>
<%@page import="java.util.List"%>
<%@page import="kr.or.kosta.blog.board.dao.BoardDao"%>
<%@page import="kr.or.kosta.blog.common.dao.DaoFactory"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%
request.setCharacterEncoding("utf-8");
DaoFactory factory = (DaoFactory) application.getAttribute("factory");
BoardDao boardDao = factory.getBoardDao();
GuestBookDao guestBookDao = factory.getGuestBookDao();
List<Board> newArticles = boardDao.newArticles(5);
List<Board> hotArticles = boardDao.hotArticles(5);
List<GuestBook> newGuestBooks = guestBookDao.newGuestBook(5);
int count = 0;
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
    <title>Jiwon Blog - Game Review</title>

    <!-- Favicon -->
    <link rel="icon" href="img/core-img/favicon.ico">

    <!-- Core Stylesheet -->
    <link href="style.css" rel="stylesheet">

    <!-- Responsive CSS -->
    <link href="css/responsive/responsive.css" rel="stylesheet">

    <script src="js/mycommon.js"></script>

    <script src="js/redirectToArticle.js"></script>
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
    <div class="index-main">
        <div class="upper">
            <div class="free-board">
                <div>최신 게시글</div>
                <table>
                    <thead>
                        <tr>
                            <th>제목</th>
                            <th>작성자</th>
                            <th>작성일</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                        if (newArticles == null || newArticles.size() == 0) {
                        %>
                        <%	
                        } else {
                            count = 0;
                            for (Board board : newArticles) {
                        %>
                        <tr class="article">
                            <td style="display: none;">
                                <%=board.getArticleId()%>
                            </td>
                            <td>
                                <%=board.getSubject()%>
                            </td>
                            <td>
                                <%=board.getWriter()%>
                            </td>
                            <td>
                                <%=board.getRegdate()%>
                            </td>
                        </tr>
                        <%
                            }
                        }
                        %>
                    </tbody>
                </table>
            </div>
            <div class="free-board">
                <div>핫 게시글</div>
                <table>
                    <thead>
                        <tr>
                            <th>제목</th>
                            <th>작성자</th>
                            <th>작성일</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                        if(hotArticles == null || hotArticles.size() == 0) {
                        %>
                        <%	
                        } else {
                            count = 0;
                            for (Board board : hotArticles) {
                        %>
                        <tr class="article">
                            <td style="display: none;">
                                <%=board.getArticleId()%>
                            </td>
                            <td>
                                <%=board.getSubject()%>
                            </td>
                            <td>
                                <%=board.getWriter()%>
                            </td>
                            <td>
                                <%=board.getRegdate()%>
                            </td>
                        </tr>
                        <%
                            }
                        }
                        %>
                    </tbody>
                </table>
            </div>
        </div>
        <div class="lower">
            <div class="free-board">
              <div>최신 방명록</div>
             	<table>
			      <thead>
			        <tr>
			          <th>작성자</th>
			          <th>내용</th>
			          <th>작성일</th>
			        </tr>
			      </thead>
			      <tbody>
			      <%
					if(newGuestBooks == null || newGuestBooks.size() == 0) {
				  %>
				  <%	
					} else {
						for(GuestBook guestBook : newGuestBooks) {
			      %>
			        <tr>
			          <td><%=guestBook.getWriter()%></td>
			          <td><%=guestBook.getContent()%></td>
			          <td><%=guestBook.getRegdate()%></td>
			        </tr>
			      <%
						}
					}
			      %>
			      </tbody>
			    </table>
            </div>
        </div>
    </div>


    <%-- ****** 메인 바디 작성 끝 ****** --%>


    <!-- ****** Footer Menu Area Start ****** -->
    <jsp:include page="/include/footer_area.jsp" />
    <!-- ****** Footer Menu Area End ****** -->

    <!-- Jquery-2.2.4 js -->
    <script src="js/jquery/jquery-2.2.4.min.js"></script>
    <!-- Popper js -->
    <script src="js/bootstrap/popper.min.js"></script>
    <!-- Bootstrap-4 js -->
    <script src="js/bootstrap/bootstrap.min.js"></script>
    <!-- All Plugins JS -->
    <script src="js/others/plugins.js"></script>
    <!-- Active JS -->
    <script src="js/active.js"></script>
    
    <script>changeColor();</script>
</body>