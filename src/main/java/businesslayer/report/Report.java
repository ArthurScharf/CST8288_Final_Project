/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package businesslayer.report;

import java.util.ArrayList;
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
    private ICurrencyStrategy currencyStrategy;
            
    public Report(String locationTracking, int energyConsumption,
            List<String> transitMaintenance, HashMap<String, String> operatorPerformance,
            double cost, ICurrencyStrategy currencyStrategy){
        this.locationTracking = locationTracking;
        this.energyConsumption = energyConsumption;
        this.transitMaintenance = transitMaintenance;
        this.operatorPerformance = operatorPerformance;
        this.cost = cost;
        this.currencyStrategy = currencyStrategy;
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
    
    
    public List<String> toHTML()
    {
    List<String> htmlElements = new ArrayList<>();

    if (locationTracking != null) {
        htmlElements.add("<p><strong>Location Tracking:</strong> " + locationTracking + "</p>");
    }

    if (energyConsumption > 0) {
        htmlElements.add("<p><strong>Energy Consumption:</strong> " + energyConsumption + " kWh</p>");
    }

    if (transitMaintenance != null && !transitMaintenance.isEmpty()) {
        htmlElements.add("<p><strong>Transit Maintenance:</strong></p><ul>");
        for (String item : transitMaintenance) {
            htmlElements.add("<li>" + item + "</li>");
        }
        htmlElements.add("</ul>");
    }

    if (operatorPerformance != null && !operatorPerformance.isEmpty()) {
        htmlElements.add("<p><strong>Operator Performance:</strong></p><ul>");
        for (String key : operatorPerformance.keySet()) {
            htmlElements.add("<li>" + key + ": " + operatorPerformance.get(key) + "</li>");
        }
        htmlElements.add("</ul>");
    }

    if (cost > 0) {
    String label = "Unspecified";
    if (currencyStrategy != null) {
        label = currencyStrategy.getLabel();
    }
    htmlElements.add("<p><strong>Cost:</strong> " + cost + " " + label + "</p>");
}

    return htmlElements;
        
    }
    
    
    //bulds a new object of ReportBuilder based on the passed parameters form the constructor
    public static ReportBuilder builder (){
        
        return new ReportBuilder();
        
    }
}
