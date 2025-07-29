/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package viewlayer.command;

import dataaccesslayer.VehicleDAO;
import java.sql.SQLException;
import transportobjects.VehicleDTO;

/** Reverts the creation of a vehicle
 *
 * @author Arthur Scharf
 */
public class UndoCreateVehicle implements UndoCommandInterface 
{
    /**
     * Vehicle to be deleted from the database
     */
    private String vehicleNumber;
    
    /**
     * @param vcl Vehicle to be deleted from the database upon command execution
     */
    public UndoCreateVehicle(VehicleDTO vcl)
    {
        this.vehicleNumber = vcl.getVehicleNumber();
    }
    
    /**
     * @param vehicleNumber Vehicle number used to delete upon command execution
     */
    public UndoCreateVehicle(String vehicleNumber)
    {
        this.vehicleNumber = vehicleNumber;
    }
    
    /** Attempts to remove the vehicle from the database
     * @throws UndoException 
     */
    @Override
    public void execute() throws UndoException
    {
        VehicleDAO dao = new VehicleDAO();
        try {
            if (!dao.delete(vehicleNumber))
            {
                throw new UndoException("UndoCreateVehicle::execute -- Failed to delete vehicle");
            }
        } catch (SQLException e)
        {
            throw new UndoException("UndoCreateVehicle::execute -- " + e.getMessage());
        }
    }
    
}
