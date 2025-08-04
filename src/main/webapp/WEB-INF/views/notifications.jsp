<%-- 
    Document   : notificationTest
    Created on : Jul 28, 2025, 2:42:38 PM
    Author     : Arthur Scharf
--%>
<%@page import='dataaccesslayer.NotificationDAO' %>
<%@page import='dataaccesslayer.NotificationType' %>
<%@page import='transportobjects.NotificationDTO' %>
<%@page import='java.util.ArrayList' %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<!--    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" type="text/css" href="http://localhost:8080/CST8288_Final_Project/css/styles.css"/>
    </head>-->
<!--    <body>-->
<center>
        <table style="border: 1px solid black; border-collapse: collapse;">
        <tr>
            <th style="border: 1px solid black; border-collapse: collapse;">ID</th>
            <th style="border: 1px solid black; border-collapse: collapse;">Type</th>
            <th style="border: 1px solid black; border-collapse: collapse;">Data</th>
        </tr> 
        <%
            // -- Test by returning all notifications of a type -- //
            NotificationDAO dao = new NotificationDAO();
            try {
                ArrayList<NotificationDTO> dtos = dao.getByType(NotificationType.MAINTENANCE);
                for (NotificationDTO n : dtos)
                {
                    out.println("<tr>");
                    out.println(
                        String.format(""
                        + "<td style=\"border: 1px solid black\"; border-collapse: collapse;> %d </td> "
                        + "<td style=\"border: 1px solid black\"; border-collapse: collapse;> %s </td>"
                        + "<td style=\"border: 1px solid black\"; border-collapse: collapse;> %s </td>", 
                        n.getId(), n.getType().name(), n.getData())
                    );
                    out.println("</tr>");
                }
            } catch (Exception e)
            {
                out.println("<p>"+e.getMessage()+"</p>"); // Should be a redirect, but this is faster
            }
        %>
        </table>
</center>
<!--    </body>-->
</html>
