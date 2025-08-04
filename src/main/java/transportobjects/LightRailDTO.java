/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package transportobjects;

/**
 * @author Mike sharpe
 */
public class LightRailDTO extends VehicleDTO
{
    private float batteryAmount;
    private float maxBattery;

    public LightRailDTO()
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
    
    public String serialized()
    {
        return "LigthRail|" 
                + batteryAmount + "|" 
                + maxBattery;
    }
}
