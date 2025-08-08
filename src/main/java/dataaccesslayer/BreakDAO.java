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
import transportobjects.BreakDTO;

/**
 * Data Access Object for operator breaks
 * 
 * @author Benjamin Gurth
 */
public class BreakDAO implements BreakDAOInterface {

     /**
     * Retrieves all breaks from the database
     * @return ArrayList of all breaks or null if none exist
     * @throws SQLException if database error occurs
     */
    @Override
    public ArrayList<BreakDTO> getAll() throws SQLException {
        ArrayList<BreakDTO> breaks = new ArrayList<>();
        String query = "SELECT b.BreakID, b.OperatorID, b.BreakType, b.Status, " +
                      "CONCAT(o.FirstName, ' ', o.LastName) AS OperatorName " +
                      "FROM Break b " +
                      "LEFT JOIN Operators o ON b.OperatorID = o.OperatorID " +
                      "ORDER BY b.BreakID DESC";

        Connection conn = DataSource.INSTANCE.getConnection();

        try (PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet results = stmt.executeQuery()) {
            
            while (results.next()) {
                BreakDTO breakDto = new BreakDTO();
                breakDto.setBreakId(results.getInt("BreakID"));
                breakDto.setOperatorId(results.getInt("OperatorID"));
                breakDto.setOperatorName(results.getString("OperatorName"));
                breakDto.setBreakType(results.getString("BreakType"));
                breakDto.setStatus(results.getString("Status"));
                breaks.add(breakDto);
            }
        } catch (SQLException e) {
            throw new SQLException("EXCEPTION BreakDAO::getAll ... failed to make query\n" + e.getMessage(), e);
        }
        return breaks.isEmpty() ? null : breaks;
    }

    /**
     * Retrieves breaks for a specific operator
     * @param operatorId the operator ID to search for
     * @return ArrayList of breaks for the operator or null if none exist
     * @throws SQLException if database error occurs
     */
    @Override
    public ArrayList<BreakDTO> getByOperatorId(int operatorId) throws SQLException {
        ArrayList<BreakDTO> breaks = new ArrayList<>();
        String query = "SELECT b.BreakID, b.OperatorID, b.BreakType, b.Status, " +
                      "CONCAT(o.FirstName, ' ', o.LastName) AS OperatorName " +
                      "FROM Break b " +
                      "LEFT JOIN Operators o ON b.OperatorID = o.OperatorID " +
                      "WHERE b.OperatorID = ? " +
                      "ORDER BY b.BreakID DESC";

        Connection conn = DataSource.INSTANCE.getConnection();

        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, operatorId);
            
            try (ResultSet results = stmt.executeQuery()) {
                while (results.next()) {
                    BreakDTO breakDto = new BreakDTO();
                    breakDto.setBreakId(results.getInt("BreakID"));
                    breakDto.setOperatorId(results.getInt("OperatorID"));
                    breakDto.setOperatorName(results.getString("OperatorName"));
                    breakDto.setBreakType(results.getString("BreakType"));
                    breakDto.setStatus(results.getString("Status"));
                    breaks.add(breakDto);
                }
            }
        } catch (SQLException e) {
            throw new SQLException("EXCEPTION BreakDAO::getByOperatorId ... failed to make query\n" + e.getMessage(), e);
        }
        return breaks.isEmpty() ? null : breaks;
    }

    /**
     * @param dto break to create
     * @throws SQLException 
     */
    @Override
    public void create(BreakDTO dto) throws SQLException {
        String query = "INSERT INTO Break (OperatorID, BreakType, Status) VALUES (?, ?, ?)";

        Connection conn = DataSource.INSTANCE.getConnection();

        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, dto.getOperatorId());
            stmt.setString(2, dto.getBreakType());
            stmt.setString(3, dto.getStatus());

            int numRowsAffected = stmt.executeUpdate();

            if (numRowsAffected != 1) {
                throw new SQLException("EXCEPTION BreakDAO::create ... Expected 1 row affected, but got " + numRowsAffected);
            }
        } catch (SQLException e) {
            throw e;
        }
    }

    /**
     * Creates a new break record in the database
     * @param breakId 
     * @throws SQLException if database error occurs
     */
    @Override
    public boolean endBreak(int breakId) throws SQLException {
        String query = "UPDATE Break SET Status = 'COMPLETED' WHERE BreakID = ? AND Status = 'ACTIVE'";

        Connection conn = DataSource.INSTANCE.getConnection();

        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, breakId);

            int numRowsAffected = stmt.executeUpdate();
            return numRowsAffected == 1;
        } catch (SQLException e) {
            throw e;
        }
    }
}