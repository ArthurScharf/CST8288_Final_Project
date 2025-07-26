/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package dataaccesslayer;

import java.sql.SQLException;
import java.util.ArrayList;
import transportobjects.OperatorDTO;

/**
 * The interface for Vehicles stored in the database
 * 
 * @author Arthur Scharf
 */
public interface  OperatorDAOInterface 
{
    /**
     * @return List of all DTO's
     * @throws SQLException 
     */
    public ArrayList<OperatorDTO> getAll() throws SQLException;
    
    
    /**
     * @param dto used to create the database object
     * @return true 
     * @throws SQLException 
     */
    public void create(OperatorDTO dto) throws SQLException;
    
    /** Search by primary key
     * 
     * @param id
     * @return VehicleDTO with number matching input
     * @throws SQLException 
     */
    public OperatorDTO get(int id) throws SQLException;
    
    /** Search by alternate key
     * 
     * @param id
     * @return
     * @throws SQLException 
     */
    public OperatorDTO get(String username) throws SQLException;
    
    /**
     * @param vehicleNumber
     * @return true if vehicle was updated
     * @throws SQLException 
     */
    public boolean update(OperatorDTO dto) throws SQLException;
    
    /**
     * @param vehicleNumber
     * @return true if vehicle was deleted
     * @throws SQLException 
     */
    public boolean delete(int id) throws SQLException;
}
