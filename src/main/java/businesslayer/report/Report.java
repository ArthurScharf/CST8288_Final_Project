/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package businesslayer.report;

import dataaccesslayer.VehicleDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import transportobjects.BusDTO;
import transportobjects.DieselElectricDTO;
import transportobjects.LightRailDTO;
import transportobjects.VehicleDTO;

/**
 *
 * @author Sina Paslar
 */
public class Report {
    
    
    //PROJECT OBJECTS
    private ArrayList<VehicleDTO> vehicleDTOList;
    private final int BATTERY_PRICE = 7;
    private final int CNG_PRICE = 8;
    private final int DIESEL_PRICE = 10;
    
    private ArrayList<VehicleDTO> busDTOList;
    private ArrayList<VehicleDTO> deiselElectricDTOList;
    private ArrayList<VehicleDTO> lightRailDTO;
    
   

    
    
    //STrategy Currency object
    
    
    ICurrencyStrategy mode;
    
    
    
    
    
    
    
            
    public Report( ArrayList<VehicleDTO> busDTOList,
            ArrayList<VehicleDTO> deiselElectricDTOList,
            ArrayList<VehicleDTO> lightRailDTO,
            ICurrencyStrategy mode){
                this.vehicleDTOList = vehicleDTOList;
                this.busDTOList = busDTOList;
                this.deiselElectricDTOList = deiselElectricDTOList;
                this.lightRailDTO = lightRailDTO;
                this.mode = mode;
            }

  
    
    
    
    //PROJECT OBJECT
    public ArrayList<VehicleDTO> getVehicleDTOList(){
        return vehicleDTOList;

    }
    
    public ArrayList<VehicleDTO> getBusDTOList() {
    return busDTOList;
    }

    public ArrayList<VehicleDTO> getDeiselElectricDTOList() {
        return deiselElectricDTOList;
    }

    public ArrayList<VehicleDTO> getLightRailDTO() {
        return lightRailDTO;
    }

    public ICurrencyStrategy getMode(){
        return mode;
    }

    
    
    
    public List<String> toHTML()
    {
        
        float sum = 0;
        List<String> htmlElements = new ArrayList<>();

        VehicleDAO dao = new VehicleDAO();
        try {
            vehicleDTOList = dao.getAll();
        } catch (SQLException e)
        {
            System.out.println("SQLException");
            return null;
            
        } catch (Exception e)
        {
            System.out.println("Exception");
            return null;
        }


        
        if (busDTOList != null && !busDTOList.isEmpty()) {
            StringBuilder busTable = new StringBuilder();
            float totalBusCost = 0;

            busTable.append("<table border='1' cellpadding='5' cellspacing='0'>");
            busTable.append("<tr><th colspan='4'>Bus Report</th></tr>");
            busTable.append("<tr>")
                    .append("<th>Bus Number</th>")
                    .append("<th>Bus Type</th>")
                    .append("<th>Remaining Fuel (%)</th>")
                    .append("<th>Cost to Refuel ($)</th>")
                    .append("</tr>");

            for (VehicleDTO vehicleDTO : vehicleDTOList) {
                if (vehicleDTO instanceof BusDTO busDTO) {
                    float remainFuel = busDTO.getResourceAmount() / busDTO.getMaxResource() * 100;
                    float costToRefuel = 0;

                    if (busDTO.getResourceType().equalsIgnoreCase("Diesel")) {
                        costToRefuel = remainFuel * DIESEL_PRICE / 100;
                    } else if (busDTO.getResourceType().equalsIgnoreCase("CNG")) {
                        costToRefuel = remainFuel * CNG_PRICE / 100;
                    }

                    CurrecnyStrategyContext currencyContext = new CurrecnyStrategyContext(mode);
                    costToRefuel = (float) currencyContext.currencyConvertor(costToRefuel);
                    totalBusCost += costToRefuel;

                    busTable.append("<tr>")
                            .append("<td>").append(busDTO.getVehicleNumber()).append("</td>")
                            .append("<td>").append(busDTO.getResourceType()).append("</td>")
                            .append("<td>").append(String.format("%.2f", remainFuel)).append("%</td>")
                            .append("<td>").append(String.format("%.2f", costToRefuel)).append("</td>")
                            .append("</tr>");
                }
        }

    busTable.append("<tr><td colspan='3'><strong>Total Refuel Cost</strong></td>")
            .append("<td><strong>").append(String.format("%.2f", totalBusCost)).append("</strong></td></tr>");

    busTable.append("</table><br>");
    htmlElements.add(busTable.toString());
}
        


        if (deiselElectricDTOList != null && !deiselElectricDTOList.isEmpty()) {
            StringBuilder tableBuilder = new StringBuilder();
            sum = 0;

            // Start single table
            tableBuilder.append("<table border='1' cellpadding='5' cellspacing='0'>");
            tableBuilder.append("<tr><th colspan='4'>Diesel-Electric Train Report</th></tr>");
            tableBuilder.append("<tr>")
                        .append("<th>Diesel-Electric Number</th>")
                        .append("<th>Battery Remaining (%)</th>")
                        .append("<th>Fuel Remaining (%)</th>")
                        .append("<th>Cost to Refuel ($)</th>")
                        .append("</tr>");

            for (VehicleDTO vehicleDTO : vehicleDTOList) {
                if (vehicleDTO instanceof DieselElectricDTO dieselElectricDTO) {
                    float remainBattery = (dieselElectricDTO.getBatteryAmount() / dieselElectricDTO.getMaxBattery()) * 100;
                    float remainFuel = (dieselElectricDTO.getFuelAmount() / dieselElectricDTO.getMaxFuel()) * 100;

                    float costToRefuel = (remainFuel * DIESEL_PRICE + remainBattery * BATTERY_PRICE) / 100;
                    sum += costToRefuel;

                    CurrecnyStrategyContext currencyContext = new CurrecnyStrategyContext(mode);
                    costToRefuel = (float) currencyContext.currencyConvertor(costToRefuel);

                    tableBuilder.append("<tr>")
                                .append("<td>").append(dieselElectricDTO.getVehicleNumber()).append("</td>")
                                .append("<td>").append(String.format("%.2f", remainBattery)).append("%</td>")
                                .append("<td>").append(String.format("%.2f", remainFuel)).append("%</td>")
                                .append("<td>").append(String.format("%.2f", costToRefuel)).append("</td>")
                                .append("</tr>");
                } else {
                    System.out.println("\nOOOPS\n");
                }
            }

            // Total row
            tableBuilder.append("<tr><td colspan='3'><strong>Total Refuel Cost</strong></td>")
                        .append("<td><strong>").append(String.format("%.2f", sum)).append("</strong></td></tr>");

            tableBuilder.append("</table><br>");
            htmlElements.add(tableBuilder.toString());
        }



    if (lightRailDTO != null && !lightRailDTO.isEmpty()) {
        StringBuilder lightRailTable = new StringBuilder();
        float totalRechargeCost = 0;

        lightRailTable.append("<table border='1' cellpadding='5' cellspacing='0'>");
        lightRailTable.append("<tr><th colspan='3'>Light Rail Report</th></tr>");
        lightRailTable.append("<tr>")
                      .append("<th>Light Rail Number</th>")
                      .append("<th>Battery Remaining (%)</th>")
                      .append("<th>Cost to Recharge ($)</th>")
                      .append("</tr>");

        for (VehicleDTO vehicleDTO : vehicleDTOList) {
            if (vehicleDTO instanceof LightRailDTO lightDTO) {
                float remainBattery = lightDTO.getBatteryAmount() / lightDTO.getMaxBattery() * 100;
                float costToRecharge = (lightDTO.getMaxBattery() - lightDTO.getBatteryAmount()) * BATTERY_PRICE / 100;

                CurrecnyStrategyContext currencyContext = new CurrecnyStrategyContext(mode);
                costToRecharge = (float) currencyContext.currencyConvertor(costToRecharge);
                totalRechargeCost += costToRecharge;

                lightRailTable.append("<tr>")
                              .append("<td>").append(lightDTO.getVehicleNumber()).append("</td>")
                              .append("<td>").append(String.format("%.2f", remainBattery)).append("%</td>")
                              .append("<td>").append(String.format("%.2f", costToRecharge)).append("</td>")
                              .append("</tr>");
            }
        }

        lightRailTable.append("<tr><td colspan='2'><strong>Total Recharge Cost</strong></td>")
                      .append("<td><strong>").append(String.format("%.2f", totalRechargeCost)).append("</strong></td></tr>");

        lightRailTable.append("</table><br>");
        htmlElements.add(lightRailTable.toString());
    }
        
        
        return htmlElements;

    }
    
    
  
    
    //bulds a new object of ReportBuilder based on the passed parameters form the constructor
    public static ReportBuilder builder (){
        
        return new ReportBuilder();
        
    }
}
