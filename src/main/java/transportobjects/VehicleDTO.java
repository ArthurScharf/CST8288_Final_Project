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
    
    private String type;
    
    private int maximumPassengers;
    
    private float totalDistanceTraveled;
    
    /**
     * Distance traveled at which the vehicle must be maintained
     */
    private float maintainanceDistance;
    
  
    
    public VehicleDTO()
    {
        this.vehicleNumber = "DEFAULT";
        this.type = "DEFAULT";
        this.maximumPassengers = -1;
        totalDistanceTraveled = -1;
    }
    
    
// ---- ---- ---- ------- ---- ---- ---- //
// ---- ---- Getters & Setters ---- ---- //
// ---- ---- ---- ------- ---- ---- ---- //
    
    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public void setType(String type) {
        this.type = type;
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

    public String getType() {
        return type;
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
