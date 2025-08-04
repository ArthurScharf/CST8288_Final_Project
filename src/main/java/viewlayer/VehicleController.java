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
import viewlayer.command.UndoCreateVehicle;
import viewlayer.command.UndoException;

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
     
        /* -- NOTE --
        The inclusion of the handling of different types of POST remains a code-smell
        unless proper control structures are implemented to clean up the code.
        At the moment of writing, the only functions for post are create vehicle,
        and undo the last vehicle created. For this reason, I've left it hard coded
            - Arthur Scharf
        */
        
        // -- Do Undo Vehicle Creation if needed -- //
        String pathInfo = request.getPathInfo();
        if (pathInfo != null && pathInfo.split("/")[1].equals("undo"))
        {
            try {
                Object obj = request.getSession(false).getAttribute("UndoCreateVehicle");
                if (obj == null){
                    request.getServletContext().getNamedDispatcher("VehicleController").forward(request, response);
                    return;
                }
                // -- Undo Vehicle Creation -- //
                UndoCreateVehicle undo = (UndoCreateVehicle)obj;
                undo.execute();
                request.getServletContext().getNamedDispatcher("VehicleController").forward(request, response);
                return;
            } catch (ClassCastException | UndoException e) {
                request.getServletContext().setAttribute("errorMessage", e.getMessage());
                response.sendRedirect("error");
                return;
            }
        }
        
        // -- Creating new vehicle -- //
        String vehicleNumber = request.getParameter("vehicleNumber");
        // int    maxPassengers = Integer.parseInt(request.getParameter("maxPassengers"));
        
        VehicleDTO vcl = new VehicleDTO();
        vcl.setVehicleNumber(vehicleNumber);
        // vcl.setMaximumPassengers(maxPassengers);
        
    
        // -- Creating Record in Database -- //
        VehicleDAO dao = new VehicleDAO();
        
        try {
            dao.create(vcl);
        } catch (SQLException e)
        {
            request.getServletContext().setAttribute("errorMessage", "Exception VehicleController::doGet -- " + e.getMessage());
            request.getServletContext().getNamedDispatcher("error").forward(request, response);
            return;
        }
        
        // -- Creating undo command -- //
        // TODO: Prevent this controller serving if user isn't logged in
        UndoCreateVehicle undo = new UndoCreateVehicle(vcl);
        request.getSession(false).setAttribute("UndoCreateVehicle", undo);
        
        request.getServletContext().getNamedDispatcher("home").forward(request, response);
        return;
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
