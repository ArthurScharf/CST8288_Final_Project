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

/**
 * Web application lifecycle listener.
 *
 * @author Arthur Scharf
 */
public class InitRAMContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) 
    {
        // -- Test Data -- //
        ArrayList<String> testAttribute = new ArrayList<>();
        testAttribute.add("Hello World");
        testAttribute.add("Goodbye Moon");
        testAttribute.add("Ugh");
        sce.getServletContext().setAttribute("testAttribute", testAttribute);
        
        // -- Vehicles on the road -- //
        
        VehicleDAO dao = new VehicleDAO();
        try {
            ArrayList<VehicleDTO> activeVehicles = dao.getAll();
            sce.getServletContext().setAttribute("activeVehicles", activeVehicles);
        } catch (SQLException e)
        {
            // TODO: Handle this better? Use exceptions that crash the program gracefully?
            e.printStackTrace();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) 
    {
        // TODO: Final update should be sent to database upon shutdown
    }
}
