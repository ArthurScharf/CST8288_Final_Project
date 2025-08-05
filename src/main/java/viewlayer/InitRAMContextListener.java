/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/ServletListener.java to edit this template
 */
package viewlayer;

import dataaccesslayer.VehicleDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import transportobjects.VehicleDTO;
import java.time.Instant;
import javax.servlet.ServletContext;
import observer.FuelObserver;
import observer.MaintenanceObserver;


/**
 * Web application lifecycle listener.
 *
 * ---- List of important variables stored in RAM ----
 * 
 * 
 * @author Arthur Scharf
 */
public class InitRAMContextListener implements ServletContextListener
{

    
    @Override
    public void contextInitialized(ServletContextEvent sce) 
    {
        ServletContext context = sce.getServletContext();
        
        // -- Test Data -- //
        ArrayList<String> testAttribute = new ArrayList<>();
        testAttribute.add("Hello World");
        testAttribute.add("Goodbye Moon");
        testAttribute.add("Ugh");
        context.setAttribute("testAttribute", testAttribute);
        
        // -- Time tracking variable for generating dummy changes in vehicle distance traveled -- //
        context.setAttribute("lastInstant", Instant.now());
        
        // -- Event Notification Observers -- //
//        context.setAttribute("maintenanceEvent", new Observer());
//        context.setAttribute("fuelEvent", new Observer());
//        context.setAttribute("breakEvent", new Observer());
        
        // -- Vehicles on the road -- //
        VehicleDAO dao = new VehicleDAO();
        try {
            // -- Initializing Vehicles on the road with proper observers -- //
            ArrayList<VehicleDTO> activeVehicles = dao.getAll();
            System.out.println(activeVehicles.size());
            for (VehicleDTO vcl : activeVehicles)
            {
                // Visitor pattern in action
                vcl.registerFuelObserver(new FuelObserver(vcl)); 
                vcl.registerMaintainenceObserver(new MaintenanceObserver(vcl));
            }
            sce.getServletContext().setAttribute("activeVehicles", activeVehicles);
        } catch (SQLException e)
        {
            // TODO: Handle this better? Use exceptions that crash the program gracefully?
            e.printStackTrace();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) 
    {
        // TODO: Final update should be sent to database upon shutdown
    }
}
