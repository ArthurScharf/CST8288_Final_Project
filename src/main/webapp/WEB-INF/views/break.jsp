<%-- 
    Document   : break
    Created on : Aug 6, 2025, 1:28:12 p.m.
    Author     : gurth
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="dataaccesslayer.BreakDAO"%>
<%@page import="transportobjects.BreakDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.sql.SQLException"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Break Management</title>
        <link rel="stylesheet" type="text/css" href="http://localhost:8080/CST8288_Final_Project/css/styles.css"/>
        <script src="https://cdn.tailwindcss.com"></script>
        <style>
            body {
                font-family: "Inter", sans-serif;
                background-color: #f0f4f8;
                margin: 0;
                padding: 0;
            }
            .main-content-wrapper {
                display: flex;
                flex-direction: column;
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
                    flex-direction: row;
                }
            }

            .panel {
                flex: 1;
                padding: 20px;
                border-radius: 8px;
                background-color: #f9fafb;
                box-shadow: inset 0 2px 4px rgba(0, 0, 0, 0.06);
                min-height: 400px;
                display: flex;
                flex-direction: column;
            }

            .scrollable-list {
                flex-grow: 1;
                overflow-y: auto;
                max-height: 350px;
                padding-right: 10px;
            }

            .break-card {
                background-color: #ffffff;
                border: 1px solid #e2e8f0;
                border-radius: 8px;
                padding: 15px;
                margin-bottom: 10px;
                box-shadow: 0 2px 5px rgba(0, 0, 0, 0.05);
                text-align: left;
                display: flex;
                justify-content: space-between;
                align-items: center;
            }
            
            .break-card p {
                margin-bottom: 0;
                font-size: 0.95em;
                color: #334155;
            }
            
            .break-card p strong {
                color: #1e293b;
            }

            .break-info {
                flex-grow: 1;
            }

            .form-group {
                margin-bottom: 15px;
            }
            
            .form-group label {
                display: block;
                margin-bottom: 5px;
                font-weight: 600;
                color: #334155;
            }
            
            .form-group select {
                width: 100%;
                padding: 10px;
                border: 1px solid #cbd5e1;
                border-radius: 6px;
                font-size: 1em;
                box-sizing: border-box;
            }

            .submit-button {
                background-image: linear-gradient(to right, #4CAF50 0%, #2E7D32 100%);
                color: white;
                padding: 12px 25px;
                border: none;
                border-radius: 8px;
                font-size: 1.05em;
                cursor: pointer;
                transition: all 0.3s ease;
                box-shadow: 0 4px 10px rgba(0, 0, 0, 0.15);
                width: 100%;
                margin-bottom: 10px;
            }
            
            .submit-button:hover {
                transform: translateY(-1px);
                box-shadow: 0 6px 15px rgba(0, 0, 0, 0.2);
            }

            .end-button {
                background-image: linear-gradient(to right, #ff9800 0%, #f57c00 100%);
                padding: 8px 15px;
                font-size: 0.9em;
                width: auto;
                margin-bottom: 0;
                margin-left: 10px;
            }

            .status-active {
                color: #22c55e;
                font-weight: bold;
            }

            .status-completed {
                color: #6b7280;
            }

            .break-emergency {
                border-left: 4px solid #ef4444;
            }

            .error-message {
                color: red;
                text-align: center;
                margin-top: 20px;
                font-weight: bold;
            }

            .no-breaks {
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
            <!-- Left Side: Break History -->
            <div class="panel break-history-panel">
                <h2 class="text-xl font-semibold text-gray-800 mb-4">My Break History</h2>
                <div class="scrollable-list">
                    <%
                        BreakDAO dao = new BreakDAO();
                        ArrayList<BreakDTO> breaks = null;
                        String errorMessage = null;
                        
                        Integer operatorId = (Integer) session.getAttribute("id");

                        try {
                            if (operatorId != null) {
                                breaks = dao.getByOperatorId(operatorId);
                            }
                        } catch (Exception e) {
                            errorMessage = "Error retrieving breaks: " + e.getMessage();
                            e.printStackTrace();
                        }

                        if (errorMessage != null) {
                    %>
                            <p class="error-message"><%= errorMessage %></p>
                    <%
                        } else if (breaks == null || breaks.isEmpty()) {
                    %>
                            <p class="no-breaks">No breaks found.</p>
                    <%
                        } else {
                            for (BreakDTO breakDto : breaks) {
                                String cardClass = "break-card";
                                if ("EMERGENCY".equals(breakDto.getBreakType())) {
                                    cardClass += " break-emergency";
                                }
                    %>
                                <div class="<%= cardClass %>">
                                    <div class="break-info">
                                        <p><strong>Type:</strong> <%= breakDto.getBreakType() %> | 
                                           <strong>Status:</strong> 
                                           <span class="<%= breakDto.isActive() ? "status-active" : "status-completed" %>">
                                               <%= breakDto.getStatus() %>
                                           </span>
                                        </p>
                                    </div>
                                    <% if (breakDto.isActive()) { %>
                                        <form method="POST" action="BreakController" style="margin: 0;">
                                            <input type="hidden" name="action" value="end">
                                            <input type="hidden" name="breakId" value="<%= breakDto.getBreakId() %>">
                                            <button type="submit" class="submit-button end-button">End Break</button>
                                        </form>
                                    <% } %>
                                </div>
                    <%
                            }
                        }
                    %>
                </div>
            </div>

            <!-- Right Side: Start New Break -->
            <div class="panel new-break-form-panel">
                <h2 class="text-xl font-semibold text-gray-800 mb-4">Start New Break</h2>
                <form action="BreakController" method="POST" class="space-y-4">
                    <input type="hidden" name="action" value="start">
                    
                    <div class="form-group">
                        <label for="breakType">Break Type:</label>
                        <select id="breakType" name="breakType" required>
                            <option value="REST">Rest Break</option>
                            <option value="LUNCH">Lunch Break</option>
                            <option value="EMERGENCY">Emergency Break</option>
                        </select>
                    </div>
                    
                    <button type="submit" class="submit-button">Start Break</button>                   
                </form>
            </div>
        </div>
    </body>
</html>