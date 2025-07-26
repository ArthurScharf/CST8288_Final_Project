/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dataaccesslayer;

import java.sql.SQLException;
import java.util.ArrayList;
import transportobjects.VehicleDTO;

/**
 * The interface for Vehicles stored in the database
 * 
 * @author Arthur Scharf
 */
public interface  VehicleDAOInterface 
{
    /**
     * @return List of all DTO's
     * @throws SQLException 
     */
    public ArrayList<VehicleDTO> getAll() throws SQLException;
    
    
    /**
     * @param vehicleNumbers ArrayList of vehicle numbers used to return corresponding DTOs
     * @return List of DTOs who's vehicleNumber's match any of those passed on the query
     * @throws SQLException 
     */
    // public ArrayList<VehicleDTO> getByVehicleNumber(ArrayList<String> vehicleNumbers) throws SQLException;
    
    /**
     * @param dto used to create the database object
     * @return true if vehicle was successfully created
     * @throws SQLException 
     */
    public boolean create(VehicleDTO dto) throws SQLException;
    
    /**
     * @param vehicleNumber
     * @return VehicleDTO with number matching input
     * @throws SQLException 
     */
    public VehicleDTO get(String vehicleNumber) throws SQLException;
    
    /**
     * @param vehicleNumber
     * @return true if vehicle was updated
     * @throws SQLException 
     */
    public boolean update(VehicleDTO dto) throws SQLException;
    
    /**
     * @param vehicleNumber
     * @return true if vehicle was deleted
     * @throws SQLException 
     */
    public boolean delete(String vehicleNumber) throws SQLException;
}
