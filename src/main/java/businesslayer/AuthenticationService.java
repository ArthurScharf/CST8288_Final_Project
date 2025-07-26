/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package businesslayer;

import dataaccesslayer.DataSource;
import dataaccesslayer.OperatorDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import transportobjects.OperatorDTO;

/**
 * Responsible for Authenticating user credentials.
 * 
 * Interacts directly with the data layer to do this
 * 
 * @author Arthur Scharf
 */
public class AuthenticationService 
{
    /** Authenticates using the passed credentials. If auth fails, null is returned
     * 
     * @param username
     * @param password
     * @return Operator DTO for corresponding valid username & password. null, otherwise
     */
    public static OperatorDTO authenticate(String username, String password) throws Exception
    {        
        OperatorDAO dao = new OperatorDAO();
        try {
            OperatorDTO dto = dao.get(username);
            if (dto.getUsername().equals(username) && dto.getPassword().equals(password))
            { 
                return dto; // Authentication Succeeded
            }
        } catch (SQLException e)
        {
            throw new Exception("EXCEPTION AuthenticationService::authenticate ... While retreiving dto\n"+e.getMessage());
        }
        return null; // Authentication failed
    }
    
//    public static OperatorDTO login(String username, String password) throws Exception
//    {
//        return null;
//    }
//    
//    public static boolean logout(String username, String password) throws Exception
//    {
//        return false;
//    }
}
