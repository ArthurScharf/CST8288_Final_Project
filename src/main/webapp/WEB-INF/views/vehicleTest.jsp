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
    </head>
    <body>
        <%@ include file="/WEB-INF/views/header.jsp" %>
        <br><br>
        
        <center>
        <table border="1">
            <tr>
                <th>Vehicle Number</th>
                <th>Vehicle Type</th>
                <th>Max Passengers</th>
                <th>Total Dist Traveled</th>
            </tr>
            
        <%
            ArrayList<String> strings = (ArrayList<String>)application.getAttribute("testAttribute");
            ArrayList<VehicleDTO> vcls = (ArrayList<VehicleDTO>)application.getAttribute("activeVehicles");
//            out.println(String.format("<p>%s</p>", strings.get(0)));
//            out.println(String.format("<p>%s</p>", strings.get(1)));
//            out.println(String.format("<p>%s</p>", strings.get(2)));
            for (VehicleDTO v : vcls)
            {
                out.println("<tr>");
                out.println(
                    String.format(""
                    + "<td> %s </td> "
                    + "<td> %s </td>"
                    + "<td> %d </td>"
                    + "<td> %f </td>", 
                    v.getVehicleNumber(), v.getType(), v.getMaximumPassengers(), v.getTotalDistanceTraveled())
                );
                out.println("</tr>");
            }
        %>
        </table>
        </center>
        <br><br>
        <%@ include file="/WEB-INF/views/footer.jsp" %>
    </body>
</html>
