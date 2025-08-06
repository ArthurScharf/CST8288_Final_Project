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
 * Observer for trigger fuel alerts
 * 
 * @author Arthur Scharf
 */
public class FuelObserver extends Observer
{    
    
    public FuelObserver(VehicleDTO vcl)
    {
        super(vcl);
        vcl.registerFuelObserver(this); // Visitor Pattern
    }
    
    
    /**
     * Creates a FuelNotification in the database based on the state
     * of the vehicle
     * 
     * @throws Exception 
     */
    public void update() throws Exception
    {
        NotificationDTO n = new NotificationDTO();
        n.setType(NotificationType.FUEL);
        String data = String.format("Vehicle Number: %s --> Fuel Low", vcl.getVehicleNumber()); 
        n.setData(data);
        NotificationDAO dao = new NotificationDAO();
        dao.create(n);
    }
}
