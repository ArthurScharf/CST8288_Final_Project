/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package businesslayer;

import java.sql.SQLException;
import java.util.ArrayList;
import transportobjects.NotificationDTO;

/**
 * Publisher manages subscribers and sends notifications
 * Sends using Observer for online users and stores with command for offline
 * @author Benjamin Gurth
 */
public class Publisher {
    
    private ArrayList<Subscriber> subscribers;
    private NotificationInvoker invoker;
    
    /**
     * Creates a publisher with empty list
     */
    public Publisher() {
        this.subscribers = new ArrayList<>();
        this.invoker = new NotificationInvoker();
    }
    
    /**
     * Adds a subscriber to the notification list
     * @param subscriber the subscriber to add
     */
    public void subscribe(Subscriber subscriber) {
        subscribers.add(subscriber);
    }
    
    /**
     * Removes a subscriber from the notification list
     * @param subscriber 
     */
    public void unsubscribe(Subscriber subscriber) {
        subscribers.remove(subscriber);
    }
    
    /**
     * Notifies all relevant subscribers dependent on Type, and online status
     * Sends using Observer for online users and stores with command for offline
     * @param notification 
     * @throws SQLException if database operation fails
     */
    public void notifyObserver(NotificationDTO notification) throws SQLException {
        for (Subscriber subscriber : subscribers) {
            
            if (subscriber.getSubscriptionType().equals(notification.getNotificationType())) {
                if (subscriber.isOnline()) {
                    
                    subscriber.update(notification);
                } else {
                    
                    NotificationCommand cmd = new NotificationCommand(notification);
                    invoker.storeCommand(cmd);
                }
            }
        }
    }
    
    /**
     * Executes pending commands for all online subscribers
     * Called when users come online
     * @throws SQLException if database operation fails
     */
    public void deliverPendingNotifications() throws SQLException {
        invoker.executePendingCommands(subscribers);
    }
    
    /**
     * Gets all current subscribers
     * @return list of subscribers
     */
    public ArrayList<Subscriber> getSubscribers() {
        return subscribers;
    }
}