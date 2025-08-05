/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dataaccesslayer;

import businesslayer.VehicleService;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import transportobjects.VehicleDTO;

/**
 * @author Arthur Scharf
 */
public class VehicleDAO implements VehicleDAOInterface
{
    /**
     * @return ArrayList of all vehicles in the database
     * @throws SQLException 
     */
    @Override
    public ArrayList<VehicleDTO> getAll() throws SQLException, Exception
    {
        String query = "SELECT * FROM vehicle";
        
        Connection conn = DataSource.INSTANCE.getConnection();
        
        ArrayList<VehicleDTO> dtos = new ArrayList<>();
        
        try (Statement stmt = conn.createStatement(); ResultSet results = stmt.executeQuery(query))
        {
            while (results.next()) // Creating DTOs
            {
                VehicleDTO dto = VehicleService.createVehicle(
                    results.getString("VehicleNumber"),
                    results.getInt("MaximumPassengers"),
                    results.getInt("RouteID"),
                    results.getFloat("TotalDistanceTraveled"),
                    results.getString("TypeInfo")
                );
                dtos.add(dto);
            }
        } catch (SQLException e)
        {
            // throw new SQLException("SQL Exception while retreiving all vehicles" + e.getMessage(), e);
            throw e;
        } catch (Exception e)
        {
            throw e;
        }
        return dtos;
    }

    /**
     * 
     * @param dto
     * @return true if vehicle was successfully created
     * @throws SQLException 
     */
    @Override
    public void create(VehicleDTO dto, String typeInfo) throws SQLException 
    {
        String query = "INSERT INTO Vehicle (VehicleNumber, TypeInfo, MaximumPassengers, TotalDistanceTraveled) "
                     + "VALUES (?, ?, ?, ?)";

        Connection conn = DataSource.INSTANCE.getConnection();

        try (PreparedStatement stmt = conn.prepareStatement(query))
        {

            stmt.setString(1, dto.getVehicleNumber());         // VehicleNumber (String)
            stmt.setString(2, typeInfo);                       // Type Info (String)
            stmt.setInt(3, dto.getMaximumPassengers());        // MaximumPassengers (int)
            stmt.setDouble(4, dto.getTotalDistanceTraveled()); // Assuming getTotalDistanceTraveled() returns double

            int numRowsAffected = stmt.executeUpdate();

            if (numRowsAffected != 1) 
            {
                throw new SQLException("EXCEPTION VehicleDAO::create ... Expected 1 row affected, but got " + numRowsAffected);
            } 
        } catch (SQLException e)
        {
            throw e;
        }
    }

    @Override
    public VehicleDTO get(String vehicleNumber) throws SQLException, Exception
    {
        String query = "SELECT VehicleNumber, TypeInfo, MaximumPassengers, TotalDistanceTraveled "
                     + "FROM Vehicle "
                     + "WHERE VehicleNumber = ?";

        Connection conn = DataSource.INSTANCE.getConnection();

        VehicleDTO dto = null;

        try (PreparedStatement stmt = conn.prepareStatement(query))
        {
            stmt.setString(1, vehicleNumber);

            try (ResultSet result = stmt.executeQuery())
            {
                if (result.next()) 
                {
                    dto = VehicleService.createVehicle(
                            vehicleNumber, 
                            result.getInt("MaximumPassengers"),
                            result.getInt("RouteID"), 
                            result.getFloat("TotalDistanceTraveled"), 
                            result.getString("TypeInfo")
                    );
                }
            }
        } catch (SQLException e)
        {
            throw e;
        } catch (Exception e)
        {
            throw e;
        }

        return dto; // Return the populated DTO or null if not found.
    }

    
    @Override
    public boolean update(VehicleDTO dto, String typeInfo) throws SQLException, Exception
    {
        String query = "UPDATE Vehicle "
                + "SET VehicleNumber = ?, "
                + "TypeInfo = ?, "
                + "MaximumPassengers = ?, "
                + "TotalDistanceTraveled = ? "
                + "WHERE VehicleNumber = ? ";
        
        Connection conn = DataSource.INSTANCE.getConnection();
        
        try (PreparedStatement stmt = conn.prepareStatement(query))
        {
            stmt.setString(1, dto.getVehicleNumber());
            stmt.setString(2, typeInfo);
            stmt.setInt(3, dto.getMaximumPassengers());
            stmt.setFloat(4, dto.getTotalDistanceTraveled());
            stmt.setString(5, dto.getVehicleNumber());
            
            int numRowsAffected = stmt.executeUpdate();
            
            if (numRowsAffected == 1) return true;
            else if (numRowsAffected == 0) return false;
            else throw new SQLException("EXCEPTION VehicleDAO::update --> more than one row affected");
        } catch (SQLException e)
        {
            throw e; // Does this need to be here?
        }
    }

    @Override
    public boolean delete(String vehicleNumber) throws SQLException 
    {
        String query = "DELETE FROM Vehicle WHERE VehicleNumber = ?";

        Connection conn = DataSource.INSTANCE.getConnection();

        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, vehicleNumber);

            int numRowsAffected = stmt.executeUpdate();

            return numRowsAffected == 1; // Returns true if one row was deleted, false otherwise
        } catch (SQLException e) {
            throw e; // Re-throw the SQLException for the calling layer to handle
        }
    }
    
    
    
}
