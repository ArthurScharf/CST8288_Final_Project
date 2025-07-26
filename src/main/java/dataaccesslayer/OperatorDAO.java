package dataaccesslayer;

import dataaccesslayer.DataSource;
import dataaccesslayer.OperatorDAOInterface;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import transportobjects.OperatorDTO;



/**
 * Used AI to generate an initial body for this class based on the VehicleDAO I hand wrote.
 * I looked through the code to make sure it was correct and consistent, applying minor edits
 * 
 * @author Arthur Scharf
*/
public class OperatorDAO implements OperatorDAOInterface
{ 

    @Override
    public ArrayList<OperatorDTO> getAll() throws SQLException
    {
        ArrayList<OperatorDTO> dtos = new ArrayList<>();
        String query = "SELECT OperatorID, FirstName, LastName, Role, Username, Password FROM OPERATORS";

        Connection conn = DataSource.INSTANCE.getConnection();

        try (PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet results = stmt.executeQuery())
        {
            while (results.next()) {
                OperatorDTO operator = new OperatorDTO();
                operator.setId(results.getInt("OperatorID"));
                operator.setFirstName(results.getString("FirstName"));
                operator.setLastName(results.getString("LastName"));
                operator.setRole(results.getString("Role"));
                operator.setUsername(results.getString("Username"));
                operator.setPassword(results.getString("Password")); // Be cautious with direct password retrieval in real apps
                dtos.add(operator);
            }
        } catch (SQLException e)
        {
            throw new SQLException("EXCEPTION OperatorDAO::getAll ... failed to make query\n" + e.getMessage(), e);
        }
        return dtos;
    }

    @Override
    public void create(OperatorDTO dto) throws SQLException
    {
        String query = "INSERT INTO OPERATORS (OperatorID, FirstName, LastName, Role, Username, Password) "
                     + "VALUES (?, ?, ?, ?, ?, ?)";

        Connection conn = DataSource.INSTANCE.getConnection();

        try (PreparedStatement stmt = conn.prepareStatement(query))
        {
            stmt.setInt(1, dto.getId());
            stmt.setString(2, dto.getFirstName());
            stmt.setString(3, dto.getLastName());
            stmt.setString(4, dto.getRole());
            stmt.setString(5, dto.getUsername());
            stmt.setString(6, dto.getPassword()); // Again, handle passwords securely in a real app

            int numRowsAffected = stmt.executeUpdate();

            if (numRowsAffected != 1) {
                throw new SQLException("EXCEPTION OperatorDAO::create ... Expected 1 row affected, but got " + numRowsAffected +"\n");
            }
        } catch (SQLException e)
        {
            throw e;
        }
    }

    @Override
    public OperatorDTO get(int id) throws SQLException // Changed parameter name for clarity
    {

        String query = "SELECT OperatorID, FirstName, LastName, Role, Username, Password "
                     + "FROM OPERATORS "
                     + "WHERE OperatorID = ?";

        Connection conn = DataSource.INSTANCE.getConnection();
        OperatorDTO dto = null;

        try (PreparedStatement stmt = conn.prepareStatement(query))
        {
            stmt.setInt(1, id);
            
            try (ResultSet results = stmt.executeQuery())
            {
                if (results.next()) {
                    dto = new OperatorDTO();
                    dto.setId(results.getInt("OperatorID"));
                    dto.setFirstName(results.getString("FirstName"));
                    dto.setLastName(results.getString("LastName"));
                    dto.setRole(results.getString("Role"));
                    dto.setUsername(results.getString("Username"));
                    dto.setPassword(results.getString("Password"));
                    if (!results.isAfterLast())
                    {
                        throw new SQLException("EXCEPTION OperatorDAO::get ... More than one row retrieved\n");
                    }
                }
            }
        } catch (SQLException e)
        {
            throw new SQLException("EXCEPTION OperatorDAO::get ... Potential error when preparing statement\n" + e.getMessage());
        }
        return dto;
    }

    @Override
    public OperatorDTO get(String username) throws SQLException // Changed parameter name for clarity
    {
        String query = "SELECT OperatorID, FirstName, LastName, Role, Username, Password "
                     + "FROM OPERATORS "
                     + "WHERE Username = ?";

        Connection conn = DataSource.INSTANCE.getConnection();
        OperatorDTO dto = null;

        try (PreparedStatement stmt = conn.prepareStatement(query))
        {
            stmt.setString(1, username);
            
            try (ResultSet results = stmt.executeQuery())
            {
                if (results.next()) {
                    dto = new OperatorDTO();
                    dto.setId(results.getInt("OperatorID"));
                    dto.setFirstName(results.getString("FirstName"));
                    dto.setLastName(results.getString("LastName"));
                    dto.setRole(results.getString("Role"));
                    dto.setUsername(results.getString("Username"));
                    dto.setPassword(results.getString("Password"));
                    if (results.next())
                    {
                        throw new SQLException("EXCEPTION OperatorDAO::get ... More than one row retrieved\n");
                    }
                }
            }
        } catch (SQLException e)
        {
            throw new SQLException("EXCEPTION OperatorDAO::get ... Potential error when preparing statement\n" + e.getMessage());
        }
        return dto;
    } 
    
    @Override
    public boolean update(OperatorDTO dto) throws SQLException
    {
        String query = "UPDATE OPERATORS SET "
                     + "FirstName = ?, "
                     + "LastName = ?, "
                     + "Role = ?, "
                     + "Username = ?, "
                     + "Password = ? "
                     + "WHERE OperatorID = ?";

        Connection conn = DataSource.INSTANCE.getConnection();

        try (PreparedStatement stmt = conn.prepareStatement(query))
        {
            stmt.setString(1, dto.getFirstName());
            stmt.setString(2, dto.getLastName());
            stmt.setString(3, dto.getRole());
            stmt.setString(4, dto.getUsername());
            stmt.setString(5, dto.getPassword()); // Password update
            stmt.setInt(6, dto.getId()); // WHERE clause based on OperatorID

            int numRowsAffected = stmt.executeUpdate();

            if (numRowsAffected == 1) return true;
            else if (numRowsAffected == 0) return false; // No record found to update
            else throw new SQLException("EXCEPTION OperatorDAO::update --> More than one row affected for OperatorID: " + dto.getId() + "\n");
        } catch (SQLException e)
        {
            System.err.println("SQL UPDATE FAILED for Operator: " + dto.getId());
            System.err.println("Error Message: " + e.getMessage());
            System.err.println("SQL State: " + e.getSQLState());
            System.err.println("Error Code: " + e.getErrorCode());
            e.printStackTrace();
            throw e;
        }
    }

    // Method to delete an operator
    @Override
    public boolean delete(int id) throws SQLException // Changed parameter name for clarity
    {
        String query = "DELETE FROM OPERATORS WHERE OperatorID = ?";

        Connection conn = DataSource.INSTANCE.getConnection();

        try (PreparedStatement stmt = conn.prepareStatement(query))
        {
            stmt.setInt(1, id);

            int numRowsAffected = stmt.executeUpdate();

            if (numRowsAffected == 1) return true; // Successfully deleted
            else if (numRowsAffected == 0) return false; // No record found to delete
            else throw new SQLException("EXCEPTION OperatorDAO::delete --> More than one row affected for OperatorID: " + id);
        } catch (SQLException e)
        {
            System.err.println("SQL DELETE FAILED for Operator: " + id);
            System.err.println("Error Message: " + e.getMessage());
            System.err.println("SQL State: " + e.getSQLState());
            System.err.println("Error Code: " + e.getErrorCode());
            e.printStackTrace();
            throw e;
        }
    }
}



