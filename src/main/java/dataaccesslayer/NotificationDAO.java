/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dataaccesslayer;

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
}

