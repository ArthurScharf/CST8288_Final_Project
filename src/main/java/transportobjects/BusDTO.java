/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package transportobjects;

/**
 * String storage convention: "type|resourceType|resourceAmount|maxResource"
 * @author Mike Sharpe
 */
public class BusDTO extends VehicleDTO
{   
    
    /* --- Note ---
    While the requirements for vehicle resource consumption with this assignment 
    could imply components and strategies, the simple and largely static nature of what
    we're doing mildly justifies using constants like this.
    
    If this were the real world, I'd either allow this constants to be stored in the database
    and gathered when DTO's are created, or use fuel components to handle this sort of this
        - Arthur
    */
    
    private static final float busSpeed = 20.f; // km/h
    private static final float dieselConsumptionRate = 0.00001f;
    private static final float CNGConsumptionRate = 0.000015f;
    
    private String resourceType;
    private float resourceAmount;
    private float maxResource;
    
    public BusDTO()
    {
        super();
        super.vehicleSpeed = busSpeed;
        super.setMaintainanceDistance(10000);
        resourceType = "DEFAULT";
        resourceAmount = -1;
        maxResource = -1;
        
    }
    
    
    public String getResourceType() 
    {
        return resourceType;
    }

    public float getResourceAmount() {
        return resourceAmount;
    }

    public float getMaxResource() {
        return maxResource;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    public void setResourceAmount(float resourceAmount) {
        this.resourceAmount = resourceAmount;
    }

    public void setMaxResource(float maxResource) {
        this.maxResource = maxResource;
    }
    
    @Override
    public String toString()
    {
        return super.toString()+"test";
    }
    
    @Override
    public String serialize()
    {
        return "Bus|" 
                + resourceType + "|" 
                + resourceAmount + "|"
                + maxResource;
    }
    
    
    @Override
    public void updateResourceConsumption(float deltaSeconds)
    {   
        float consumptionRate;
        if ("Diesel".equals(resourceType))
        {
            consumptionRate = dieselConsumptionRate;
        } else if ("CNG".equals(resourceType)) {
            consumptionRate = CNGConsumptionRate;
        } else {
            // TODO: Throw error
            System.out.println(
                 String.format(
                         "ERROR > BusDTO::updateResourceConsumption > VehicleNumber: %s"
                                 + " -- Invalid resourceType value: %s", getVehicleNumber(),  getResourceType())
            );
            return;
        }
        resourceAmount = Math.clamp(resourceAmount - (consumptionRate * deltaSeconds) , 0.f, maxResource);
            
        // -- Notifying fuel observer -- //
        if ( (resourceAmount / maxResource) <= 0.25f)
        {
            try {
                fuelObserver.update();
            } catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }//~ updateResourceConsumption(...);
}
