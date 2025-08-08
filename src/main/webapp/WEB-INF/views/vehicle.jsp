<%-- 
    Document   : vehicleTest
    Created on : Jul 25, 2025, 5:26:15 PM
    Author     : Arthur Scharf
--%>

<%@page import="transportobjects.VehicleDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Vehicle Management</title>
        <link rel="stylesheet" type="text/css" href="http://localhost:8080/CST8288_Final_Project/css/styles.css"/>
        <!-- Include Tailwind CSS for modern styling -->
        <script src="https://cdn.tailwindcss.com"></script>
        <style>
            /* Custom styles for overall layout and scrollability */
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

            .vehicle-card {
                background-color: #ffffff;
                border: 1px solid #e2e8f0;
                border-radius: 8px;
                padding: 15px;
                margin-bottom: 10px;
                box-shadow: 0 2px 5px rgba(0, 0, 0, 0.05);
                text-align: left; /* Added this line to ensure left justification */
            }
            
            .vehicle-card p {
                margin-bottom: 5px;
                font-size: 0.95em;
                color: #334155;
            }
            .vehicle-card p strong {
                color: #1e293b;
            }

            /* Form specific styles */
            .form-group {
                margin-bottom: 15px;
            }
            .form-group label {
                display: block;
                margin-bottom: 5px;
                font-weight: 600;
                color: #334155;
            }
            .form-group input[type="text"],
            .form-group input[type="number"] {
                width: 100%;
                padding: 10px;
                border: 1px solid #cbd5e1;
                border-radius: 6px;
                font-size: 1em;
                box-sizing: border-box; /* Include padding in width */
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
            }
            .submit-button:hover {
                transform: translateY(-1px);
                box-shadow: 0 6px 15px rgba(0, 0, 0, 0.2);
            }
        </style>
    </head>
    <body>
        <%
            // --- ADD THESE LINES TO PREVENT CACHING ---
            response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1
            response.setHeader("Pragma", "no-cache"); // HTTP 1.0
            response.setHeader("Expires", "0"); // Proxies
            // --- END CACHE CONTROL ---
        %>
        <jsp:include page="/WEB-INF/views/header.jsp" />
        <div class="main-content-wrapper">
            <!-- Left Side: Vehicle List -->
            <div class="panel vehicle-list-panel">
                <h2 class="text-xl font-semibold text-gray-800 mb-4">Active Vehicles</h2>
                <div class="scrollable-list">
                    <%
                        try {
                            // Retrieve the list of vehicles from application scope
                            ArrayList<VehicleDTO> vcls = (ArrayList<VehicleDTO>)application.getAttribute("activeVehicles");

                            // Add a null check to prevent NullPointerException
                            if (vcls != null && !vcls.isEmpty()) {
                                for (VehicleDTO v : vcls) {
                                    out.println("<div class=\"vehicle-card\">");
                                    out.println("<p><strong>Vehicle Number:</strong> " + v.getVehicleNumber() + "</p>");
                                    out.println("<p><strong>Max Passengers:</strong> " + v.getMaximumPassengers() + "</p>");
                                    out.println("<p><strong>Total Dist Traveled:</strong> " + String.format("%.2f", v.getTotalDistanceTraveled()) + "</p>");
                                    out.println("<p><strong>Type Info:</strong> " + v.serialize() + "</p>");
                                    out.println("</div>");
                                }
                            } else {
                                out.println("<p class=\"text-gray-600 text-center\">No active vehicles found.</p>");
                            }
                        } catch (Exception e) {
                            // Catch any exceptions during data retrieval or rendering
                            out.println("<p class=\"text-red-600 text-center\">Error loading vehicles: " + e.getMessage() + "</p>");
                            // For debugging, you might also log e.printStackTrace() to the server console
                        }
                    %>
                </div>
            </div>
            <div class="panel new-vehicle-form-panel">
                <h2 class="text-xl font-semibold text-gray-800 mb-4">Add New Vehicle</h2>
                <form action="VehicleController" method="POST" class="space-y-4">
                    <div class="form-group">
                        <label for="vehicleNumber">Vehicle Number:</label>
                        <input type="text" id="vehicleNumber" name="vehicleNumber" required class="focus:ring-blue-500 focus:border-blue-500 rounded-md">
                    </div>
                    <div class="form-group">
                        <label for="vehicleType">Vehicle Type:</label>
                        <select id="vehicleType" name="vehicleType" required class="w-full p-2 border border-gray-300 rounded-md focus:ring-blue-500 focus:border-blue-500">
                            <option value="Bus">Bus</option>
                            <option value="DieselElectric">Diesel Electric</option>
                            <option value="LightRail">Light Rail</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="maxPassengers">Max Passengers:</label>
                        <input type="number" id="maxPassengers" name="maxPassengers" min="1" required class="focus:ring-blue-500 focus:border-blue-500 rounded-md">
                    </div>
                    <button type="submit" class="submit-button rounded-md">Add Vehicle</button>
                </form>
                <form action="VehicleController/undo" method="POST" class="mt-4">
                    <button type="submit" class="submit-button rounded-md">Undo</button>
                </form>
            </div>
        </div>
    </body>
</html>
