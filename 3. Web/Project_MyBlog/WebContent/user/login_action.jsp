<%@page import="kr.or.kosta.blog.common.dao.JdbcDaoFactory"%>
<%@page import="kr.or.kosta.blog.common.dao.DaoFactory"%>
<%@page import="kr.or.kosta.blog.user.domain.User"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@page import="kr.or.kosta.blog.user.dao.UserDao"%>
<%
request.setCharacterEncoding("utf-8");
DaoFactory factory = (DaoFactory)application.getAttribute("factory");
UserDao dao = factory.getUserDao();

String userId = request.getParameter("id");
String userPw = request.getParameter("passwd");

if(userId == null || userPw == null) {
    response.sendRedirect("/user/loginfail.jsp");
} else{
    User user = dao.certify(userId, userPw);
    if(user == null) {
        response.sendRedirect("/user/loginfail.jsp");
    } else {
        Cookie cId = new Cookie("userId",userId);
        cId.setPath("/");
        response.addCookie(cId);
        response.sendRedirect("/");
    }
}
%>
