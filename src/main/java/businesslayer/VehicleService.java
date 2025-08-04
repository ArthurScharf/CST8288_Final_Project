/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package businesslayer;

import dataaccesslayer.VehicleDAO;
import java.sql.SQLException;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import transportobjects.BusDTO;
import transportobjects.DieselElectricDTO;
import transportobjects.LightRailDTO;
import transportobjects.VehicleDTO;

/**
 * Business logic relating to vehicles.
 * 
 * NOTE: The code for dummy changes in the vehicles position over time is stored here
 * 
 * @author Arthur Scharf
 */
public class VehicleService 
{
    /**
     * Helper function which simulates the updates vehicles would send as their distance traveled changes.
     * In a real system, this would be a Servlet of it's own, receiving updates live and 
     * processing them
     * 
     * @param vcls
     * @param lastInstant
     * @throws Exception 
     */
    public static void increaseDistanceTraveled(ArrayList<VehicleDTO> vcls, Instant lastInstant) throws Exception
    {
        // -- Calculating delta time -- //
        Instant now = Instant.now();
        Duration deltaTime = Duration.between(lastInstant, now);
        
        float deltaSeconds = (float)deltaTime.getSeconds();
        VehicleDAO dao = new VehicleDAO();

//        try {
            // Hardcoded vehicle speed for testing
            // TODO: Implement vehicle speed specific distance changes
            for (VehicleDTO v : vcls)
            {
                /*
                    Check for overridden limitations
                */
                v.setTotalDistanceTraveled((float) (v.getTotalDistanceTraveled() + (deltaSeconds * 13.0))); // Buses are 50km/h speeds
                // -- Updating Vehicles in Database -- //
                /*
                    Calling this query many times seems terrible inefficient. For now, though, it works
                */
                // dao.update(v, );
            }
//        } 
//        catch (SQLException e)
//        {
//            throw new Exception("SQLException in VehicleServices::increaseDistanceTraveled __ while updating vehicle\n" + e.getMessage(), e);
//        }
    }//~ increaseDistanceTraveled(...)
    
    
    
    
    public static VehicleDTO createVehicle(String vehicleNumber, int maxPassengers, int routeID, float totalDistanceTraveled, String typeInfo)
        throws Exception
    {
        // "type|resourceType|resourceAmount|maxResource"
        String[] typeParams = typeInfo.split("\\|");
        
        // TODO: What happens when no delimiter exists?
        if (typeParams.length == 0 || typeParams[0] == null)
        {
            throw new Exception("VehicleService::createVehicle--Invalid type info passed");
        }
        
        switch(typeParams[0])
        {
            case("Bus") -> {
                BusDTO dto = new BusDTO();
                initVehicle(dto, vehicleNumber, maxPassengers, routeID, totalDistanceTraveled);
                dto.setResourceType(typeParams[1]);
                try
                {
                    dto.setResourceAmount(Float.parseFloat(typeParams[2]));
                    dto.setMaxResource(Float.parseFloat(typeParams[3]));
                }
                catch (Exception e)
                {
                    throw new Exception("VehicleService::createVehicle--Failed to parse Bus Resources" + e.getMessage());
                }
                return dto;
            }
            
//          private float batteryAmount;
//          private float maxBattery;
            case("LightRail") -> {
                LightRailDTO dto = new LightRailDTO();
                initVehicle(dto, vehicleNumber, maxPassengers, routeID, totalDistanceTraveled);
                try
                {
                    dto.setBatteryAmount(Float.parseFloat(typeParams[1]));
                    dto.setMaxBattery(Float.parseFloat(typeParams[2]));
                }
                catch (Exception e)
                {
                    throw new Exception("VehicleService::createVehicle--Failed to parse Light Rail Battery" + e.getMessage());
                }
                return dto;
            }
            
            case("DieselElectric") -> {
                DieselElectricDTO dto = new DieselElectricDTO();
                initVehicle(dto, vehicleNumber, maxPassengers, routeID, totalDistanceTraveled);
                try
                {
                    dto.setBatteryAmount(Float.parseFloat(typeParams[1]));
                    dto.setMaximumBattery(Float.parseFloat(typeParams[2]));
                    dto.setFuelAmount(Float.parseFloat(typeParams[3]));
                    dto.setMaxFuel(Float.parseFloat(typeParams[4]));
                }
                catch (Exception e)
                {
                    throw new Exception("VehicleService::createVehicle--Failed to parse Diesel Electric Train" + e.getMessage());
                }
                return dto;
            }
        }
        return null;
    }//~ createCreateVehicle(...)
    
    private static void initVehicle(VehicleDTO dto, String vehicleNumber, int maxPassengers, int routeID, float totalDistanceTraveled)
    {
        dto.setVehicleNumber(vehicleNumber);
        dto.setMaximumPassengers(maxPassengers);
        dto.setRouteID(routeID);
        dto.setTotalDistanceTraveled(totalDistanceTraveled);
    }   
}
