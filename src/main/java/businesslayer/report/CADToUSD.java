/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package businesslayer.report;

/**
 * This class represents the CAD to USD conversion strategy.
 * @author Sina Paslar
 */
public class CADToUSD implements ICurrencyStrategy {

    private final double dailyRate = 0.73;
    
    /**
     * Converts a value based on the implemented conversion strategy.
     * @param amount the input value
     * @return the converted result
     */
    @Override
    public double convert(double amount) {
        return amount * dailyRate;
    }

    @Override
    public String getLabel() {
        return "USD";
    }
    
    
}
