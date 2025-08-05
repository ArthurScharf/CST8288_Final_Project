<%--
    Document   : notificationTest
    Created on : Aug 5, 2025
    Author     : Arthur Scharf
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="dataaccesslayer.NotificationDAO"%>
<%@page import="transportobjects.NotificationDTO"%>
<%@page import="transportobjects.NotificationType"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.sql.SQLException"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Notifications</title>
        <!-- Link to your unified CSS file (when created) -->
        <link rel="stylesheet" type="text/css" href="http://localhost:8080/CST8288_Final_Project/css/styles.css"/>
        <!-- Include Tailwind CSS for modern styling -->
        <script src="https://cdn.tailwindcss.com"></script>
        <style>
            /* Custom styles for overall layout and scrollability - matching vehicleTest.jsp */
            body {
                font-family: "Inter", sans-serif;
                background-color: #f0f4f8; /* Light blue-gray background */
                margin: 0;
                padding: 0;
            }
            .main-content-wrapper {
                display: flex;
                flex-direction: column; /* Stack on small screens */
                gap: 20px;
                padding: 20px;
                max-width: 1200px;
                margin: 20px auto;
                background-color: #ffffff;
                border-radius: 12px;
                box-shadow: 0 10px 25px rgba(0, 0, 0, 0.1);
            }
            @media (min-width: 768px) {
                .main-content-wrapper {
                    flex-direction: row; /* Side-by-side on medium screens and up */
                }
            }

            .panel {
                flex: 1; /* Each panel takes equal width */
                padding: 20px;
                border-radius: 8px;
                background-color: #f9fafb; /* Lighter background for panels */
                box-shadow: inset 0 2px 4px rgba(0, 0, 0, 0.06);
                min-height: 400px; /* Minimum height for panels */
                display: flex;
                flex-direction: column;
            }

            .scrollable-list {
                flex-grow: 1; /* Allows the list to take available space */
                overflow-y: auto; /* Enables vertical scrolling */
                max-height: 350px; /* Max height before scrolling kicks in */
                padding-right: 10px; /* Space for scrollbar */
            }

            /* Adjusted for notifications, similar to vehicle-card */
            .notification-card {
                background-color: #ffffff;
                border: 1px solid #e2e8f0;
                border-radius: 8px;
                padding: 15px;
                margin-bottom: 10px;
                box-shadow: 0 2px 5px rgba(0, 0, 0, 0.05);
                text-align: left;
                display: flex; /* Use flexbox for layout inside card */
                flex-direction: column;
                gap: 8px; /* Space between card elements */
            }
            
            .notification-card p {
                margin-bottom: 0; /* Remove default paragraph margin */
                font-size: 0.95em;
                color: #334155;
            }
            .notification-card p strong {
                color: #1e293b;
            }

            /* Form specific styles - matching vehicleTest.jsp */
            .form-group {
                margin-bottom: 15px; /* Not directly used for buttons here, but good to keep */
            }
            .submit-button {
                background-image: linear-gradient(to right, #4CAF50 0%, #2E7D32 100%);
                color: white;
                padding: 8px 15px; /* Slightly smaller for individual buttons */
                border: none;
                border-radius: 6px; /* Slightly smaller radius */
                font-size: 0.95em;
                cursor: pointer;
                transition: all 0.3s ease;
                box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1); /* Smaller shadow */
                width: auto; /* Allow button to size to content */
                align-self: flex-end; /* Align button to the right within the card */
            }
            .submit-button:hover {
                transform: translateY(-1px);
                box-shadow: 0 4px 8px rgba(0, 0, 0, 0.15);
            }
            .error-message {
                color: red;
                text-align: center;
                margin-top: 20px;
                font-weight: bold;
            }
            .no-notifications {
                text-align: center;
                font-style: italic;
                color: #666;
                margin-top: 20px;
            }
        </style>
    </head>
    <body>
        <jsp:include page="/WEB-INF/views/header.jsp" />

        <div class="main-content-wrapper">
            <!-- Left Side: Notification List -->
            <div class="panel notification-list-panel">
                <h2 class="text-xl font-semibold text-gray-800 mb-4">System Notifications</h2>
                <div class="scrollable-list">
                    <%
                        NotificationDAO dao = new NotificationDAO();
                        ArrayList<NotificationDTO> notifications = null;
                        String errorMessage = null;

                        try {
                            notifications = dao.getAll();
                        } catch (Exception e) {
                            errorMessage = "Error retrieving notifications: " + e.getMessage();
                            e.printStackTrace(); // Log error to server console
                        }

                        if (errorMessage != null) {
                    %>
                            <p class="error-message"><%= errorMessage %></p>
                    <%
                        } else if (notifications == null || notifications.isEmpty()) {
                    %>
                            <p class="no-notifications">No active notifications found.</p>
                    <%
                        } else {
                            for (NotificationDTO notification : notifications) {
                    %>
                                <div class="notification-card">
                                    <p><strong>ID:</strong> <%= notification.getId() %></p>
                                    <p><strong>Type:</strong> <%= notification.getType().name() %></p>
                                    <p><strong>Data:</strong> <%= notification.getData() %></p>
                                    
                                    <!-- <%= notification.getType().name() %> -->
                                    <form action="NotificationResponseController" method="POST" class="mt-2">
                                        <!-- You might want to include the ID as a hidden input if the controller needs it -->
                                        <input type="hidden" name="notificationId" value="<%= notification.getId() %>">
                                        <button type="submit" class="submit-button">Handle <%= notification.getType().name().toLowerCase() %></button>
                                    </form>
                                </div>
                    <%
                            }
                        }
                    %>
                </div>
            </div>
        </div>
    </body>
</html>
