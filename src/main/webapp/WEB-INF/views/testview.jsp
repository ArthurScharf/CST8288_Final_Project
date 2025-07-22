<%-- 
    Document   : TestJSP
    Created on : Jul 22, 2025, 12:16:24 PM
    Author     : Arthur Scharf
--%>

<%@page import="java.util.ArrayList"%> <%-- Import statement --%>
<%@page import='businesslayer.TestDataGenerator' %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!-- 
            I don't know why, but the application fails to interpret the path 
            from the root of the project 'webapp'. Absolute path is required...
        -->
        <link rel="stylesheet" type="text/css" href="http://localhost:8080/CST8288_Final_Project/css/styles.css"/>
        <title>JSP-Page</title>
    </head>
    <body>
    <center>
        <%-- Including other files. Makes things cleaner --%>
        
        <%@ include file="/WEB-INF/views/header.jsp" %>
        
        <%--  <jsp:include page="/TestServlet" /> dynamic incldue --%>
        
        <%
            // ArrayList<String> results = (ArrayList<String>)request.getAttribute("results");
            ArrayList<String> results = TestDataGenerator.execute();
            for (String str : results)
            {
                out.println("<p>" + str + "</p>");
            }
        %>
        
        <%@ include file="/WEB-INF/views/footer.jsp" %> 
        
    </center>
    </body>
</html>
