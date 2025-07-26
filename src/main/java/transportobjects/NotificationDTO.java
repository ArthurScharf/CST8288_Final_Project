/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package transportobjects;

import java.io.Serializable;

/**
 * Data transfer object for notifications.
 * 
 * @author Benjamin Gurth
 */
public class NotificationDTO implements Serializable {
    
    private int notificationId;
    
    private String vehicleId;
    
    private String notificationType;
    
    //Data will be used as whatever is needed per notification
    private String data;
    
    /**
     * Default constructor
     */
    public NotificationDTO() {
        this.notificationId = -1;
        this.vehicleId = "DEFAULT";
        this.notificationType = "DEFAULT";
        this.data = "DEFAULT";
    }
    
    /**
     * Constructor for creating new notifications (without ID because
     * the database will auto-increment)
     * @param vehicleId 
     * @param notificationType 
     * @param data 
     */
    public NotificationDTO(String vehicleId, String notificationType, String data) {
        this.notificationId = -1;
        this.vehicleId = vehicleId;
        this.notificationType = notificationType;
        this.data = data;
    }
    
    /**
     * Constructor for database records (with ID for reading notifications)
     * @param notificationId 
     * @param vehicleId 
     * @param notificationType 
     * @param data 
     */
    public NotificationDTO(int notificationId, String vehicleId, String notificationType, String data) {
        this.notificationId = notificationId;
        this.vehicleId = vehicleId;
        this.notificationType = notificationType;
        this.data = data;
    }

    public int getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(int notificationId) {
        this.notificationId = notificationId;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getNotificationType() {
        return notificationType;
    }

    public void setNotificationType(String notificationType) {
        this.notificationType = notificationType;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}