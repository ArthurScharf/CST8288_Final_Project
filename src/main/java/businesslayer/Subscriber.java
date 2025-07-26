/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package businesslayer;
import transportobjects.NotificationDTO;

/**
 * Observer interface for notifications
 * 
 * @author Benjamin Gurth
 */
public interface Subscriber 
{
    
    /**
     * Receives notification updates when subscribed events happen
     * @param notification 
     */
    void update(NotificationDTO notification);
    
    /**
     * Gets the subscriber's user ID
     * @return user identifier
     */
    String getUserId();
    
    /**
     * Checks if subscriber is currently online
     * @return true if subscriber is online 
     */
    boolean isOnline();
    
    /**
     * Gets the notification type this subscriber is interested in
     * @return notification type (maintenance, fuel, break)
     */
    String getSubscriptionType();
}