<%-- 
    Document   : reporttest
    Created on : Jul 26, 2025, 1:33:36 p.m.
    Author     : Sina Paslar
--%>

<%@page import="businesslayer.report.Report"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%@ include file="/WEB-INF/views/header.jsp" %>
        <%
            Report repo = Report.builder().addCost(10).addEnergyConsumption(20).addLocationTracking("Piont 1").build();
            double cost = repo.getCost();
            out.println("<p> cost: "+ cost + "</p>");
            
            
            
            
            %>
        
        
        
        
        
        
        
        
        <%@ include file="/WEB-INF/views/footer.jsp" %> 
    </body>
</html>
