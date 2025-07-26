/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dataaccesslayer;

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
    public ArrayList<VehicleDTO> getAll() throws SQLException 
    {
        String query = "SELECT * FROM vehicle";
        
        Connection conn = DataSource.INSTANCE.getConnection();
        
        ArrayList<VehicleDTO> dtos = new ArrayList<>();
        
        try (Statement stmt = conn.createStatement(); ResultSet results = stmt.executeQuery(query))
        {
            while (results.next()) // Creating DTOs
            {
                VehicleDTO dto = new VehicleDTO();
                dto.setVehicleNumber(results.getString("VehicleNumber"));
                dto.setType(results.getString("Type"));
                dto.setMaximumPassengers(results.getInt("MaximumPassengers"));
                dto.setTotalDistanceTraveled(results.getFloat("TotalDistanceTraveled"));
                dtos.add(dto);
            }
        } catch (SQLException e)
        {
            throw new SQLException("SQL Exception while retreiving all vehicles" + e.getMessage(), e);
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
    public void create(VehicleDTO dto) throws SQLException 
    {
        String query = "INSERT INTO Vehicle (VehicleNumber, Type, MaximumPassengers, TotalDistanceTraveled) "
                     + "VALUES (?, ?, ?, ?)";

        Connection conn = DataSource.INSTANCE.getConnection();

        try (PreparedStatement stmt = conn.prepareStatement(query))
        {

            stmt.setString(1, dto.getVehicleNumber());         // VehicleNumber (String)
            stmt.setString(2, dto.getType());                  // Type (String)
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
    public VehicleDTO get(String vehicleNumber) throws SQLException 
    {
        String query = "SELECT VehicleNumber, Type, MaximumPassengers, TotalDistanceTraveled "
                     + "FROM Vehicle "
                     + "WHERE VehicleNumber = ?";

        Connection conn = DataSource.INSTANCE.getConnection();

        VehicleDTO dto = null;

        try (PreparedStatement stmt = conn.prepareStatement(query))
        {
            stmt.setString(1, vehicleNumber);

            try (ResultSet results = stmt.executeQuery())
            {
                if (results.next()) 
                {
                    dto = new VehicleDTO();
                    dto.setVehicleNumber(results.getString("VehicleNumber"));
                    dto.setType(results.getString("Type"));
                    dto.setMaximumPassengers(results.getInt("MaximumPassengers"));
                    dto.setTotalDistanceTraveled(results.getFloat("TotalDistanceTraveled"));
                }
            }
        } catch (SQLException e)
        {
            throw e;
        }

        return dto; // Return the populated DTO or null if not found.
    }

    
    @Override
    public boolean update(VehicleDTO dto) throws SQLException 
    {
        String query = "UPDATE Vehicle "
                + "SET VehicleNumber = ?, "
                + "Type = ?, "
                + "MaximumPassengers = ?, "
                + "TotalDistanceTraveled = ? "
                + "WHERE VehicleNumber = ? ";
        
        Connection conn = DataSource.INSTANCE.getConnection();
        
        try (PreparedStatement stmt = conn.prepareStatement(query))
        {
            stmt.setString(1, dto.getVehicleNumber());
            stmt.setString(2, dto.getType());
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
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
