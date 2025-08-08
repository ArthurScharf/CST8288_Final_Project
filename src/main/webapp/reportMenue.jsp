<%@page contentType="text/html" pageEncoding="UTF-8"%> 
<%-- Sets the content type to HTML and specifies UTF-8 encoding for proper character display --%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8"> <!-- Defines character encoding -->
    <title>Transit Report Menu</title> <!-- Browser tab title -->
    <script src="https://cdn.tailwindcss.com"></script> <!-- Tailwind CSS for styling -->
</head>
<body class="bg-gray-100 font-sans text-gray-800">
    <!-- Centered container for the form with styling -->
    <div class="max-w-2xl mx-auto mt-10 bg-white p-8 rounded-lg shadow-md">
        <h2 class="text-2xl font-bold mb-6 text-center">Transit Report Form</h2>

        <!-- Form sends POST request to reportView.jsp -->
        <form action="${pageContext.request.contextPath}/reportView.jsp" method="post" class="space-y-6">
            
            <!-- SECTION: Report Sections -->
            <div>
                <p class="text-lg font-semibold mb-2">Select Report Sections:</p>
                <div class="space-y-2 pl-2">
                    <!-- Light Rail option -->
                    <label class="flex items-center">
                        <input type="checkbox" id="lightRail" name="sections" value="lightRail" class="mr-2">
                        Light Rail Analysis
                    </label>
                    <!-- Diesel Electric option -->
                    <label class="flex items-center">
                        <input type="checkbox" id="dieselElectric" name="sections" value="dieselElectric" class="mr-2">
                        Diesel Electric Train Report
                    </label>
                    <!-- Bus option -->
                    <label class="flex items-center">
                        <input type="checkbox" id="bus" name="sections" value="bus" class="mr-2">
                        Bus Report
                    </label>
                </div>
            </div>

            <!-- SECTION: Cost Currency -->
            <div>
                <p class="text-lg font-semibold mb-2">Cost Currency:</p>
                <div class="space-y-2 pl-2">
                    <!-- Radio button: USD to CAD (default selected) -->
                    <label class="flex items-center">
                        <input type="radio" id="cad" name="costCurrency" value="USDToCAD" checked class="mr-2">
                        From USD to CAD
                    </label>
                    <!-- Radio button: CAD to USD -->
                    <label class="flex items-center">
                        <input type="radio" id="usd" name="costCurrency" value="CADToUSD" class="mr-2">
                        From CAD to USD
                    </label>
                </div>
            </div>

            <!-- SECTION: Submit Button -->
            <div>
                <input type="submit" value="Submit Report" 
                       class="w-full bg-green-600 hover:bg-green-700 text-white font-semibold py-3 px-6 rounded-lg transition duration-300 shadow-md">
            </div>
        </form>
    </div>
</body>
</html>