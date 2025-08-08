/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package transportobjects;


import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * String storage convention: "type|batteryAmount|maxBattery|fuelAmount|maxFuel"
 * @author Mike Sharpe
 */
public class DieselElectricDTO extends VehicleDTO
{

    private static final float dieselElectricSpeed = 18.f;
    private static final float batteryConsumptionRate = 0.00002f;
    private static final float fuelConsumptionRate = 0.000015f;
    
    private float batteryAmount;
    private float maxBattery;
    private float fuelAmount;
    private float maxFuel;
    
    public DieselElectricDTO()
    {
        super();
        super.vehicleSpeed = dieselElectricSpeed;
        super.setMaintainanceDistance(12000);
        batteryAmount = -1;
        maxBattery = -1;
    }
    
    public float getBatteryAmount() {
        return batteryAmount;
    }

    public float getMaxBattery() {
        return maxBattery;
    }

    public float getFuelAmount() {
        return fuelAmount;
    }

    public float getMaxFuel() {
        return maxFuel;
    }

    public void setBatteryAmount(float batteryAmount) {
        this.batteryAmount = batteryAmount;
    }

    public void setMaximumBattery(float maxBattery) {
        this.maxBattery = maxBattery;
    }

    public void setFuelAmount(float fuelAmount) {
        this.fuelAmount = fuelAmount;
    }

    public void setMaxFuel(float maxFuel) {
        this.maxFuel = maxFuel;
    }

    @Override
    public String toString()
    {
        return super.toString() + "test";
    }
    
    @Override
    public String serialize()
    {
        return "DieselElectric|" 
                + batteryAmount + "|" 
                + maxBattery + "|"
                + fuelAmount + "|"
                + maxFuel;
    }
    
    
    @Override
    public void updateResourceConsumption(float deltaSeconds)
    {
        batteryAmount = Math.clamp(batteryAmount - (batteryConsumptionRate * deltaSeconds), 0.f, maxBattery);
        if ( (batteryAmount / maxBattery) <= 0.25 )
        {
            try {
                fuelObserver.update();
            } catch (Exception ex) {
                Logger.getLogger(DieselElectricDTO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        batteryAmount -= Math.clamp(fuelAmount - (fuelConsumptionRate * deltaSeconds), 0.f, maxFuel);
        if ( (fuelAmount / maxFuel) <= 0.25 )
        {
            try {
                fuelObserver.update();
            } catch (Exception ex) {
                Logger.getLogger(DieselElectricDTO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//~ updateResourceConsumption(...)

        
    public static String getInitialTypeInfo()
    {
        return "DieselEletric|100.0|100.0|1000.0|1000.0";
    }
    
}
