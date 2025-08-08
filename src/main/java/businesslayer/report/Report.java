/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt 
 * to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java 
 * to edit this template
 */
package businesslayer.report;

import dataaccesslayer.VehicleDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import transportobjects.BusDTO;
import transportobjects.DieselElectricDTO;
import transportobjects.LightRailDTO;
import transportobjects.VehicleDTO;

/**
 * The {@code Report} class is responsible for generating transit vehicle reports 
 * for different types of vehicles, including buses, diesel-electric trains, 
 * and light rail vehicles. 
 * <p>
 * It retrieves vehicle data from the database, processes resource usage 
 * (fuel/battery), calculates refueling or recharging costs based on the 
 * {@link ICurrencyStrategy} provided, and produces HTML-formatted tables 
 * summarizing the information.
 * </p>
 *
 * <p>
 * This class uses the Strategy design pattern for currency conversion via 
 * {@link ICurrencyStrategy}, and can be instantiated using the 
 * {@link #builder()} factory method.
 * </p>
 * 
 * @author  Sina Paslar
 */
public class Report {

    /** List of all vehicles retrieved from the database. */
    private ArrayList<VehicleDTO> vehicleDTOList;

    /** Price per unit for battery charging. */
    private final int BATTERY_PRICE = 7;

    /** Price per unit for CNG refueling. */
    private final int CNG_PRICE = 8;

    /** Price per unit for diesel refueling. */
    private final int DIESEL_PRICE = 10;

    /** List containing only bus DTOs. */
    private ArrayList<VehicleDTO> busDTOList;

    /** List containing only diesel-electric train DTOs. */
    private ArrayList<VehicleDTO> deiselElectricDTOList;

    /** List containing only light rail DTOs. */
    private ArrayList<VehicleDTO> lightRailDTO;

    /** Strategy instance used for currency conversion. */
    private ICurrencyStrategy mode;

    /**
     * Constructs a new {@code Report} instance with specific vehicle lists and 
     * a currency conversion strategy.
     *
     * @param busDTOList           list of bus DTOs
     * @param deiselElectricDTOList list of diesel-electric train DTOs
     * @param lightRailDTO         list of light rail DTOs
     * @param mode                 currency conversion strategy
     */
    public Report(ArrayList<VehicleDTO> busDTOList,
                  ArrayList<VehicleDTO> deiselElectricDTOList,
                  ArrayList<VehicleDTO> lightRailDTO,
                  ICurrencyStrategy mode) {
        this.vehicleDTOList = vehicleDTOList; // NOTE: This might be a bug: variable not passed in constructor
        this.busDTOList = busDTOList;
        this.deiselElectricDTOList = deiselElectricDTOList;
        this.lightRailDTO = lightRailDTO;
        this.mode = mode;
    }

    /**
     * Returns the full list of all vehicles.
     *
     * @return the list of all vehicles
     */
    public ArrayList<VehicleDTO> getVehicleDTOList() {
        return vehicleDTOList;
    }

    /**
     * Returns the list of bus DTOs.
     *
     * @return list of bus DTOs
     */
    public ArrayList<VehicleDTO> getBusDTOList() {
        return busDTOList;
    }

    /**
     * Returns the list of diesel-electric train DTOs.
     *
     * @return list of diesel-electric DTOs
     */
    public ArrayList<VehicleDTO> getDeiselElectricDTOList() {
        return deiselElectricDTOList;
    }

    /**
     * Returns the list of light rail DTOs.
     *
     * @return list of light rail DTOs
     */
    public ArrayList<VehicleDTO> getLightRailDTO() {
        return lightRailDTO;
    }

    /**
     * Returns the currently set currency conversion strategy.
     *
     * @return currency conversion strategy
     */
    public ICurrencyStrategy getMode() {
        return mode;
    }

    /**
     * Generates HTML-formatted reports for buses, diesel-electric trains, 
     * and light rail vehicles.
     * <p>
     * The method:
     * <ul>
     *     <li>Retrieves all vehicles from the database using {@link VehicleDAO}</li>
     *     <li>Calculates resource usage and refueling/recharging costs</li>
     *     <li>Converts costs to the target currency using {@link ICurrencyStrategy}</li>
     *     <li>Generates HTML tables with totals for each vehicle type</li>
     * </ul>
     * </p>
     *
     * @return a list of HTML strings, each representing a report section; 
     *         or {@code null} if an error occurs
     */
    public List<String> toHTML() {
        float sum = 0;
        List<String> htmlElements = new ArrayList<>();

        VehicleDAO dao = new VehicleDAO();
        try {
            vehicleDTOList = dao.getAll();
        } catch (SQLException e) {
            System.out.println("SQLException");
            return null;
        } catch (Exception e) {
            System.out.println("Exception");
            return null;
        }

        // ===== Bus Report =====
        if (busDTOList != null && !busDTOList.isEmpty()) {
            StringBuilder busTable = new StringBuilder();
            float totalBusCost = 0;

            busTable.append("<table border='1' cellpadding='5' cellspacing='0'>");
            busTable.append("<tr><th colspan='4'>Bus Report</th></tr>");
            busTable.append("<tr>")
                    .append("<th>Bus Number</th>")
                    .append("<th>Bus Type</th>")
                    .append("<th>Remaining Fuel (%)</th>")
                    .append("<th>Cost to Refuel ($)</th>")
                    .append("</tr>");

            for (VehicleDTO vehicleDTO : vehicleDTOList) {
                if (vehicleDTO instanceof BusDTO busDTO) {
                    float remainFuel = busDTO.getResourceAmount() / busDTO.getMaxResource() * 100;
                    float costToRefuel = 0;

                    if (busDTO.getResourceType().equalsIgnoreCase("Diesel")) {
                        costToRefuel = remainFuel * DIESEL_PRICE / 100;
                    } else if (busDTO.getResourceType().equalsIgnoreCase("CNG")) {
                        costToRefuel = remainFuel * CNG_PRICE / 100;
                    }

                    CurrecnyStrategyContext currencyContext = new CurrecnyStrategyContext(mode);
                    costToRefuel = (float) currencyContext.currencyConvertor(costToRefuel);
                    totalBusCost += costToRefuel;

                    busTable.append("<tr>")
                            .append("<td>").append(busDTO.getVehicleNumber()).append("</td>")
                            .append("<td>").append(busDTO.getResourceType()).append("</td>")
                            .append("<td>").append(String.format("%.2f", remainFuel)).append("%</td>")
                            .append("<td>").append(String.format("%.2f", costToRefuel)).append("</td>")
                            .append("</tr>");
                }
            }

            busTable.append("<tr><td colspan='3'><strong>Total Refuel Cost</strong></td>")
                    .append("<td><strong>").append(String.format("%.2f", totalBusCost)).append("</strong></td></tr>")
                    .append("</table><br>");
            htmlElements.add(busTable.toString());
        }

        // ===== Diesel-Electric Train Report =====
        if (deiselElectricDTOList != null && !deiselElectricDTOList.isEmpty()) {
            StringBuilder tableBuilder = new StringBuilder();
            float totalDieselElectricCost = 0; // Renamed for clarity

            tableBuilder.append("<table border='1' cellpadding='5' cellspacing='0'>");
            tableBuilder.append("<tr><th colspan='4'>Diesel-Electric Train Report</th></tr>");
            tableBuilder.append("<tr>")
                    .append("<th>Diesel-Electric Number</th>")
                    .append("<th>Battery Remaining (%)</th>")
                    .append("<th>Fuel Remaining (%)</th>")
                    .append("<th>Cost to Refuel ($)</th>")
                    .append("</tr>");

            for (VehicleDTO vehicleDTO : vehicleDTOList) {
                if (vehicleDTO instanceof DieselElectricDTO dieselElectricDTO) {
                    float remainBattery = (dieselElectricDTO.getBatteryAmount() / dieselElectricDTO.getMaxBattery()) * 100;
                    float remainFuel = (dieselElectricDTO.getFuelAmount() / dieselElectricDTO.getMaxFuel()) * 100;

                    float costToRefuel = (remainFuel * DIESEL_PRICE + remainBattery * BATTERY_PRICE) / 100;

                    CurrecnyStrategyContext currencyContext = new CurrecnyStrategyContext(mode);
                    costToRefuel = (float) currencyContext.currencyConvertor(costToRefuel);
                    totalDieselElectricCost += costToRefuel; // Add converted cost to total

                    tableBuilder.append("<tr>")
                            .append("<td>").append(dieselElectricDTO.getVehicleNumber()).append("</td>")
                            .append("<td>").append(String.format("%.2f", remainBattery)).append("%</td>")
                            .append("<td>").append(String.format("%.2f", remainFuel)).append("%</td>")
                            .append("<td>").append(String.format("%.2f", costToRefuel)).append("</td>")
                            .append("</tr>");
                }
            }

            tableBuilder.append("<tr><td colspan='3'><strong>Total Refuel Cost</strong></td>")
                    .append("<td><strong>").append(String.format("%.2f", totalDieselElectricCost)).append("</strong></td></tr>") // Use converted total
                    .append("</table><br>");
            htmlElements.add(tableBuilder.toString());
        }

        // ===== Light Rail Report =====
        if (lightRailDTO != null && !lightRailDTO.isEmpty()) {
            StringBuilder lightRailTable = new StringBuilder();
            float totalRechargeCost = 0;

            lightRailTable.append("<table border='1' cellpadding='5' cellspacing='0'>");
            lightRailTable.append("<tr><th colspan='3'>Light Rail Report</th></tr>");
            lightRailTable.append("<tr>")
                    .append("<th>Light Rail Number</th>")
                    .append("<th>Battery Remaining (%)</th>")
                    .append("<th>Cost to Recharge ($)</th>")
                    .append("</tr>");

            for (VehicleDTO vehicleDTO : vehicleDTOList) {
                if (vehicleDTO instanceof LightRailDTO lightDTO) {
                    float remainBattery = lightDTO.getBatteryAmount() / lightDTO.getMaxBattery() * 100;
                    float costToRecharge = (lightDTO.getMaxBattery() - lightDTO.getBatteryAmount()) * BATTERY_PRICE / 100;

                    CurrecnyStrategyContext currencyContext = new CurrecnyStrategyContext(mode);
                    costToRecharge = (float) currencyContext.currencyConvertor(costToRecharge);
                    totalRechargeCost += costToRecharge;

                    lightRailTable.append("<tr>")
                            .append("<td>").append(lightDTO.getVehicleNumber()).append("</td>")
                            .append("<td>").append(String.format("%.2f", remainBattery)).append("%</td>")
                            .append("<td>").append(String.format("%.2f", costToRecharge)).append("</td>")
                            .append("</tr>");
                }
            }

            lightRailTable.append("<tr><td colspan='2'><strong>Total Recharge Cost</strong></td>")
                    .append("<td><strong>").append(String.format("%.2f", totalRechargeCost)).append("</strong></td></tr>")
                    .append("</table><br>");
            htmlElements.add(lightRailTable.toString());
        }

        return htmlElements;
    }

    /**
     * Returns a new instance of the {@link ReportBuilder} to build a {@code Report} object.
     *
     * @return a new {@link ReportBuilder} instance
     */
    public static ReportBuilder builder() {
        return new ReportBuilder();
    }
}