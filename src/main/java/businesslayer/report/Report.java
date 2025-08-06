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
    
    //Fileds are subjected to be chanegd based in the project proccess
    private String locationTracking;
    private int energyConsumption;
    private List<String> transitMaintenance;
    private HashMap<String, String> operatorPerformance;
    private double cost;
    private ICurrencyStrategy currencyStrategy;
    
    
    //PROJECT OBJECTS
    private ArrayList<VehicleDTO> vehicleDTOList;
    private final int BATTERY_PRICE = 7;
    private final int CNG_PRICE = 8;
    private final int DIESEL_PRICE = 10;
    
    private ArrayList<VehicleDTO> busDTOList;
    private ArrayList<VehicleDTO> deiselElectricDTOList;
    private ArrayList<VehicleDTO> lightRailDTO;

            
    public Report(String locationTracking, int energyConsumption,
            List<String> transitMaintenance, HashMap<String, String> operatorPerformance,
            double cost, ICurrencyStrategy currencyStrategy,
              ArrayList<VehicleDTO> busDTOList,
            ArrayList<VehicleDTO> deiselElectricDTOList,
            ArrayList<VehicleDTO> lightRailDTO){
        this.locationTracking = locationTracking;
        this.energyConsumption = energyConsumption;
        this.transitMaintenance = transitMaintenance;
        this.operatorPerformance = operatorPerformance;
        this.cost = cost;
        this.currencyStrategy = currencyStrategy;
        
        
        this.vehicleDTOList = vehicleDTOList;
        this.busDTOList = busDTOList;
        this.deiselElectricDTOList = deiselElectricDTOList;
        this.lightRailDTO = lightRailDTO;
    }

    public String getLocationTracking() {
        return locationTracking;
    }

    public int getEnergyConsumption() {
        return energyConsumption;
    }

    public List<String> getTransitMaintenance() {
        return transitMaintenance;
    }

    public HashMap<String, String> getOperatorPerformance() {
        return operatorPerformance;
    }

    public double getCost() {
        return cost;
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


    
    
    public List<String> toHTML()
    {
        List<String> htmlElements = new ArrayList<>();

        if (locationTracking != null) {
            htmlElements.add("<p><strong>Location Tracking:</strong> " + locationTracking + "</p>");
        }

        if (energyConsumption > 0) {
            htmlElements.add("<p><strong>Energy Consumption:</strong> " + energyConsumption + " kWh</p>");
        }

        if (transitMaintenance != null && !transitMaintenance.isEmpty()) {
            htmlElements.add("<p><strong>Transit Maintenance:</strong></p><ul>");
            for (String item : transitMaintenance) {
                htmlElements.add("<li>" + item + "</li>");
            }
            htmlElements.add("</ul>");
        }

        if (operatorPerformance != null && !operatorPerformance.isEmpty()) {
            htmlElements.add("<p><strong>Operator Performance:</strong></p><ul>");
            for (String key : operatorPerformance.keySet()) {
                htmlElements.add("<li>" + key + ": " + operatorPerformance.get(key) + "</li>");
            }
            htmlElements.add("</ul>");
        }

        if (cost > 0) {
        String label = "Unspecified";
        if (currencyStrategy != null) {
            label = currencyStrategy.getLabel();
        }
        htmlElements.add("<p><strong>Cost:</strong> " + cost + " " + label + "</p>");
        }

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

//        if (vehicleDTOList != null && !vehicleDTOList.isEmpty()){
//            //TO DO: Implement a method to defferntiate different types of vehicle
//           for (VehicleDTO vehicleDTO : vehicleDTOList){
//               if (vehicleDTO instanceof LightRailDTO lightRailDTO){
//                   float remainBatt = ( lightRailDTO.getBatteryAmount() / lightRailDTO.getMaxBattery()) * 100;
//                   float costToRecharge = ((lightRailDTO.getMaxBattery() - lightRailDTO.getBatteryAmount()) * BATTERY_PRICE) / 100;
//                   
//                   htmlElements.add("<p><strong>Light Rail Number:</strong> " + lightRailDTO.getVehicleNumber() + " &nbsp; | &nbsp; " +
//                 "<strong>Remaining Battery:</strong> " + remainBatt + "% &nbsp; | &nbsp; " +
//                 "<strong>Cost to recharge:</strong> " + costToRecharge + "$</p>");
//
//               } else {
//                   System.out.println("\nOOOPS\n");
//               }
//           }
//           for (VehicleDTO vehicleDTO : vehicleDTOList){
//               if (vehicleDTO instanceof BusDTO busDTO){
//                  float remainFuel = busDTO.getResourceAmount() / busDTO.getMaxResource() * 100;
//                  float costToRefuel = 0;
//                  if (busDTO.getResourceType().equalsIgnoreCase("Diesel")){
//                      costToRefuel = remainFuel * DIESEL_PRICE / 100;
//                  } else if (busDTO.getResourceType().equalsIgnoreCase("CNG") ){
//                      costToRefuel = remainFuel * CNG_PRICE / 100;
//                  }
//                   
//                  
//                   htmlElements.add("<p><strong>Bus Number:</strong> " + busDTO.getVehicleNumber() + " &nbsp; | &nbsp; " +
//                 "<strong>Bus Type:</strong> " + busDTO.getResourceType() + " &nbsp; | &nbsp; " +
//                 "<strong>Bus Remaining Fuel:</strong> " + remainFuel + "% &nbsp; | &nbsp; " +
//                 "<strong>Cost to Refuel:</strong> " + costToRefuel + "$</p>");
//
//               } else {
//                   System.out.println("\nOOOPS\n");
//               }
//           }
//           for (VehicleDTO vehicleDTO : vehicleDTOList){
//               if (vehicleDTO instanceof DieselElectricDTO dieselElectricDTO){
//                  
//                float remainBattery = (dieselElectricDTO.getBatteryAmount() / dieselElectricDTO.getMaxBattery()) * 100;
//                float remainFuel = (dieselElectricDTO.getFuelAmount() / dieselElectricDTO.getMaxFuel()) * 100;
//
//                
//                float costToRefuel = (remainFuel * DIESEL_PRICE + remainBattery * BATTERY_PRICE) / 100; 
//
//                htmlElements.add("<p><strong>Diesel-Electric Number:</strong> " + dieselElectricDTO.getVehicleNumber() + " &nbsp; | &nbsp; " +
//                                 "<strong>Battery Remaining:</strong> " + String.format("%.2f", remainBattery) + "% &nbsp; | &nbsp; " +
//                                 "<strong>Fuel Remaining:</strong> " + String.format("%.2f", remainFuel) + "% &nbsp; | &nbsp; " +
//                                 "<strong>Cost to Refuel:</strong> " + String.format("%.2f", costToRefuel) + "$</p>");
//               } else {
//                   System.out.println("\nOOOPS\n");
//               }
//           }
//
//        }
        
        if (busDTOList != null && !busDTOList.isEmpty()){
           for (VehicleDTO vehicleDTO : vehicleDTOList){
               if (vehicleDTO instanceof BusDTO busDTO){
//                   String type = busDTO.serialize().substring(0, 3);
//                   if (type.equalsIgnoreCase("Bus")){
                       float remainFuel = busDTO.getResourceAmount() / busDTO.getMaxResource() * 100;
                       float costToRefuel = 0;
                        if (busDTO.getResourceType().equalsIgnoreCase("Diesel")){
                            costToRefuel = remainFuel * DIESEL_PRICE / 100;
                        } else if (busDTO.getResourceType().equalsIgnoreCase("CNG") ){
                            costToRefuel = remainFuel * CNG_PRICE / 100;
                        }


                         htmlElements.add("<p><strong>Bus Number:</strong> " + busDTO.getVehicleNumber() + " &nbsp; | &nbsp; " +
                       "<strong>Bus Type:</strong> " + busDTO.getResourceType() + " &nbsp; | &nbsp; " +
                       "<strong>Bus Remaining Fuel:</strong> " + remainFuel + "% &nbsp; | &nbsp; " +
                       "<strong>Cost to Refuel:</strong> " + costToRefuel + "$</p>");

                     } else {
                         System.out.println("\nOOOPS\n");
                     }
                       
//                   }
                  
           }
        }
        
        if (deiselElectricDTOList != null && !deiselElectricDTOList.isEmpty()){
            for (VehicleDTO vehicleDTO : vehicleDTOList){
               if (vehicleDTO instanceof DieselElectricDTO dieselElectricDTO){
                  
                float remainBattery = (dieselElectricDTO.getBatteryAmount() / dieselElectricDTO.getMaxBattery()) * 100;
                float remainFuel = (dieselElectricDTO.getFuelAmount() / dieselElectricDTO.getMaxFuel()) * 100;

                
                float costToRefuel = (remainFuel * DIESEL_PRICE + remainBattery * BATTERY_PRICE) / 100; 

                htmlElements.add("<p><strong>Diesel-Electric Number:</strong> " + dieselElectricDTO.getVehicleNumber() + " &nbsp; | &nbsp; " +
                                 "<strong>Battery Remaining:</strong> " + String.format("%.2f", remainBattery) + "% &nbsp; | &nbsp; " +
                                 "<strong>Fuel Remaining:</strong> " + String.format("%.2f", remainFuel) + "% &nbsp; | &nbsp; " +
                                 "<strong>Cost to Refuel:</strong> " + String.format("%.2f", costToRefuel) + "$</p>");
               } else {
                   System.out.println("\nOOOPS\n");
               }
           }
        }
        
        if (lightRailDTO != null && !lightRailDTO.isEmpty()){
            for (VehicleDTO vehicleDTO : vehicleDTOList){
               if (vehicleDTO instanceof LightRailDTO lightRailDTO){
                   float remainBatt = ( lightRailDTO.getBatteryAmount() / lightRailDTO.getMaxBattery()) * 100;
                   float costToRecharge = ((lightRailDTO.getMaxBattery() - lightRailDTO.getBatteryAmount()) * BATTERY_PRICE) / 100;
                   
                   htmlElements.add("<p><strong>Light Rail Number:</strong> " + lightRailDTO.getVehicleNumber() + " &nbsp; | &nbsp; " +
                 "<strong>Remaining Battery:</strong> " + remainBatt + "% &nbsp; | &nbsp; " +
                 "<strong>Cost to recharge:</strong> " + costToRecharge + "$</p>");

               } else {
                   System.out.println("\nOOOPS\n");
               }
           }
        }

        return htmlElements;

    }
    
    
    //bulds a new object of ReportBuilder based on the passed parameters form the constructor
    public static ReportBuilder builder (){
        
        return new ReportBuilder();
        
    }
}
