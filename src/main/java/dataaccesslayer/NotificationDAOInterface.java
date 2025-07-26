/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dataaccesslayer;

import java.sql.SQLException;
import java.util.ArrayList;
import transportobjects.NotificationDTO;
/**
 *
 * @author gurth
 */
public interface NotificationDAOInterface {
    
    /**
     * @return List of all notification DTOs
     * @throws SQLException 
     */
    public ArrayList<NotificationDTO> getAll() throws SQLException;
    
    /**
     * @param dto used to create the database object
     * @throws SQLException 
     */
    public void create(NotificationDTO dto) throws SQLException;
    
    /**
     * @param notificationId
     * @return true if notification was deleted
     * @throws SQLException 
     */
    public boolean delete(int notificationId) throws SQLException;
    
    /**
     * @param notificationType
     * @return List of notifications of type: (maintenance, fuel, break)
     * @throws SQLException 
     */
    public ArrayList<NotificationDTO> getByType(String notificationType) throws SQLException;
}

