<%-- 
    Document   : reportView
    Created on : Jul 27, 2025, 1:50:59 p.m.
    Author     : sina8
--%>

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
    //String sections = request.getParameter("sections");
    String costMode = request.getParameter("costCurrency");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Report View!</h1>
        

        
        
            <%
                ReportBuilder builder = Report.builder();
                Report repo = null;
                boolean isLocationTrackingSelected = false;
                boolean isEnergyConsumptionSelected = false;
                boolean isTarnsitMaintenance = false;
                boolean isOperatorPerformance = false;
                boolean isCostSelected = false;
                
                //PROJECT OBJECT
                boolean isVehicleSelected = false;
                boolean isBusselected = false;
                boolean isDieselElectric = false;
                boolean isLightRail = false;
                
                
                for (String section: selectedSections){
                    if ("locationTracking".equals(section)) {
                        isLocationTrackingSelected = true;
                    } else if ("energyConsumption".equals(section)) {
                        isEnergyConsumptionSelected = true;
                    } else if ("transitMaintenance".equals(section)) {
                        isTarnsitMaintenance = true;
                    } else if ("operatorPerformance".equals(section)) {
                        isOperatorPerformance = true;
                    } else if ("cost".equals(section)) {
                        isCostSelected = true;
                    } else if ("vehicleDTO".equals(section)){
                    isVehicleSelected = true; 
                    } else if ("bus".equals(section)){
                        isBusselected = true;
                    } else if ("dieselElectric".equals(section)){
                        isDieselElectric = true;
                    } else if ("lightRail".equals(section)){
                        isLightRail = true;
                }
                
                    
                }
                
                if (isLocationTrackingSelected){
                    builder.addLocationTracking("I'm here");
                }
                if (isEnergyConsumptionSelected){
                    builder.addEnergyConsumption(10);
                }
                if (isTarnsitMaintenance){
                    List<String> maintenance = new ArrayList<>();
                    
                    maintenance.add("Tire is flat");
                    builder.addTransitMaintenance(maintenance);
                }
                if (isOperatorPerformance){
                    HashMap<String, String> operator = new HashMap<>();
                    operator.put("Driver 1", "Excellent");
                    operator.put("Driver 2", "Satisfactory");
                    builder.addOperatorPerformance(operator);
                    
                   
                }
//                if (isCostSelected){
//                    int mode = 0;
//                    switch(costMode){
//                    case "USDToCAD": mode = 1;
//                    break;
//                    case "CADToUSD": mode = 2;
//                    break;              
//                    }
//                    builder.addCost(10, mode);
//                }
//                
                
                
                if (isDieselElectric){
                    VehicleDAO dao = new VehicleDAO();
                    ArrayList<VehicleDTO> dieselElectricDTODTOList = dao.getAll();
                    builder.addDeiselElectricDTOList(dieselElectricDTODTOList);
                
                
                }
                
                if (isLightRail){   
                    VehicleDAO dao = new VehicleDAO();
                    ArrayList<VehicleDTO> lightRailDTODTOList = dao.getAll();
                    ICurrencyStrategy mode = new USDToCAD();
                    builder.addMode(mode);
                    builder.addLightRailDTO(lightRailDTODTOList);
                    
                
                }
                
                
                if (isBusselected){
                    VehicleDAO dao = new VehicleDAO();
                    ArrayList<VehicleDTO> vehicleDTOList = dao.getAll();
                    builder.addBusDTOList(vehicleDTOList);
                }
                
                
                
                repo = builder.build();
                
                List<String> htmlLines = repo.toHTML();
                for (String line : htmlLines) {
                    out.println(line);
                }
                
                
                
//            if (costMode == "USDToCAD"){
//                ICurrencyStrategy currencyStrategy = new CADToUSD();
//                
//                builder.addCost(10, 1);
//                builder.addLocationTracking("I'm here.");
//                repo = builder.build();
//                
//                //double cost = repo.getCost();
//                out.println("<p> cost: "+ repo.getCost() +" </p>");
//                out.println("<p> cost: "+ repo.getLocationTracking()+" </p>");
//                } else{
//                    builder.addCost(3000, 1);
//                    builder.addEnergyConsumption(5);
//                    repo = builder.build();
//                    out.println("<p> cost: "+ repo.getCost() + "</p>");
//                    out.println("<p> energy "+ repo.getEnergyConsumption() + "</p>");
//                }
            
            %>

    </body>
</html>
