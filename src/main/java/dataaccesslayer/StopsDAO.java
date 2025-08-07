package dataaccesslayer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import transportobjects.StopsDTO;

/**
 * Data Access Object for Stops
 * 
 * @author Benjmain Gurth
 */
public class StopsDAO implements StopsDAOInterface {

    /**
     * Retrieves all stops from the database
     * @return ArrayList of all stops or null if none exist
     * @throws SQLException if database error occurs
     */
    @Override
    public ArrayList<StopsDTO> getAll() throws SQLException {
        ArrayList<StopsDTO> stops = new ArrayList<>();
        String query = "SELECT ID, StopName, ST_AsText(Coordinates) as coordinates FROM Stops";

        Connection conn = DataSource.INSTANCE.getConnection();

        try (PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet results = stmt.executeQuery()) {
            
            while (results.next()) {
                StopsDTO stop = new StopsDTO();
                stop.setId(results.getInt("ID"));
                stop.setStopName(results.getString("StopName"));
                stop.setCoordinates(results.getString("coordinates"));
                stops.add(stop);
            }
        } catch (SQLException e) {
            throw new SQLException("EXCEPTION StopsDAO::getAll ... failed to make query\n" + e.getMessage(), e);
        }
        return stops.isEmpty() ? null : stops;
    }

    /**
     * Retrieves a stop by its name
     * @param stopName the name of the stop to search for
     * @return StopsDTO with matching stop name or null if not found
     * @throws SQLException if database error occurs
     */
    @Override
    public StopsDTO getByStopName(String stopName) throws SQLException {
        String query = "SELECT ID, StopName, ST_AsText(Coordinates) as coordinates FROM Stops WHERE StopName = ?";

        Connection conn = DataSource.INSTANCE.getConnection();

        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, stopName);
            
            try (ResultSet result = stmt.executeQuery()) {
                if (result.next()) {
                    StopsDTO stop = new StopsDTO();
                    stop.setId(result.getInt("ID"));
                    stop.setStopName(result.getString("StopName"));
                    stop.setCoordinates(result.getString("coordinates"));
                    return stop;
                }
            }
        } catch (SQLException e) {
            throw new SQLException("EXCEPTION StopsDAO::getByStopName ... failed to make query\n" + e.getMessage(), e);
        }
        return null;
    }

    /**
     * Retrieves a stop by its ID
     * @param id the ID of the stop to search for
     * @return StopsDTO with matching ID or null if not found
     * @throws SQLException if database error occurs
     */
    @Override
    public StopsDTO getById(int id) throws SQLException {
        String query = "SELECT ID, StopName, ST_AsText(Coordinates) as coordinates FROM Stops WHERE ID = ?";

        Connection conn = DataSource.INSTANCE.getConnection();

        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            
            try (ResultSet result = stmt.executeQuery()) {
                if (result.next()) {
                    StopsDTO stop = new StopsDTO();
                    stop.setId(result.getInt("ID"));
                    stop.setStopName(result.getString("StopName"));
                    stop.setCoordinates(result.getString("coordinates"));
                    return stop;
                }
            }
        } catch (SQLException e) {
            throw new SQLException("EXCEPTION StopsDAO::getById ... failed to make query\n" + e.getMessage(), e);
        }
        return null;
    }
}