<%-- 
    Document   : reportMenu
    Created on : Jul 27, 2025, 1:50:49 p.m.
    Author     : Sina Paslar
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h2>Transit Report Form</h2>
    <form action="${pageContext.request.contextPath}/reportView.jsp" method="post">
        <!-- Report Section Checkboxes -->
        <p>Select Report Sections:</p>
        <!-- 
<input type="checkbox" id="locationTracking" name="sections" value="locationTracking">
<label for="locationTracking">Location Tracking</label><br>

<input type="checkbox" id="energyConsumption" name="sections" value="energyConsumption">
<label for="energyConsumption">Energy Consumption</label><br>

<input type="checkbox" id="transitMaintenance" name="sections" value="transitMaintenance">
<label for="transitMaintenance">Transit Maintenance</label><br>

<input type="checkbox" id="operatorPerformance" name="sections" value="operatorPerformance">
<label for="operatorPerformance">Operator Performance</label><br>

<input type="checkbox" id="cost" name="sections" value="cost">
<label for="cost">Cost</label><br>

<input type="checkbox" id="currencyStrategy" name="sections" value="currencyStrategy">
<label for="currencyStrategy">Currency Strategy</label><br>



<input type="checkbox" id="vehicleDTO" name="sections" value="vehicleDTO">
<label for="vehicleDTO">Vehicle Report</label><br>


-->
        <input type="checkbox" id="lightRail" name="sections" value="lightRail">
        <label for="lightRail">Light Rail Analysis</label><br>

        <input type="checkbox" id="dieselElectric" name="sections" value="dieselElectric">
        <label for="dieselElectric">Diesel ELectric Train Report</label><br>

        <input type="checkbox" id="bus" name="sections" value="bus">
        <label for="bus">Bus Report</label><br>

        <br><br>

        <!-- Radio Buttons for Cost Currency -->
        <label>Cost Currency:</label><br>
        <input type="radio" id="cad" name="costCurrency" value="USDToCAD" checked>
        <label for="cad">From USD To CAD</label><br>
        <input type="radio" id="usd" name="costCurrency" value="CADToUSD">
        <label for="usd">From CAD TO USD</label><br>
        
        <!-- Submit Button -->
        <input type="submit" value="Submit Report">
    </form>
    </body>
</html>
