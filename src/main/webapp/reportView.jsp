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
    String[] selectedSections = request.getParameterValues("sections");
    String costMode = request.getParameter("costCurrency");
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Transit Report View</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-gray-100 font-sans text-gray-800">
    <div class="max-w-5xl mx-auto mt-10 bg-white p-8 rounded-lg shadow-md">
        <h1 class="text-3xl font-bold mb-6 text-center">Transit Report</h1>

        <div class="space-y-4">
            <%
                ReportBuilder builder = Report.builder();
                Report repo = null;

                boolean isBusSelected = false;
                boolean isDieselElectric = false;
                boolean isLightRail = false;

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

                VehicleDAO dao;
                ICurrencyStrategy mode;

                switch (costMode) {
                    case "CADToUSD":
                        mode = new CADToUSD();
                        break;
                    case "USDToCAD":
                    default:
                        mode = new USDToCAD();
                        break;
                }

                builder.addMode(mode);

                if (isDieselElectric) {
                    dao = new VehicleDAO();
                    ArrayList<VehicleDTO> dieselElectricDTOList = dao.getAll();
                    builder.addDeiselElectricDTOList(dieselElectricDTOList);
                }

                if (isLightRail) {
                    dao = new VehicleDAO();
                    ArrayList<VehicleDTO> lightRailDTOList = dao.getAll();
                    builder.addLightRailDTO(lightRailDTOList);
                }

                if (isBusSelected) {
                    dao = new VehicleDAO();
                    ArrayList<VehicleDTO> busDTOList = dao.getAll();
                    builder.addBusDTOList(busDTOList);
                }

                repo = builder.build();
                List<String> htmlLines = repo.toHTML();
                for (String line : htmlLines) {
                    out.println("<div class='mb-4 p-4 bg-gray-100 rounded shadow'>" + line + "</div>");
                }
            %>
        </div>
    </div>
</body>
</html>
