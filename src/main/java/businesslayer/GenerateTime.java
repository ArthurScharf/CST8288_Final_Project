/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package businesslayer;

import dataaccesslayer.StopsDAO;
import dataaccesslayer.VehicleDAO;
import transportobjects.StopsDTO;
import transportobjects.VehicleDTO;
import java.sql.SQLException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * Generates arrival and departure times for vehicles at stops
 * 
 * @author Benjamin Gurth
 */
public class GenerateTime {
    
    private static final Random random = new Random();
    private static final DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
    
    /**
     * Generates arrival times for all vehicles at a specific stop
     * @param stopName the name of the stop
     * @return HashMap with vehicleNumber as key and arrival time as value
     */
    public HashMap<String, String> getArrival(String stopName) {
        HashMap<String, String> arrivalTimes = new HashMap<>();
        
        try {
            VehicleDAO vehicleDAO = new VehicleDAO();
            ArrayList<VehicleDTO> vehicles = vehicleDAO.getAll();
            
            StopsDAO stopsDAO = new StopsDAO();
            StopsDTO stop = stopsDAO.getByStopName(stopName);
            
            if (stop != null && vehicles != null) {
                for (VehicleDTO vehicle : vehicles) {
                    String arrivalTime = generateRandomTime();
                    arrivalTimes.put(vehicle.getVehicleNumber(), arrivalTime);
                }
            }
        } catch (Exception e) {
            System.err.println("Error generating arrival times: " + e.getMessage());
        }
        
        return arrivalTimes;
    }
    
    /**
     * Generates departure times for all vehicles at a specific stop
     * @param stopName the name of the stop
     * @return HashMap with vehicleNumber as key and departure time as value
     */
    public HashMap<String, String> getDeparture(String stopName) {
        HashMap<String, String> departureTimes = new HashMap<>();
        
        try {
            VehicleDAO vehicleDAO = new VehicleDAO();
            ArrayList<VehicleDTO> vehicles = vehicleDAO.getAll();
            
            StopsDAO stopsDAO = new StopsDAO();
            StopsDTO stop = stopsDAO.getByStopName(stopName);
            
            if (stop != null && vehicles != null) {
                HashMap<String, String> arrivalTimes = getArrival(stopName);
                
                for (VehicleDTO vehicle : vehicles) {
                    String vehicleNumber = vehicle.getVehicleNumber();
                    String arrivalTime = arrivalTimes.get(vehicleNumber);
                    
                    if (arrivalTime != null) {
                        String departureTime = generateDepartureTime(arrivalTime);
                        departureTimes.put(vehicleNumber, departureTime);
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("Error generating departure times: " + e.getMessage());
        }
        
        return departureTimes;
    }
    
    /**
     * Generates a random time for arrival
     * @return random time as string in hour:minute:seconds format
     */
    private String generateRandomTime() {
        LocalTime now = LocalTime.now();
        int randomMinutes = random.nextInt(30) - 15;
        LocalTime arrivalTime = now.plusMinutes(randomMinutes);
        return arrivalTime.format(timeFormatter);
    }
    
    /**
     * Generates departure time based on arrival time
     * @param arrivalTimeStr arrival time as string
     * @return departure time as string
     */
    private String generateDepartureTime(String arrivalTimeStr) {
        try {
            LocalTime arrivalTime = LocalTime.parse(arrivalTimeStr, timeFormatter);
            int stopDuration = 2 + random.nextInt(4);
            LocalTime departureTime = arrivalTime.plusMinutes(stopDuration);
            return departureTime.format(timeFormatter);
        } catch (Exception e) {
            System.err.println("Error parsing arrival time: " + e.getMessage());
            return generateRandomTime();
        }
    }
}