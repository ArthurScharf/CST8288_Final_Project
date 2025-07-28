<%-- 
    Document   : Register
    Created on : Jul 28, 2025, 6:58:38 PM
    Author     : m
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registration</title>
    </head>
    <body>
        <form action="RegisterController" method="post">
            <label for="firstName">First Name:</label>
            <input type="text" id="firstName" name="firstName" required><br>

            <label for="lastName">Last Name:</label>
            <input type="text" id="lastName" name="lastName" required><br>

            <label for="username">Username:</label>
            <input type="text" id="username" name="username" required><br>

            <label for="password">Password:</label>
            <input type="password" id="password" name="password" required><br>
            
            <label for="role">Role:</label>
            <input type="text" id="role" name="role" required><br>

            <input type="submit" value="Register">
        </form>
    </body>
</html>
