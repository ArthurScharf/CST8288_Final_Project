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
                
                
                //PROJECT OBJECT
                boolean isBusselected = false;
                boolean isDieselElectric = false;
                boolean isLightRail = false;
                
                
                for (String section: selectedSections){
                    if ("bus".equals(section)){
                        isBusselected = true;
                    } else if ("dieselElectric".equals(section)){
                        isDieselElectric = true;
                    } else if ("lightRail".equals(section)){
                        isLightRail = true;
                }
                }
 
                
                
                if (isDieselElectric){
                    VehicleDAO dao = new VehicleDAO();
                    ArrayList<VehicleDTO> dieselElectricDTODTOList = dao.getAll();
                    builder.addDeiselElectricDTOList(dieselElectricDTODTOList);
                    ICurrencyStrategy mode = null;
                    switch(costMode){
                    case "USDToCAD": mode =new USDToCAD() ;
                    break;
                    case "CADToUSD": mode = new CADToUSD();
                    break;              
                    default: mode = new USDToCAD();
                    }
                    builder.addMode(mode);
                
                
                }
                
                if (isLightRail){   
                    VehicleDAO dao = new VehicleDAO();
                    ArrayList<VehicleDTO> lightRailDTODTOList = dao.getAll();
                    ICurrencyStrategy mode = null;
                    switch(costMode){
                    case "USDToCAD": mode =new USDToCAD() ;
                    break;
                    case "CADToUSD": mode = new CADToUSD();
                    break;              
                    default: mode = new USDToCAD();
                    }
                    builder.addMode(mode);
                    builder.addLightRailDTO(lightRailDTODTOList);
                    
                
                }
                
                
                if (isBusselected){
                    VehicleDAO dao = new VehicleDAO();
                    ArrayList<VehicleDTO> vehicleDTOList = dao.getAll();
                    ICurrencyStrategy mode = null;
                    switch(costMode){
                    case "USDToCAD": mode =new USDToCAD() ;
                    break;
                    case "CADToUSD": mode = new CADToUSD();
                    break;              
                    default: mode = new USDToCAD();
                    }
                    builder.addMode(mode);
                    builder.addBusDTOList(vehicleDTOList);
                }
                
                
                
                repo = builder.build();
                
                List<String> htmlLines = repo.toHTML();
                for (String line : htmlLines) {
                    out.println(line);
                }
                
                
                

            
            %>

    </body>
</html>
