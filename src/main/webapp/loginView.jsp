<%-- 
    Document   : login
    Created on : Jul 22, 2025, 4:44:25 PM
    Author     : Arthur Scharf
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <jsp:include page="/WEB-INF/views/header.jsp" /> 
    <body>
        <form action="LoginController" method="POST">
            <label for="username">Username:</label><br>
            <input type="text" id="username" name="username" required><br><br>

            <label for="password">Password:</label><br>
            <input type="password" id="password" name="password" required><br><br>

            <button type="submit">Login</button>
            <button type="reset">Reset</button>
        </form>
    </body>
</html>
