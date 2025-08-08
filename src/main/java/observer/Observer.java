/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package observer;

import dataaccesslayer.NotificationDAO;
import transportobjects.NotificationType;
import transportobjects.NotificationDTO;
import transportobjects.VehicleDTO;

/**
 * An Observer class to create notification objects. These notifications are stored
 * in the database and served when appropriate accounts ask for them
 * 
 * @author Arthur Scharf
 */
public class Observer 
{
    /**
     * Publisher this observer is registered to. Is is the responsibility of this observer
     * to construct the notification it creates, so it needs this context
     */
    protected VehicleDTO vcl;
    
    
    protected Observer(VehicleDTO vcl)
    {
        this.vcl = vcl;
    }
    
    /**
     * Creates a NotificationDTO using the data that pertains the the event that
     * triggered the Observer
     * 
     * This should be abstract, but I'm not changing it last minute
     * 
     * @deprecated Migrating code to child classes
     * 
     * @param type Type of notification to be created
     * @param data String representation of the data relevant to the event. Stored in database
     * @throws Exception 
     */
    public void update(NotificationType type, String data) throws Exception
    {
        NotificationDAO dao = new NotificationDAO();
        NotificationDTO dto = new NotificationDTO();
        dto.setType(type);
        dto.setData(data);
        try {
            dao.create(dto);
        } catch (Exception e)
        {
            throw new Exception("Observer::update -- Update failed. Notification Not created: " + e.getMessage(), e);
        }
    }
}
