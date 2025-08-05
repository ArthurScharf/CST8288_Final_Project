/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package transportobjects;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * String storage convention: "type|batteryAmount|maxBattery"
 * @author Mike sharpe
 */
public class LightRailDTO extends VehicleDTO
{
    private static final float lightRailSpeed = 30.f;
    private static final float batteryConsumptionRate = 0.000005f;

    private float batteryAmount;
    private float maxBattery;

    public LightRailDTO()
    {
        super();
        super.vehicleSpeed = lightRailSpeed;
        super.setMaintainanceDistance(9000);
        batteryAmount = -1;
        maxBattery = -1;
    }

    public float getBatteryAmount() {
        return batteryAmount;
    }

    public float getMaxBattery() {
        return maxBattery;
    }

    public void setBatteryAmount(float batteryAmount) {
        this.batteryAmount = batteryAmount;
    }

    public void setMaxBattery(float maxBattery){
        this.maxBattery = maxBattery;
    }

    @Override
    public String toString()
    {
        return super.toString() + "test";
    }
    
    @Override
    public String serialize()
    {
        return "LightRail|" 
                + batteryAmount + "|" 
                + maxBattery;
    }
    
    @Override
    public void updateResourceConsumption(float deltaSeconds)
    {
        batteryAmount = Math.clamp(batteryAmount - (batteryConsumptionRate * deltaSeconds), 0.f, maxBattery);
        if ((batteryAmount / maxBattery) <= 0.25)
        {
            try {
                fuelObserver.update();
            } catch (Exception ex) {
                Logger.getLogger(LightRailDTO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
