<%-- 
    Document   : header.jsp
    Created on : Jul 22, 2025, 12:19:05 PM
    Author     : User
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
    <center><h1>Header
        <%
        Object username = session.getAttribute("username");
        if (username != null)
        {
            out.print(" Hello " + (String)username);
        } else {
            out.print(" Not logged in");
        }
        %>
        </h1></center>
    </body>
</html>
