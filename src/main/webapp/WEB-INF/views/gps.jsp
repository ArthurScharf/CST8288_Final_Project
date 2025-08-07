<%-- 
    Document   : gps
    Created on : 2025-08-07
    Author     : Benjamin Gurth
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="transportobjects.StopsDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="businesslayer.GenerateTime"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>GPS Tracking Reports</title>
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
            .panel {
                padding: 20px;
                border-radius: 8px;
                background-color: #f9fafb;
                box-shadow: inset 0 2px 4px rgba(0, 0, 0, 0.06);
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
            }
            .submit-button:hover {
                transform: translateY(-1px);
                box-shadow: 0 6px 15px rgba(0, 0, 0, 0.2);
            }
            table {
                width: 100%;
                border-collapse: collapse;
                margin-top: 20px;
                background-color: white;
                border-radius: 8px;
                overflow: hidden;
                box-shadow: 0 2px 8px rgba(0,0,0,0.1);
            }
            th, td {
                padding: 12px 15px;
                text-align: left;
                border-bottom: 1px solid #e2e8f0;
            }
            th {
                background-color: #f8fafc;
                font-weight: 600;
                color: #334155;
            }
            tr:hover {
                background-color: #f1f5f9;
            }
            .no-data {
                text-align: center;
                color: #64748b;
                font-style: italic;
                padding: 40px;
            }
        </style>
    </head>
    <body>
        <jsp:include page="/WEB-INF/views/header.jsp" />

        <div class="main-content-wrapper">
            <div class="panel">
                <h1 class="text-2xl font-bold text-gray-800 mb-6">GPS Tracking Reports</h1>
                
                <form method="POST" action="GPSController" class="mb-6">
                    <div class="form-group">
                        <label for="stopName">Select Stop:</label>
                        <select id="stopName" name="stopName" required>
                            <option value="">-- Select a Stop --</option>
                            <%
                                ArrayList<StopsDTO> stops = (ArrayList<StopsDTO>) request.getAttribute("stops");
                                String selectedStop = (String) request.getAttribute("selectedStop");
                                
                                if (stops != null) {
                                    for (StopsDTO stop : stops) {
                                        String selected = (selectedStop != null && selectedStop.equals(stop.getStopName())) ? "selected" : "";
                                        out.println("<option value=\"" + stop.getStopName() + "\" " + selected + ">" + stop.getStopName() + "</option>");
                                    }
                                }
                            %>
                        </select>
                    </div>
                    <button type="submit" class="submit-button">Generate Report</button>
                </form>

                <%
                    if (selectedStop != null && !selectedStop.trim().isEmpty()) {
                %>
                        <div class="report-section">
                            <h2 class="text-xl font-semibold text-gray-800 mb-4">Vehicle Times for: <%= selectedStop %></h2>
                            
                            <div style="display: flex; gap: 20px; flex-wrap: wrap;">
                                <div style="flex: 1; min-width: 300px;">
                                    <h3 class="text-lg font-medium text-gray-700 mb-3">Arrival Times</h3>
                                    <table>
                                        <thead>
                                            <tr>
                                                <th>Vehicle Number</th>
                                                <th>Arrival Time</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <%
                                                try {
                                                    GenerateTime gt = new GenerateTime();
                                                    HashMap<String, String> arrivals = gt.getArrival(selectedStop);
                                                    
                                                    if (arrivals != null && !arrivals.isEmpty()) {
                                                        for (Map.Entry<String, String> entry : arrivals.entrySet()) {
                                                            out.println("<tr>");
                                                            out.println("<td>" + entry.getKey() + "</td>");
                                                            out.println("<td>" + entry.getValue() + "</td>");
                                                            out.println("</tr>");
                                                        }
                                                    } else {
                                                        out.println("<tr><td colspan='2' class='no-data'>No arrival data available</td></tr>");
                                                    }
                                                } catch (Exception e) {
                                                    out.println("<tr><td colspan='2' class='no-data'>Error loading arrival data</td></tr>");
                                                }
                                            %>
                                        </tbody>
                                    </table>
                                </div>

                                <div style="flex: 1; min-width: 300px;">
                                    <h3 class="text-lg font-medium text-gray-700 mb-3">Departure Times</h3>
                                    <table>
                                        <thead>
                                            <tr>
                                                <th>Vehicle Number</th>
                                                <th>Departure Time</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <%
                                                try {
                                                    GenerateTime gt = new GenerateTime();
                                                    HashMap<String, String> departures = gt.getDeparture(selectedStop);
                                                    
                                                    if (departures != null && !departures.isEmpty()) {
                                                        for (Map.Entry<String, String> entry : departures.entrySet()) {
                                                            out.println("<tr>");
                                                            out.println("<td>" + entry.getKey() + "</td>");
                                                            out.println("<td>" + entry.getValue() + "</td>");
                                                            out.println("</tr>");
                                                        }
                                                    } else {
                                                        out.println("<tr><td colspan='2' class='no-data'>No departure data available</td></tr>");
                                                    }
                                                } catch (Exception e) {
                                                    out.println("<tr><td colspan='2' class='no-data'>Error loading departure data</td></tr>");
                                                }
                                            %>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                <%
                    } else {
                %>
                        <div class="no-data">
                            <p>Select a stop above to view vehicle arrival and departure times.</p>
                        </div>
                <%
                    }
                %>
            </div>
        </div>
    </body>
</html>