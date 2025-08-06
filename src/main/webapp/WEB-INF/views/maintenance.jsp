<%-- 
    Document   : maintenance
    Created on : Aug 5, 2025, 2:50:26 PM
    Author     : User
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="dataaccesslayer.MaintenanceDAO"%>
<%@page import="transportobjects.MaintenanceTaskDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.sql.SQLException"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Maintenance Tasks</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                margin: 20px;
                background-color: #f4f4f4;
                color: #333;
            }
            h1 {
                color: #0056b3;
                text-align: center;
                margin-bottom: 30px;
            }
            table {
                width: 80%;
                margin: 0 auto;
                border-collapse: collapse;
                box-shadow: 0 2px 8px rgba(0,0,0,0.1);
                background-color: #fff;
            }
            th, td {
                padding: 12px 15px;
                text-align: left;
                border-bottom: 1px solid #ddd;
            }
            th {
                background-color: #007bff;
                color: white;
                text-transform: uppercase;
                letter-spacing: 0.05em;
            }
            tr:nth-child(even) {
                background-color: #f9f9f9;
            }
            tr:hover {
                background-color: #e9e9e9;
            }
            .no-tasks {
                text-align: center;
                font-style: italic;
                color: #666;
                margin-top: 20px;
            }
            .error-message {
                color: red;
                text-align: center;
                margin-top: 20px;
                font-weight: bold;
            }
        </style>
    </head>
    <body>
        <jsp:include page="/WEB-INF/views/header.jsp"/>

        <%
            MaintenanceDAO dao = new MaintenanceDAO();
            ArrayList<MaintenanceTaskDTO> tasks = null;
            String errorMessage = null;

            try {
                tasks = dao.getAll();
            } catch (Exception e) {
                errorMessage = "Error retrieving tasks: " + e.getMessage();
                e.printStackTrace(); 
            }

            if (errorMessage != null) {
        %>
                <p class="error-message"><%= errorMessage %></p>
        <%
            } else if (tasks == null || tasks.isEmpty()) {
        %>
                <p class="no-tasks">No maintenance tasks found.</p>
        <%
            } else {
        %>
                <table>
                    <thead>
                        <tr>
                            <th>Task ID</th>
                            <th>Description</th>
                            <th>Status</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            for (MaintenanceTaskDTO task : tasks) {
                        %>
                                <tr>
                                    <td><%= task.getTaskId() %></td>
                                    <td><%= task.getDescription() %></td>
                                    <td><%= task.getStatus() %></td>
                                </tr>
                        <%
                            }
                        %>
                    </tbody>
                </table>
        <%
            }
        %>
    </body>
</html>
