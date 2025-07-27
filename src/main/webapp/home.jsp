<%-- 
    Document   : home
    Created on : Jul 27, 2025, 4:59:29 PM
    Author     : User
--%>


<%
Object role = session.getAttribute("role");
%>
        



<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" type="text/css" href="http://localhost:8080/CST8288_Final_Project/css/styles.css"/>
    </head>
    <jsp:include page="/WEB-INF/views/header.jsp" /> 
    <body>
        <p>Hello World<p>
    </body>
</html>
