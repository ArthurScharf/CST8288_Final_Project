/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package viewlayer;

import dataaccesslayer.BreakDAO;
import dataaccesslayer.NotificationDAO;
import transportobjects.BreakDTO;
import transportobjects.NotificationDTO;
import transportobjects.NotificationType;
import java.util.ArrayList;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Handles operator break management operations
 * 
 * @author Benjamin Gurth
 */
public class BreakController extends HttpServlet {

    /**
     * Displays break management page
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("id") == null) {
            response.sendRedirect("loginView");
            return;
        }
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/break.jsp");
        dispatcher.forward(request, response);
    }

    /**
     * Processes break actions
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("id") == null) {
            response.sendRedirect("loginView");
            return;
        }
        
        String action = request.getParameter("action");
        BreakDAO breakDAO = new BreakDAO();
        
        try {
            if ("start".equals(action)) {
                handleStartBreak(request, session, breakDAO);
            } else if ("end".equals(action)) {
                handleEndBreak(request, breakDAO);
            } else if ("handleBreakNotification".equals(action)) {
                handleManagerBreakNotification(request, session);
            } else if ("endFromNotification".equals(action)) {
                handleOperatorEndFromNotification(request, session, breakDAO);
            } else {
                request.getServletContext().setAttribute("errorMessage", "Invalid break action specified");
                response.sendRedirect("error.jsp");
                return;
            }
        } catch (Exception e) {
            request.getServletContext().setAttribute("errorMessage", "Break operation failed: " + e.getMessage());
            response.sendRedirect("error.jsp");
            return;
        }
        
        String role = (String) session.getAttribute("role");
        if ("handleBreakNotification".equals(action) && "Manager".equals(role)) {
            response.sendRedirect("home");
        } else {
            response.sendRedirect("BreakController");
        }
    }
    
    /**
     * Starts new break for operator
     */
    private void handleStartBreak(HttpServletRequest request, HttpSession session, BreakDAO breakDAO) 
            throws Exception {
        
        int operatorId = (Integer) session.getAttribute("id");
        String breakType = request.getParameter("breakType");
        
        if (breakType == null || breakType.trim().isEmpty()) {
            breakType = "REST";
        }
        
        BreakDTO newBreak = new BreakDTO();
        newBreak.setOperatorId(operatorId);
        newBreak.setBreakType(breakType.toUpperCase());
        newBreak.setStatus("ACTIVE");
        
        breakDAO.create(newBreak);
        
        createBreakNotification(session, breakType);
    }
    
    /**
     * Ends active break
     */
    private void handleEndBreak(HttpServletRequest request, BreakDAO breakDAO) throws Exception {
        String breakIdStr = request.getParameter("breakId");
        if (breakIdStr == null || breakIdStr.trim().isEmpty()) {
            throw new IllegalArgumentException("Break ID is required to end break");
        }
        
        int breakId = Integer.parseInt(breakIdStr);
        
        boolean success = breakDAO.endBreak(breakId);
        if (!success) {
            throw new RuntimeException("Failed to end break");
        }
    }
    
    /**
     * Handles when manager responds to a break notification
     */
    private void handleManagerBreakNotification(HttpServletRequest request, HttpSession session) throws Exception {
        String notificationIdParam = request.getParameter("notificationId");
        int notificationId = Integer.parseInt(notificationIdParam);
        
        NotificationDAO notificationDAO = new NotificationDAO();
        NotificationDTO originalNotification = notificationDAO.getById(notificationId);
        
        if (originalNotification != null) {
            String operatorName = extractOperatorNameFromBreakData(originalNotification.getData());
            
            NotificationDTO operatorNotification = new NotificationDTO();
            operatorNotification.setType(NotificationType.BREAK);
            operatorNotification.setData("Manager requests you end your break - Operator: " + operatorName);
            notificationDAO.create(operatorNotification);
            
            notificationDAO.delete(notificationId);
        }
    }
    
    /**
     * Handles when operator ends break from manager notification
     */
    private void handleOperatorEndFromNotification(HttpServletRequest request, HttpSession session, BreakDAO breakDAO) throws Exception {
        String notificationIdParam = request.getParameter("notificationId");
        int notificationId = Integer.parseInt(notificationIdParam);
        Integer operatorId = (Integer) session.getAttribute("id");
        
        if (operatorId != null) {
            ArrayList<BreakDTO> operatorBreaks = breakDAO.getByOperatorId(operatorId);
            if (operatorBreaks != null) {
                for (BreakDTO breakDto : operatorBreaks) {
                    if (breakDto.isActive()) {
                        breakDAO.endBreak(breakDto.getBreakId());
                        break;
                    }
                }
            }
            
            NotificationDAO notificationDAO = new NotificationDAO();
            notificationDAO.delete(notificationId);
        }
    }
    
    /**
     * Creates notification for managers
     */
    private void createBreakNotification(HttpSession session, String breakType) {
        try {
            NotificationDAO notificationDAO = new NotificationDAO();
            NotificationDTO notification = new NotificationDTO();
            notification.setType(NotificationType.BREAK);
            
            String operatorName = session.getAttribute("firstName") + " " + session.getAttribute("lastName");
            notification.setData(String.format("Operator %s started a %s break", operatorName, breakType.toLowerCase()));
            
            notificationDAO.create(notification);
        } catch (Exception e) {
            System.err.println("Failed to create break notification: " + e.getMessage());
        }
    }
    
    /**
     * Helper method to extract operator name from break notification data
     */
    private String extractOperatorNameFromBreakData(String data) {
        try {
            if (data.startsWith("Operator ") && data.contains(" started")) {
                int startIndex = "Operator ".length();
                int endIndex = data.indexOf(" started");
                return data.substring(startIndex, endIndex);
            }
        } catch (Exception e) {
            System.err.println("Error extracting operator name from: " + data);
        }
        return "Unknown Operator";
    }

    @Override
    public String getServletInfo() {
        return "BreakController handles operator break management";
    }
}