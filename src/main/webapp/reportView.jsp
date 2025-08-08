<%-- Import statements for classes used in this JSP --%>
<%@page import="businesslayer.report.USDToCAD"%>
<%@page import="transportobjects.DieselElectricDTO"%>
<%@page import="java.sql.SQLException"%>
<%@page import="dataaccesslayer.VehicleDAO"%>
<%@page import="transportobjects.VehicleDTO"%>
<%@page import="transportobjects.LightRailDTO"%>
<%@page import="java.lang.Exception"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="businesslayer.report.ReportBuilder"%>
<%@page import="businesslayer.report.CADToUSD"%>
<%@page import="businesslayer.report.Report"%>
<%@page import="businesslayer.report.ICurrencyStrategy"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<% 
    // Read selected report sections (checkboxes named "sections") from the form submission.
    String[] selectedSections = request.getParameterValues("sections");
    // Read selected currency conversion mode from the form (radio/select named "costCurrency").
    String costMode = request.getParameter("costCurrency");
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Transit Report View</title>
    <!-- Tailwind CSS for quick styling -->
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-gray-100 font-sans text-gray-800">
    <div class="max-w-5xl mx-auto mt-10 bg-white p-8 rounded-lg shadow-md">
        <h1 class="text-3xl font-bold mb-6 text-center">Transit Report</h1>
        <div class="space-y-4">
            <%
                // Create a ReportBuilder using a static builder() factory on Report.
                ReportBuilder builder = Report.builder();
                Report repo = null;

                // Flags for which sections the user selected.
                boolean isBusSelected = false;
                boolean isDieselElectric = false;
                boolean isLightRail = false;

                // Determine which sections were checked by the user.
                if (selectedSections != null) {
                    for (String section : selectedSections) {
                        if ("bus".equals(section)) {
                            isBusSelected = true;
                        } else if ("dieselElectric".equals(section)) {
                            isDieselElectric = true;
                        } else if ("lightRail".equals(section)) {
                            isLightRail = true;
                        }
                    }
                }

                // DAO for data access and strategy for currency conversion.
                VehicleDAO dao;
                ICurrencyStrategy mode;

                // Choose the currency conversion strategy based on user selection.
                switch (costMode) {
                    case "CADToUSD":
                        mode = new CADToUSD();
                        break;
                    case "USDToCAD":
                    default:
                        mode = new USDToCAD();
                        break;
                }

                // Apply the chosen currency mode to the builder.
                builder.addMode(mode);

                // If Diesel-Electric section is selected, fetch data and add to builder.
                if (isDieselElectric) {
                    dao = new VehicleDAO();
                    ArrayList<VehicleDTO> dieselElectricDTOList = dao.getAll();
                    builder.addDeiselElectricDTOList(dieselElectricDTOList);
                }

                // If Light Rail section is selected, fetch data and add to builder.
                if (isLightRail) {
                    dao = new VehicleDAO();
                    ArrayList<VehicleDTO> lightRailDTOList = dao.getAll();
                    builder.addLightRailDTO(lightRailDTOList);
                }

                // If Bus section is selected, fetch data and add to builder.
                if (isBusSelected) {
                    dao = new VehicleDAO();
                    ArrayList<VehicleDTO> busDTOList = dao.getAll();
                    builder.addBusDTOList(busDTOList);
                }

                // Build the final Report object from the builder.
                repo = builder.build();

                // Generate HTML lines from the report and write them to the response.
                List<String> htmlLines = repo.toHTML();
                for (String line : htmlLines) {
                    out.println("<div class='mb-4 p-4 bg-gray-100 rounded shadow'>" + line + "</div>");
                }
            %>
        </div>
    </div>
</body>
</html>