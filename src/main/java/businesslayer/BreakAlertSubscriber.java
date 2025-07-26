/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package businesslayer;

import transportobjects.NotificationDTO;

/**
 * Break alert subscriber implementation
 * 
 * @author Benjamin Gurth
 */
public class BreakAlertSubscriber implements Subscriber {
    
    private String userId;
    private boolean online;
    
    /**
     * Creates break alert subscriber
     * @param userId 
     */
    public BreakAlertSubscriber(String userId) {
        this.userId = userId;
        this.online = false;
    }
    
    /**
     * Receives break notification updates
     * @param notification 
     */
    @Override
    public void update(NotificationDTO notification) {
        if ("break".equals(notification.getNotificationType())) {
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
     * @return "break"
     */
    @Override
    public String getSubscriptionType() {
        return "break";
    }
    
    /**
     * Sets online status
     * @param online
     */
    public void setOnline(boolean online) {
        this.online = online;
    }
}