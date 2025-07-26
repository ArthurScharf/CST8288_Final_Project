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
            throw new SQLException("SQL Exception while retreiving all vehicles", e);
        }
        return dtos;
    }

    @Override
    public boolean create(VehicleDTO dto) throws SQLException 
    {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public VehicleDTO get(String vehicleNumber) throws SQLException 
    {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
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
        /*
        1 = num
        2 = type
        3. max
        4. dist
        */
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
