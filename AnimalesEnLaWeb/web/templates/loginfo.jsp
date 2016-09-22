<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<%
    String user = (String) session.getAttribute("user");
    if ((user != null) && !user.isEmpty()) {
%>
        <li><a href="#">Bienvenido <%= user %>!</a></li>
        <li><a href="<%= request.getContextPath() %>/autenticar?comando=logout">LogOut</a></li>
<%
    }else{
%>
        <li><a href="<%= request.getContextPath() %>/login.jsp">Login</a></li>
<%
}
%>
