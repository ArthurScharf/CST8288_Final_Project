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
import transportobjects.VehicleDTO;

/**
 * Business logic relating to vehicles.
 * 
 * NOTE: The code for dummy changes in the vehicles position over time is stored here
 * 
 * @author Arthur Scharf
 */
public class VehicleServices 
{
    public static void increaseDistanceTraveled(ArrayList<VehicleDTO> vcls, Instant lastInstant) throws Exception
    {
        // -- Calculating delta time -- //
        Instant now = Instant.now();
        Duration deltaTime = Duration.between(lastInstant, now);
        
        float deltaSeconds = (float)deltaTime.getSeconds();
        VehicleDAO dao = new VehicleDAO();

        try {
            // Hardcoded vehicle speed for testing
            // TODO: Implement vehicle speed specific distance changes
            for (VehicleDTO v : vcls)
            {
                v.setTotalDistanceTraveled((float) (v.getTotalDistanceTraveled() + (deltaSeconds * 13.0))); // Buses are 50km/h speeds
                // -- Updating Vehicles in Database -- //
                /*
                    Calling this query many times seems terrible inefficient. For now, though, it works
                */
                dao.update(v);
            }
        } catch (SQLException e)
        {
            throw new Exception("SQLException in VehicleServices::increaseDistanceTraveled __ while updating vehicle\n" + e.getMessage(), e);
        }
    }//~ increaseDistanceTraveled(...)
}
