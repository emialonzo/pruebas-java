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
        <link href="<%= request.getContextPath()%>/bower_components/bootstrap/dist/css/bootstrap.css"  rel="stylesheet" type="text/css" />
        <link href="<%= request.getContextPath()%>/bower_components/bootstrap/dist/css/bootstrap-theme.css"  rel="stylesheet" type="text/css" />
    </head>
    <body>

        <h1>Hello World!</h1>
        
        <ol>
            <%
                List<Persona> list = (List<Persona>) request.getAttribute("personas");
            %>

            <%
                for (Persona p : list) {
            %>
            <li><%= p.toString()%> -- ${p}
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

        <script src="<%= request.getContextPath()%>/bower_components/jquery/dist/jquery.js" type="text/javascript" ></script>
        <script src="<%= request.getContextPath()%>/bower_components/bootstrap/dist/js/bootstrap.js" type="text/javascript" ></script>
    </body>
</html>
