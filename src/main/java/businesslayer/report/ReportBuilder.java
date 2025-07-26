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
    private int cost;
    
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

    public ReportBuilder addCost(int cost) {
        this.cost = cost;
        return this;
    }

    //This method builds a Report object based on the added parameters
    public Report builder(){
        return new Report(locationTracking, energyConsumption,
            transitMaintenance, operatorPerformance,
            cost);
    }
    
}
