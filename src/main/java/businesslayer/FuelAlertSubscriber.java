/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package businesslayer;

import transportobjects.NotificationDTO;

/**
 * Fuel alert subscriber implementation
 * 
 * @author Benjamin Gurth
 */
public class FuelAlertSubscriber implements Subscriber {
    
    private String userId;
    private boolean online;
    
    /**
     * Creates fuel alert subscriber
     * @param userId 
     */
    public FuelAlertSubscriber(String userId) {
        this.userId = userId;
        this.online = false;
    }
    
    /**
     * Receives fuel notification updates
     * @param notification
     */
    @Override
    public void update(NotificationDTO notification) {
        if ("fuel".equals(notification.getNotificationType())) {
            //TODO: Add when command class is complete
        }
    }
    
    /**
     * Returns the subscriber's user ID
     * @return userId
     */
    @Override
    public String getUserId() {
        return userId;
    }
    
    /**
     * Returns subscriber's online status
     * @return true if subscriber is online
     */
    @Override
    public boolean isOnline() {
        return online;
    }
    
    /**
     * Gets the notification type this subscriber handles
     * @return "fuel"
     */
    @Override
    public String getSubscriptionType() {
        return "fuel";
    }
    
    /**
     * Sets online status
     * @param online 
     */
    public void setOnline(boolean online) {
        this.online = online;
    }
}