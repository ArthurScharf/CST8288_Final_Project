/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package businesslayer.report;

/**
 *
 * @author Sina Paslar
 */
public class CurrecnyStrategyContext {
    
    
    private ICurrencyStrategy convertor;
    
    /**
     * Constructs a CurrencyContext with a given currency conversion strategy.
     *
     * @param convertor the initial currency conversion strategy
     */
    public CurrecnyStrategyContext (ICurrencyStrategy convertor){
        this.convertor = convertor;
    }
    
    /**
     * Sets a new currency conversion strategy.
     *
     * @param convertor the new strategy to use for currency conversion
     * @return 
     */
    public void setStrategy (ICurrencyStrategy convertor){
        this.convertor = convertor;
    }
    
    /**
     * Converts a currency amount using the current strategy.
     *
     * @param amount the currency value to convert
     * @return the converted currency value
     */
    public double currencyConvertor (double amount){
        return convertor.convert(amount);
    }
    

}
