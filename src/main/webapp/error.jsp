<%-- 
    Document   : error
    Created on : Jul 22, 2025, 5:00:17 PM
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
    <%
        String errorMessage = (String)application.getAttribute("errorMessage");

        if (errorMessage != null)
        {
            out.println("<p>"+errorMessage+"</p>");
        } else {
            out.println("<h1>ERROR MESSAGE NOT SET</h1>");
        }
        %>
    </body>
</html>
