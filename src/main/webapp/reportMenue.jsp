<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Transit Report Menu</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-gray-100 font-sans text-gray-800">
    <jsp:include page="/WEB-INF/views/header.jsp"/>
    <div class="max-w-2xl mx-auto mt-10 bg-white p-8 rounded-lg shadow-md">
        <h2 class="text-2xl font-bold mb-6 text-center">Transit Report Form</h2>
        <form action="${pageContext.request.contextPath}/reportView.jsp" method="post" class="space-y-6">

            <!-- Report Sections -->
            <div>
                <p class="text-lg font-semibold mb-2">Select Report Sections:</p>
                <div class="space-y-2 pl-2">
                    <label class="flex items-center">
                        <input type="checkbox" id="lightRail" name="sections" value="lightRail" class="mr-2">
                        Light Rail Analysis
                    </label>
                    <label class="flex items-center">
                        <input type="checkbox" id="dieselElectric" name="sections" value="dieselElectric" class="mr-2">
                        Diesel Electric Train Report
                    </label>
                    <label class="flex items-center">
                        <input type="checkbox" id="bus" name="sections" value="bus" class="mr-2">
                        Bus Report
                    </label>
                </div>
            </div>

            <!-- Cost Currency -->
            <div>
                <p class="text-lg font-semibold mb-2">Cost Currency:</p>
                <div class="space-y-2 pl-2">
                    <label class="flex items-center">
                        <input type="radio" id="cad" name="costCurrency" value="USDToCAD" checked class="mr-2">
                        From USD to CAD
                    </label>
                    <label class="flex items-center">
                        <input type="radio" id="usd" name="costCurrency" value="CADToUSD" class="mr-2">
                        From CAD to USD
                    </label>
                </div>
            </div>

            <!-- Submit Button -->
            <div>
                <input type="submit" value="Submit Report" 
                       class="w-full bg-green-600 hover:bg-green-700 text-white font-semibold py-3 px-6 rounded-lg transition duration-300 shadow-md">
            </div>
        </form>
    </div>
</body>
</html>
