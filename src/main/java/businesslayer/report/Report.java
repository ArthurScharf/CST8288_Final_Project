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
public class Report {
    
    //Fileds are subjected to be chanegd based in the project proccess
    private String locationTracking;
    private int energyConsumption;
    private List<String> transitMaintenance;
    private HashMap<String, String> operatorPerformance;
    private double cost;
            
    public Report(String locationTracking, int energyConsumption,
            List<String> transitMaintenance, HashMap<String, String> operatorPerformance,
            double cost){
        this.locationTracking = locationTracking;
        this.energyConsumption = energyConsumption;
        this.transitMaintenance = transitMaintenance;
        this.operatorPerformance = operatorPerformance;
        this.cost = cost;
    
    }

    public String getLocationTracking() {
        return locationTracking;
    }

    public int getEnergyConsumption() {
        return energyConsumption;
    }

    public List<String> getTransitMaintenance() {
        return transitMaintenance;
    }

    public HashMap<String, String> getOperatorPerformance() {
        return operatorPerformance;
    }

    public double getCost() {
        return cost;
    }
    
    
    
    //bulds a new object of ReportBuilder based on the passed parameters form the constructor
    public static ReportBuilder builder (){
        
        return new ReportBuilder();
        
    }
}
