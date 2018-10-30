<%@ page contentType="text/html; charset=utf-8"%>
<%
request.setCharacterEncoding("utf-8");
Cookie[] cookies = request.getCookies();
Cookie cId = null;
if (cookies != null) {
    for (Cookie cookie : cookies) {
        if (cookie.getName().equals("userId")) {
            cId = cookie;
            break;
        }
    }
}
cId.setPath("/");
cId.setMaxAge(0);
response.addCookie(cId);
response.sendRedirect("/");
%>
