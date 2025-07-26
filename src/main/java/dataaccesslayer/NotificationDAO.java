/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dataaccesslayer;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.PreparedStatement;
import transportobjects.NotificationDTO;
/**
 *
 * @author gurth
 */
public class NotificationDAO implements NotificationDAOInterface {
    
    /**
     * @return ArrayList of all notifications in the database
     * @throws SQLException 
     */
    @Override
    public ArrayList<NotificationDTO> getAll() throws SQLException {
        String query = "SELECT * FROM notification";

        Connection conn = DataSource.INSTANCE.getConnection();

        ArrayList<NotificationDTO> notifications = new ArrayList<>();

        try (Statement stmt = conn.createStatement(); ResultSet results = stmt.executeQuery(query)){
            while (results.next()){
                NotificationDTO dto = new NotificationDTO();
                dto.setNotificationId(results.getInt("notification_id"));
                dto.setVehicleId(results.getString("vehicle_id"));
                dto.setNotificationType(results.getString("notification_type"));
                dto.setData(results.getString("data"));
                notifications.add(dto);
            }
        }
        catch(SQLException e){
            throw e;
        }
        return notifications;
    }
    /**
     * @param dto notification to create in database
     * @throws SQLException 
     */
    @Override
    public void create(NotificationDTO dto) throws SQLException {
        String query = "INSERT INTO notification (vehicle_id, notification_type, data) VALUES (?, ?, ?)";
        Connection conn = DataSource.INSTANCE.getConnection();
        
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, dto.getVehicleId());
            stmt.setString(2, dto.getNotificationType());
            stmt.setString(3, dto.getData());

            int numRowsAffected = stmt.executeUpdate();

            if (numRowsAffected != 1) {
                throw new SQLException("EXCEPTION NotificationDAO::create --> Expected 1 row affected, but got " + numRowsAffected);
            } 
        } catch (SQLException e) {
            throw e;
        }
    }
    
    
      /**
     * @param notificationId
     * @return true if notification was deleted
     * @throws SQLException 
     */
    @Override
    public boolean delete(int notificationId) throws SQLException {
        String query = "DELETE FROM notification WHERE notification_id = ?";
        Connection conn = DataSource.INSTANCE.getConnection();
        
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, notificationId);
            
            int numRowsAffected = stmt.executeUpdate();
            
            if (numRowsAffected == 1) return true;
            
            else if (numRowsAffected == 0) return false;
            
            else throw new SQLException("EXCEPTION NotificationDAO::delete --> more than one row affected");
            
        } catch (SQLException e) {
            throw e;
        }
    }
    
    
     /**
     * @param notificationType
     * @return List of notifications of type: (maintenance, fuel, break)
     * @throws SQLException 
     */
    @Override
    public ArrayList<NotificationDTO> getByType(String notificationType) throws SQLException {
        String query = "SELECT * FROM notification WHERE notification_type = ?";
        Connection conn = DataSource.INSTANCE.getConnection();
        ArrayList<NotificationDTO> notifications = new ArrayList<>();
        
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, notificationType);
            
            try (ResultSet results = stmt.executeQuery()) {
                while (results.next()) {
                    NotificationDTO dto = new NotificationDTO();
                    dto.setNotificationId(results.getInt("notification_id"));
                    dto.setVehicleId(results.getString("vehicle_id"));
                    dto.setNotificationType(results.getString("notification_type"));
                    dto.setData(results.getString("data"));
                    notifications.add(dto);
                }
            }
        } catch (SQLException e) {
            throw e;
        }
        
        return notifications;
    }
    
}
