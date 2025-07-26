/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package businesslayer;

import dataaccesslayer.NotificationDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import transportobjects.NotificationDTO;


/**
 * Invoker executes and manages notification commands
 * 
 * @author Benjamin Gurth
 */
public class NotificationInvoker {
    
    
    /**
     * Stores a notification command for offline users
     * @param command command to execute
     * @throws SQLException if database operation fails
     */
    public void storeCommand(NotificationCommand command) throws SQLException {
        command.execute();
    }
    
    
    /**
     * Executes notifications  when they come online
     * Retrieves stored notifications and sends them, then deletes them
     * @param subscribers list of subscribers
     * @throws SQLException if database operation fails
     */
    public void executePendingCommands(ArrayList<Subscriber> subscribers) throws SQLException {
        NotificationDAO dao = new NotificationDAO();
        
        for (Subscriber subscriber : subscribers) {
            if (subscriber.isOnline()) {

                ArrayList<NotificationDTO> pendingNotifications = dao.getByType(subscriber.getSubscriptionType()); 
                
                
                for (NotificationDTO notification : pendingNotifications) {
                    subscriber.update(notification);
                    
                    
                    dao.delete(notification.getNotificationId()); 
                }
            }
        }
    }
}