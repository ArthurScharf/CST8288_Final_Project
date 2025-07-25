/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/ServletListener.java to edit this template
 */
package viewlayer;

import java.util.ArrayList;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Web application lifecycle listener.
 *
 * @author User
 */
public class ObserverContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) 
    {
        ArrayList<String> testAttribute = new ArrayList<>();
        testAttribute.add("Hello World");
        testAttribute.add("Goodbye Moon");
        testAttribute.add("Ugh");
        sce.getServletContext().setAttribute("testAttribute", testAttribute);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) 
    {
        
    }
}
