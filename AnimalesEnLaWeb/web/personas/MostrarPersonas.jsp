<%-- 
    Document   : MostrarPersonas
    Created on : 19/09/2016, 07:15:42 PM
    Author     : emi
--%>

<%@page import="java.util.List"%>
<%@page import="entidades.Persona"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <jsp:include page="/templates/csss.jsp" />
    </head>
    <body>
        <jsp:include page="/templates/header.jsp" />

        <h1>Hello World!</h1>

        <ol>
            <%
                List<Persona> list = (List<Persona>) request.getAttribute("personas");
            %>

            <%
                for (Persona p : list) {
            %>
            <li><a href="<%= request.getContextPath()%>/persona?comando=verPerfil&idPersona=<%= p.getId()%>"><%= p.toString()%></a> 
            </li>
            <%
                }
            %>
        </ol>

        <ol>
            <c:forEach var="persona" items="${personas}">
                <li> - ${persona.toString()}</li>
                </c:forEach>
        </ol>

        <jsp:include page="/templates/scripts.jsp" />
    </body>
</html>
