package viewlayer;

import dataaccesslayer.MaintenanceDAO;
import dataaccesslayer.NotificationDAO;
import transportobjects.MaintenanceTaskDTO;
import dataaccesslayer.MaintenanceStatus;
import transportobjects.NotificationDTO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This servlet's single purpose is to handle requests for creating Maintenance tasks
 * based on notifications.
 *
 * @author Arthur Scharf
 */
public class NotificationResponseController extends HttpServlet
{
    /**
     * Handles the HTTP POST method.
     * This method receives requests from the notification display JSP.
     * It extracts the notification type from the URL path and the notification ID
     * from the form parameter, then attempts to create a corresponding
     * MaintenanceTask in the database. After processing, it redirects to the home page.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        String notificationIdParam = request.getParameter("notificationId");
        
        
               
        int notificationId = -1;

        try {
            if (notificationIdParam != null && !notificationIdParam.isEmpty()) {
                notificationId = Integer.parseInt(notificationIdParam);
                System.out.println(" --------------------- " + notificationId);
            } else {
                System.err.println("NotificationResponseController: Missing notification ID in request.");
                response.sendRedirect(request.getContextPath() + "/index.jsp?error=missing_id");
                return;
            }
            
            NotificationDAO notificationDAO = new NotificationDAO();
            MaintenanceDAO maintenanceDAO = new MaintenanceDAO();
            NotificationDTO originalNotification = notificationDAO.getById(notificationId);
            

            if (originalNotification != null) {
                MaintenanceTaskDTO newTask = new MaintenanceTaskDTO();
                
                newTask.setDescription(
                    "Action required for " + 
                    originalNotification.getType().name().toLowerCase() + 
                    " notification: " + 
                    originalNotification.getData()
                );
                
                newTask.setStatus(MaintenanceStatus.PENDING);

                maintenanceDAO.create(newTask);
                
                System.out.println("Maintenance task created successfully for Notification ID: " + notificationId);

                notificationDAO.delete(notificationId);
            } else {
                System.err.println("NotificationResponseController: Original notification not found for ID: " + notificationId);
                response.sendRedirect(request.getContextPath() + "/index.jsp?error=notification_not_found");
                return;
            }

        } catch (NumberFormatException e) {
            System.err.println("NotificationResponseController: Invalid Notification ID format: " + notificationIdParam);
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/index.jsp?error=invalid_id_format");
            return; 
        } catch (Exception e) {
            System.err.println("NotificationResponseController: Error processing notification or creating task: " + e.getMessage());
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/index.jsp?error=processing_failed");
            return;
        }

        // --- Redirect back to "home" (index.jsp) after successful processing ---
        response.sendRedirect(request.getContextPath() + "/home");
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "NotificationResponseController handles creation of maintenance tasks from notifications.";
    }
}
