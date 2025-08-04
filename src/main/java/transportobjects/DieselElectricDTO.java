/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package transportobjects;

/**
 * String storage convention: "type|batteryAmount|maxBattery|fuelAmount|maxFuel"
 * @author Mike Sharpe
 */
public class DieselElectricDTO extends VehicleDTO
{

    private float batteryAmount;
    private float maxBattery;
    private float fuelAmount;
    private float maxFuel;
    
    public DieselElectricDTO()
    {
        super();
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
}
