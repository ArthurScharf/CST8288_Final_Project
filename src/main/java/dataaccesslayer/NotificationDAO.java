/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dataaccesslayer;

import transportobjects.NotificationType;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import transportobjects.NotificationDTO;

/**
 *
 * @author Arthur Scharf
 */
public class NotificationDAO implements NotificationDAOInterface
{
    /**
    * @return all notifications in the database, or null if none exist.
    * @throws Exception if a SQL error occurs during the operation.
    */
    public ArrayList<NotificationDTO> getAll() throws Exception {
        String query = "SELECT * FROM Notification"; 

        Connection conn = DataSource.INSTANCE.getConnection();

        ArrayList<NotificationDTO> dtos = new ArrayList<>();

        try (PreparedStatement stmt = conn.prepareStatement(query); ResultSet results = stmt.executeQuery()) 
        {
            System.out.println("\nNumber: " + dtos.size());
            
            while (results.next()) {
                NotificationDTO dto = new NotificationDTO();
                dto.setId(results.getInt("ID"));
                dto.setType(
                    NotificationType.fromString(
                        results.getString("Type")
                    )
                );
                dto.setData(results.getString("Data"));
                dtos.add(dto);
            }
        } catch (SQLException e) {
            throw new Exception("NotificationDAO::getAll -- SQL operation failed: " + e.getMessage(), e);
        }
        return dtos.isEmpty() ? null : dtos;
    }
    
    
    /**
     * @param type The type to return
     * @return all notifications of the passed type or null of none exist
     * @throws Exception 
     */
    public ArrayList<NotificationDTO> getByType(NotificationType type) throws Exception
    {
        String query = "SELECT * FROM Notification WHERE Type = ?";
        
        Connection conn = DataSource.INSTANCE.getConnection();
     
        ArrayList<NotificationDTO> dtos = new ArrayList<NotificationDTO>();
        
        try (PreparedStatement stmt = conn.prepareStatement(query))
        {
            stmt.setString(1, type.toString());
            
            try (ResultSet results = stmt.executeQuery())
            {
                while (results.next())
                {
                   // -- Processing Result Set -- //
                    NotificationDTO dto = new NotificationDTO();
                    dto.setId(results.getInt("ID"));
                    dto.setType(
                        NotificationType.fromString(
                            results.getString("Type")
                        )
                    );
                    dto.setData(results.getString("Data"));
                    dtos.add(dto); 
                }
                
            } catch (SQLException e)
            {
                e.printStackTrace();
                throw new Exception("NotificationDTO::getByType ... SQL query failed: " + e.getMessage(), e);
            }
        } catch (SQLException e)
        {
            throw new Exception("NotificationDTO::getByType" + e.getMessage(), e);
        }
        // returning null is more consistent with rest of system than returning empty
        return (dtos.size() == 0) ? null : dtos; 
    }//~ getByType(...)
    
    
    /**
     * Retrieves a single notification by its ID.
     *
     * @param id The ID of the notification to retrieve.
     * @return The NotificationDTO object if found, or null if not found.
     * @throws Exception if a SQL error occurs during the operation or if the type string is invalid.
     */
    public NotificationDTO getById(int id) throws Exception {
        String query = "SELECT ID, Type, Data FROM Notification WHERE ID = ?";
        Connection conn =  DataSource.INSTANCE.getConnection();

        NotificationDTO dto = null;

        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            try (ResultSet results = stmt.executeQuery()) {
                if (results.next()) {
                    dto = new NotificationDTO();
                    dto.setId(results.getInt("ID"));
                    dto.setType(NotificationType.fromString(results.getString("Type")));
                    dto.setData(results.getString("Data"));
                }
            } catch (SQLException e) {
                e.printStackTrace();
                throw new Exception("NotificationDAO::getById ... SQL query failed: " + e.getMessage(), e);
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
                throw new Exception("NotificationDAO::getById -- Invalid type found in database for ID " + id + ": " + e.getMessage(), e);
            }
        } catch (SQLException e) {
            throw new Exception("NotificationDAO::getById -- SQL operation failed: " + e.getMessage(), e);
        }
        return dto;
    }
    
    
    
    public void create(NotificationDTO dto) throws Exception
    {
        String query = "INSERT INTO Notification (Type, Data) VALUES (?, ?)";

        Connection conn = DataSource.INSTANCE.getConnection();

        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, dto.getType().name()); 
            stmt.setString(2, dto.getData());

            int rowsAffected = stmt.executeUpdate(); 

            if (rowsAffected == 0) {
                throw new Exception("NotificationDTO::create -- No rows affected, notification not created.");
            }
        } catch (SQLException e) {
            throw new Exception("NotificationDAO::create -- SQL operation failed: " + e.getMessage(), e);
        }
    }//~ create(...)
    
    
        /**
     * Deletes a notification from the database by its ID.
     *
     * @param id The ID of the notification to delete.
     * @return true if the notification was successfully deleted (one row affected), false otherwise.
     * @throws Exception if a SQL error occurs during the operation.
     */
    public boolean delete(int id) throws Exception {
        String query = "DELETE FROM Notification WHERE ID = ?";
        Connection conn = DataSource.INSTANCE.getConnection();
        boolean deleted = false;

        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                deleted = true;
            } else {
                System.out.println("NotificationDAO::delete -- No notification found with ID: " + id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("NotificationDAO::delete -- SQL operation failed: " + e.getMessage(), e);
        }
        return deleted;
    }
    
}

