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
    
    private String resourceType;
    private float resourceAmount;
    private float maxResource;
    
    public BusDTO()
    {
        super();
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
}
