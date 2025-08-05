/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package transportobjects;

import dataaccesslayer.VehicleDAO;
import java.io.Serializable;
import observer.FuelObserver;
import observer.MaintenanceObserver;

/**
 * Data transfer object for vehicles.
 * Follows the java bean convention
 * 
 * 
 * @author Arthur Scharf
 */
public abstract class VehicleDTO implements Serializable
{
    private String vehicleNumber;
    
    private int maximumPassengers;

    /*
    TODO
    This value should be called distanceSinceLastMaintenanceRequest.
    The design changed but I didn't have time to fix this as of writing
    This value is used as if it's tracking distance since last maintenance
     */
    public void setTotalDistanceTraveled(float totalDistanceTraveled) {
        this.totalDistanceTraveled = totalDistanceTraveled;
    }

    private float totalDistanceTraveled;
    
    /**
     * Initialized by subtypes. Distance until the vehicle must be maintained.
     * 
     * TODO: This value shouldn't be stored in the database
     * 
     * I'm aware I spelled this wrong 
     *    -Arthur
     */
    private float maintainanceDistance;
    

    
    private int routeID;
    
    /** 
     * Initialized by subtypes
     */
    protected float vehicleSpeed;
    
    
    /**
     * Observer notified when maintenance metrics are reached events occur
     */
    MaintenanceObserver maintenanceObserver;
    
    
    /**
     * Observer notified when fuel metrics are reached events occur
     */
    FuelObserver fuelObserver;
    
    
    public VehicleDTO()
    {
        this.vehicleNumber = "DEFAULT";
        this.maximumPassengers = -1;
        totalDistanceTraveled = -1;
        maintainanceDistance = -1;
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
    
    public void registerMaintainenceObserver(MaintenanceObserver obs)
    {
        this.maintenanceObserver = obs;
    }
    
    public boolean hasMaintenenceObserver()
    {
        return maintenanceObserver != null;
    }
    
    public void registerFuelObserver(FuelObserver obs)
    {
        this.fuelObserver = obs;
    }
    
    public boolean hasFuelObserver()
    {
        return fuelObserver != null;
    }
    
    /**
     * This method is part of a placeholder system to simulate the movement of vehicles
     * without directly implementing a real GPS tracking system
     * 
     * @param deltaSeconds difference in seconds since last time vehicle distance was updated.
     */
    public void updateDistanceTraveled(float deltaSeconds)
    {
        totalDistanceTraveled += vehicleSpeed * deltaSeconds;
        
        if (vehicleNumber.equals("V001"))
        {
            System.out.println("\n" + totalDistanceTraveled + "\n");
        }
        
        // -- Distance to maintenance handling -- //
        if (totalDistanceTraveled >= maintainanceDistance)
        {
            try {
                maintenanceObserver.update();
                totalDistanceTraveled = totalDistanceTraveled - maintainanceDistance;
                VehicleDAO dao = new VehicleDAO();
                dao.update(this, serialize());
            } catch (Exception e)
            {
                e.printStackTrace(); // TODO: Properly handle errors
            }
        }
    }
    
    
    
    /**
     * Each subtype of VehicleDTO manages different energy resources and handles
     * resource consumption differently (Hybrids use two resources, while fuel vehicles use one).
     * Thus, this method is abstract and handled by the inheritors
     * @param deltaSeconds 
     */
    public abstract void updateResourceConsumption(float deltaSeconds);
    
    public abstract String serialize();
}
