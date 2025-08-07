/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dataaccesslayer;

import java.sql.SQLException;
import java.util.ArrayList;
import transportobjects.StopsDTO;

/**
 * The interface for Stops stored in the database
 * 
 * @author Benjamin Gurth
 */
public interface StopsDAOInterface {
    
    /**
     * @return List of all stops
     * @throws SQLException 
     */
    public ArrayList<StopsDTO> getAll() throws SQLException;
    
    /**
     * @param stopName
     * @return StopsDTO with name matching input
     * @throws SQLException 
     */
    public StopsDTO getByStopName(String stopName) throws SQLException;
    
    /**
     * @param id
     * @return StopsDTO with ID matching input
     * @throws SQLException 
     */
    public StopsDTO getById(int id) throws SQLException;
}