/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package businesslayer.report;

/**
 * Defines a strategy for currency conversion.
 * Implementations of this interface provide specific logic
 * for converting amounts between currencies and returning a label
 * describing the conversion type.
 * 
 * @author 
 *     Sina Paslar
 */
public interface ICurrencyStrategy {
    
    /**
     * Converts the given amount according to the implemented strategy.
     *
     * @param amount the amount to convert
     * @return the converted amount
     */
    double convert(double amount);
    
    /**
     * Returns a label describing this conversion strategy (e.g., "USD to CAD").
     *
     * @return the label for the strategy
     */
    String getLabel();
    
}