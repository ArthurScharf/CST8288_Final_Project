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
import transportobjects.NotificationDTO;
/**
 *
 * @author gurth
 */
public class NotificationDAO implements NotificationDAOInterface {
    
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
}
