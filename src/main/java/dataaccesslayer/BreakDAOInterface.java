/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dataaccesslayer;

import java.sql.SQLException;
import java.util.ArrayList;
import transportobjects.BreakDTO;

/**
 * Interface for break database operations
 * 
 * @author Benjamin Gurth
 */
public interface BreakDAOInterface {
    
    /**
     * @return List of all breaks
     * @throws SQLException 
     */
    public ArrayList<BreakDTO> getAll() throws SQLException;
    
    /**
     * @param operatorId operator to search for
     * @return List of breaks for operator
     * @throws SQLException 
     */
    public ArrayList<BreakDTO> getByOperatorId(int operatorId) throws SQLException;
    
    /**
     * @param dto break to create
     * @throws SQLException 
     */
    public void create(BreakDTO dto) throws SQLException;
    
    /**
     * Ends active break
     * @param breakId break ID to end
     * @return true if ended successfully
     * @throws SQLException 
     */
    public boolean endBreak(int breakId) throws SQLException;
}