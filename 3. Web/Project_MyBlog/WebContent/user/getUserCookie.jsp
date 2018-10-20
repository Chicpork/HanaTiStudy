<%@ page contentType="text/html; charset=utf-8"%>
<%
Cookie[] cookies = request.getCookies();
String userId = null;
if (cookies != null) {
    for (Cookie cookie : cookies) {
        if (cookie.getName().equals("userId")) {
            userId = cookie.getValue();
            break;
        }
    }
}
%>