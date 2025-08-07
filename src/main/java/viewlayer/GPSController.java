/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package viewlayer;

import dataaccesslayer.StopsDAO;
import transportobjects.StopsDTO;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Handles GPS tracking reports for managers
 * 
 * @author Benjamin Gurth
 */
public class GPSController extends HttpServlet {

    /**
     * Displays GPS tracking page with stop selection
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("id") == null) {
            response.sendRedirect("loginView");
            return;
        }
        
        String role = (String) session.getAttribute("role");
        if (!"Manager".equals(role)) {
            response.sendRedirect("loginView");
            return;
        }
        
        try {
            StopsDAO stopsDAO = new StopsDAO();
            ArrayList<StopsDTO> stops = stopsDAO.getAll();
            request.setAttribute("stops", stops);
        } catch (Exception e) {
            request.getServletContext().setAttribute("errorMessage", "Error loading stops: " + e.getMessage());
            response.sendRedirect("error.jsp");
            return;
        }
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/gps.jsp");
        dispatcher.forward(request, response);
    }

    /**
     * Handles form submission for generating GPS reports
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("id") == null) {
            response.sendRedirect("loginView");
            return;
        }
        
        String role = (String) session.getAttribute("role");
        if (!"Manager".equals(role)) {
            response.sendRedirect("loginView");
            return;
        }
        
        String selectedStop = request.getParameter("stopName");
        if (selectedStop != null && !selectedStop.trim().isEmpty()) {
            request.setAttribute("selectedStop", selectedStop);
            
            try {
                StopsDAO stopsDAO = new StopsDAO();
                ArrayList<StopsDTO> stops = stopsDAO.getAll();
                request.setAttribute("stops", stops);
            } catch (Exception e) {
                request.getServletContext().setAttribute("errorMessage", "Error loading stops: " + e.getMessage());
                response.sendRedirect("error.jsp");
                return;
            }
        }
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/gps.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "GPSController handles GPS tracking reports for managers";
    }
}