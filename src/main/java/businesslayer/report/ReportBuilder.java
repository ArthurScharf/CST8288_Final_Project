/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package businesslayer.report;

import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Sina Paslar
 */
public class ReportBuilder {
    
    //Fileds are subjected to be chanegd based in the project proccess
    private String locationTracking;
    private int energyConsumption;
    private List<String> transitMaintenance;
    private HashMap<String, String> operatorPerformance;
    private double cost;
    private ICurrencyStrategy currencyStrategy;
    
    //Currency Strategy
    private CurrecnyStrategyContext currencyContext;
        
    public void setStrategy(ICurrencyStrategy currencyStrategy){
        currencyContext.setStrategy(currencyStrategy);
        
    }
    
    public ReportBuilder addLocationTracking(String locationTracking)
    {
        this.locationTracking = locationTracking;
        return this;
    }
    
    public ReportBuilder addEnergyConsumption(int energyConsumption) {
    this.energyConsumption = energyConsumption;
    return this;
    }

    public ReportBuilder addTransitMaintenance(List<String> transitMaintenance) {
        this.transitMaintenance = transitMaintenance;
        return this;
    }

    public ReportBuilder addOperatorPerformance(HashMap<String, String> operatorPerformance) {
        this.operatorPerformance = operatorPerformance;
        return this;
    }

    public ReportBuilder addCost(double cost, int mode) {
        //Choosing between USDToCAD or CADToUSD shoudl be implemented somehow later on
        switch (mode) {
            case 1:
                currencyContext = new CurrecnyStrategyContext(new CADToUSD());
                this.cost = currencyContext.currencyConvertor(cost);
                break;
            case 2:
                currencyContext = new CurrecnyStrategyContext(new USDToCAD());
                this.cost = currencyContext.currencyConvertor(cost);
                break;
            default:
                this.cost = cost;
                break;
        }
        return this;
        }

    //This method builds a Report object based on the added parameters
    public Report build(){
        return new Report(locationTracking, energyConsumption,
            transitMaintenance, operatorPerformance,
            cost, currencyStrategy);
    }
    
}
