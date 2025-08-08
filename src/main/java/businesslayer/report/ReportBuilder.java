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
 * The {@code ReportBuilder} class is responsible for constructing {@link Report} 
 * objects using the Builder design pattern. It allows the step-by-step configuration 
 * of report data such as vehicle lists, currency conversion strategy, and DAO access.
 * <p>
 * Supported vehicle types include:
 * <ul>
 *   <li>{@link BusDTO}</li>
 *   <li>{@link DieselElectricDTO}</li>
 *   <li>{@link LightRailDTO}</li>
 * </ul>
 * </p>
 *
 * <p>
 * Example usage:
 * <pre>{@code
 * Report report = new ReportBuilder()
 *                     .addBusDTOList(busList)
 *                     .addDeiselElectricDTOList(dieselList)
 *                     .addLightRailDTO(lightRailList)
 *                     .addMode(new USDToCAD())
 *                     .build();
 * }</pre>
 * </p>
 * 
 * @author 
 *     Sina Paslar
 */
public class ReportBuilder {
    // PROJECT OBJECTS
    private ArrayList<VehicleDTO> vehicleDTOList;
    private VehicleDAO vehicleDAO;

    private ArrayList<VehicleDTO> busDTOList;
    private ArrayList<VehicleDTO> deiselElectricDTOList;
    private ArrayList<VehicleDTO> lightRailDTO;
    ICurrencyStrategy mode;

    /**
     * Sets the currency conversion strategy to be used in the report.
     * @param mode the currency strategy implementation
     * @return the current {@code ReportBuilder} instance
     */
    public ReportBuilder addMode(ICurrencyStrategy mode) {
        this.mode = mode;
        return this;
    }

    // Currency Strategy
    private CurrecnyStrategyContext currencyContext;

    /**
     * Updates the currency strategy context with the given strategy.
     * @param currencyStrategy the currency conversion strategy to set
     */
    public void setStrategy(ICurrencyStrategy currencyStrategy) {
        currencyContext.setStrategy(currencyStrategy);
    }

    /**
     * Adds a list of all vehicles to the builder.
     * @param vehicleDTOList list of {@link VehicleDTO} objects
     * @return the current {@code ReportBuilder} instance
     */
    public ReportBuilder addVehicleDTOList(ArrayList<VehicleDTO> vehicleDTOList) {
        this.vehicleDTOList = vehicleDTOList;
        return this;
    }

    /**
     * Adds a list of bus vehicles to the builder.
     * @param busDTOList list of {@link BusDTO} objects
     * @return the current {@code ReportBuilder} instance
     */
    public ReportBuilder addBusDTOList(ArrayList<VehicleDTO> busDTOList) {
        this.busDTOList = busDTOList;
        return this;
    }

    /**
     * Adds a list of diesel-electric vehicles to the builder.
     * @param deiselElectricDTOList list of {@link DieselElectricDTO} objects
     * @return the current {@code ReportBuilder} instance
     */
    public ReportBuilder addDeiselElectricDTOList(ArrayList<VehicleDTO> deiselElectricDTOList) {
        this.deiselElectricDTOList = deiselElectricDTOList;
        return this;
    }

    /**
     * Adds a list of light rail vehicles to the builder.
     * @param lightRailDTO list of {@link LightRailDTO} objects
     * @return the current {@code ReportBuilder} instance
     */
    public ReportBuilder addLightRailDTO(ArrayList<VehicleDTO> lightRailDTO) {
        this.lightRailDTO = lightRailDTO;
        return this;
    }

    /**
     * Associates a {@link VehicleDAO} with the builder.
     * @param dao the DAO to use for vehicle data access
     * @return the current {@code ReportBuilder} instance
     */
    public ReportBuilder withVehicleDAO(VehicleDAO dao) {
        this.vehicleDAO = dao;
        return this;
    }

    /**
     * Builds and returns a {@link Report} object using the configured parameters.
     * @return a new {@link Report} instance
     */
    public Report build() {
        return new Report(busDTOList,
                deiselElectricDTOList, lightRailDTO, mode);
    }
}