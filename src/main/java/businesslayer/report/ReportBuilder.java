/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package businesslayer.report;

import dataaccesslayer.VehicleDAO;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import transportobjects.BusDTO;
import transportobjects.DieselElectricDTO;
import transportobjects.LightRailDTO;
import transportobjects.VehicleDTO;

/**
 *
 * @author Sina Paslar
 */
public class ReportBuilder {
    //PROJECT OBJECTS
    private ArrayList<VehicleDTO> vehicleDTOList;
    private VehicleDAO vehicleDAO;
    
    private ArrayList<VehicleDTO> busDTOList;
    private ArrayList<VehicleDTO> deiselElectricDTOList;
    private ArrayList<VehicleDTO> lightRailDTO;

    ICurrencyStrategy mode;
    public ReportBuilder addMode(ICurrencyStrategy mode){
        this.mode = mode;
        return this;
    }
    
    //Currency Strategy
    private CurrecnyStrategyContext currencyContext;
        
    public void setStrategy(ICurrencyStrategy currencyStrategy){
        currencyContext.setStrategy(currencyStrategy);
        
    }
    
    

//    public ReportBuilder addCost(double cost, int mode) {
//        //Choosing between USDToCAD or CADToUSD shoudl be implemented somehow later on
//        switch (mode) {
//            case 1:
//                currencyStrategy = new CADToUSD();
//                break;
//            case 2:
//                currencyStrategy = new USDToCAD();
//                break;
//            default:
//                currencyStrategy = null;
//                break;
//        }
//
//        if (currencyStrategy != null) {
//            this.cost = currencyStrategy.convert(cost);
//        } else {
//            this.cost = cost;
//        }
//        return this;
//        }
    
    
    
    //PROJECT OBJECTS
    public ReportBuilder addVehicleDTOList(ArrayList<VehicleDTO> vehicleDTOList){
        this.vehicleDTOList = vehicleDTOList;
        return this;
    }
    
    public ReportBuilder addBusDTOList(ArrayList<VehicleDTO> busDTOList){
        this.busDTOList = busDTOList;
        return this;
    }
    
    public ReportBuilder addDeiselElectricDTOList (ArrayList<VehicleDTO> deiselElectricDTOList){
        this.deiselElectricDTOList = deiselElectricDTOList;
        return this;
    }
    
    
    public ReportBuilder addLightRailDTO (ArrayList<VehicleDTO> lightRailDTO){
        this.lightRailDTO = lightRailDTO;
        return this;
    }
    
    public ReportBuilder withVehicleDAO(VehicleDAO dao) {
    this.vehicleDAO = dao;
    return this;
    }

    
    

    //This method builds a Report object based on the added parameters
    public Report build(){
        return new Report(busDTOList, 
                deiselElectricDTOList, lightRailDTO, mode);
    }
    
}
