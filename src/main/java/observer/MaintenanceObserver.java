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
 * Observer for triggering vehicle maintenance
 * 
 * @author Arthur Scharf
 */
public class MaintenanceObserver extends Observer
{    
    
    public MaintenanceObserver(VehicleDTO vcl)
    {
        super(vcl);
        vcl.registerMaintainenceObserver(this); // Visitor Pattern
    }
    
    
    /**
     * Creates a MaintenanceNotification in the database based on the state
     * of the vehicle
     * 
     * @throws Exception 
     */
    public void update() throws Exception
    {
        NotificationDTO n = new NotificationDTO();
        n.setType(NotificationType.MAINTENANCE);
        /* -- TODO --
        *  VehicleDTO exposes abstract method for getting maintenance state.
        *  Observer calls this method and reads maintenance state, creating a simple
        *  maintenance report from it. 
        *  This can be done in the future, but for now, this simple placeholder will do
        */
        String data = String.format("Vehicle Number: %s --> Maintenance Required", vcl.getVehicleNumber());
        n.setData(data);
        NotificationDAO dao = new NotificationDAO();
        dao.create(n);
    }

}
