/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package transportobjects;

import java.io.Serializable;

/**
 * Data transfer object for vehicles.
 * Follows the java bean convention
 * 
 * @author Arthur Scharf
 */
public class VehicleDTO implements Serializable
{
    
    private String vehicleNumber;
    
    private int maximumPassengers;
    
    private float totalDistanceTraveled;
    
    // Initialized by subtypes
    private float maintenanceDistance;
    
    /**
     * Distance traveled at which the vehicle must be maintained
     */
    private float maintainanceDistance;
    
    private int routeID;

  
    
    public VehicleDTO()
    {
        this.vehicleNumber = "DEFAULT";
        this.maximumPassengers = -1;
        totalDistanceTraveled = -1;
        maintenanceDistance = -1;
    }
    
// ---- ---- ---- ------- ---- ---- ---- //
// ---- ---- Getters & Setters ---- ---- //
// ---- ---- ---- ------- ---- ---- ---- //
    
    public int getRouteID() {
        return routeID;
    }
    
    public void setRouteID(int routeID)
    {
        this.routeID = routeID;
    }
    
    
    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public void setMaximumPassengers(int maximumPassengers) {
        this.maximumPassengers = maximumPassengers;
    }

    public void setTotalDistanceTraveled(float totalDistanceTraveled) {
        this.totalDistanceTraveled = totalDistanceTraveled;
    }

    public void setMaintainanceDistance(float maintainanceDistance) {
        this.maintainanceDistance = maintainanceDistance;
    }

    public int getMaximumPassengers() {
        return maximumPassengers;
    }

    public float getTotalDistanceTraveled() {
        return totalDistanceTraveled;
    }

    public float getMaintainanceDistance() {
        return maintainanceDistance;
    }
}
