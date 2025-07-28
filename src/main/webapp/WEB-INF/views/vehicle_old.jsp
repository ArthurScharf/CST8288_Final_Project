<%-- 
    Document   : vehicleTest
    Created on : Jul 25, 2025, 5:26:15 PM
    Author     : Arthur Scharf
--%>

<%@page import="transportobjects.VehicleDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" type="text/css" href="http://localhost:8080/CST8288_Final_Project/css/styles.css"/>
    </head>
    <body>
    <jsp:include page="/WEB-INF/views/header.jsp" /> 
    <br><br>
    <center>
        <table style="border: 1px solid black; border-collapse: collapse;">
            <tr>
                <th style="border: 1px solid black; border-collapse: collapse;">Vehicle Number</th>
                <th style="border: 1px solid black; border-collapse: collapse;">Vehicle Type</th>
                <th style="border: 1px solid black; border-collapse: collapse;">Max Passengers</th>
                <th style="border: 1px solid black; border-collapse: collapse;">Total Dist Traveled</th>
            </tr>  
        <%
            ArrayList<VehicleDTO> vcls = (ArrayList<VehicleDTO>)application.getAttribute("activeVehicles");
            for (VehicleDTO v : vcls)
            {
                out.println("<tr>");
                out.println(
                    String.format(""
                    + "<td style=\"border: 1px solid black\"; border-collapse: collapse;> %s </td> "
                    + "<td style=\"border: 1px solid black\"; border-collapse: collapse;> %s </td>"
                    + "<td style=\"border: 1px solid black\"; border-collapse: collapse;> %d </td>"
                    + "<td style=\"border: 1px solid black\"; border-collapse: collapse;> %f </td>", 
                    v.getVehicleNumber(), v.getType(), v.getMaximumPassengers(), v.getTotalDistanceTraveled())
                );
                out.println("</tr>");
            }
        %>
        </table>
    </center>
    </body>
    
</html>
-->