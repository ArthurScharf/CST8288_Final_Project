/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package viewlayer;

import businesslayer.VehicleService;
import dataaccesslayer.VehicleDAO;
import java.io.IOException;
import java.sql.SQLException;
import java.time.Instant;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import transportobjects.VehicleDTO;

/**
 *
 * @author Arthur Scharf
 */
public class VehicleController extends HttpServlet 
{
    /**
     * Handles requests for vehicle queries
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        // -- Dummy distance change code for vehicles changes distances -- //
        ServletContext context = request.getServletContext();
        try {
            VehicleService.increaseDistanceTraveled(
                    (ArrayList<VehicleDTO>) context.getAttribute("activeVehicles"),  
                    (Instant) context.getAttribute("lastInstant")
            );
            // -- TEST: Creating test notification -- //
            // Observer fuelObserver = (Observer)context.getAttribute("fuelEvent");
            // fuelObserver.update(NotificationType.MAINTENANCE, "V002|Axel Broken"); // Creates a notificaton in the database
        } catch (Exception e)
        {
            context.setAttribute("errorMessage", e.getMessage());
            response.sendRedirect("error.jsp");
            return;
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** Retrieves vehicles from the database
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException 
    {
        processRequest(request, response);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/vehicle.jsp");
        dispatcher.forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        processRequest(request, response);
        
        // -- Creating new vehicle -- //
        String vehicleNumber = request.getParameter("vehicleNumber");
        String vehicleType   = request.getParameter("vehicleType");
        int    maxPassengers = Integer.parseInt(request.getParameter("maxPassengers"));
        
        VehicleDTO vcl = new VehicleDTO();
        vcl.setVehicleNumber(vehicleNumber);
        vcl.setType(vehicleType);
        vcl.setMaximumPassengers(maxPassengers);
        
        VehicleDAO dao = new VehicleDAO();
        
        try {
            dao.create(vcl);
        } catch (SQLException e)
        {
            request.getServletContext().setAttribute("errorMessage", "Exception VehicleController::doGet -- " + e.getMessage());
            response.sendRedirect("error");
            return;
        }
        
        response.sendRedirect("home");
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
