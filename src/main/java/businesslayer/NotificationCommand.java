/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package businesslayer;

import dataaccesslayer.NotificationDAO;
import java.sql.SQLException;
import transportobjects.NotificationDTO;

/**
 * Command implementation that stores notifications in database
 * @author Benjamin Gurth
 */
public class NotificationCommand {
    
    private NotificationDTO notification;
    
    /**
     * Creates a notification command for storing offline notification
     * @param notification 
     */
    public NotificationCommand(NotificationDTO notification) {
        this.notification = notification;
    }
    
    /**
     * Executes the command by storing notification in database
     * @throws SQLException if database operation fails
     */
    public void execute() throws SQLException {
        NotificationDAO dao = new NotificationDAO();
        dao.create(notification);
    }
    
    /**
     * Gets the notification being stored
     * @return the notification DTO
     */
    public NotificationDTO getNotification() {
        return notification;
    }
}
