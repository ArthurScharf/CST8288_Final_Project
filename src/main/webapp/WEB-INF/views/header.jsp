<%-- 
    Document   : header.jsp
    Created on : Jul 22, 2025, 12:19:05 PM
    Author     : Arthur Scharf
--%>



<%-- Is this session for a logged in user? --%>
<% 
Object obj = session.getAttribute("role");
String role = (obj == null) ? null : (String)obj;
%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="http://localhost:8080/CST8288_Final_Project/css/styles.css"/>
    </head>
    <header class="banner">
    <nav>
        <%if ("Manager".equals(role)) { %> <%-- Are we logged in? --%>
            <a href="home">Notifications</a>
            <a href="VehicleController">Vehicles</a>
            <a href="reportMenue.jsp">Reports</a>
            <a href="maintenance">Maintenance</a>
            <a href="GPSController">GPS Tracking</a>
        <%} else if ("Operator".equals(role)) { %>
            <a href="BreakController">Breaks</a>
        <%} else {%>
            <a href="loginView">Log in</a>
        <%}%>
        
        <% if (role != null) { %> 
            <a href="LogoutController" method="POST">Logout</a>
        <% } else { %>
            <a href="register" method="POST">Register</a> 
        <% } %>
    </nav>
    </header>

</html>
