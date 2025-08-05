/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package transportobjects;

/**
 * Enum for notification types.
 * 
 * IMPORTANT: Any changes to this class MUST be changed in the database definition for the Notification
 * Table. I'm aware that this isn't very extensible, but I don't have time to fix it
 *
 * @author Arthur Scharf
 */
public enum NotificationType 
{
    MAINTENANCE,
    FUEL,
    BREAK,
    UNKNOWN;
        
    public static NotificationType fromString(String typeStr)
    {
        String str = typeStr.toLowerCase();
        switch (str.toLowerCase())
        {
            case("maintenance") -> { return NotificationType.MAINTENANCE; }
            case("fuel") -> { return NotificationType.FUEL; }
            case("break") -> { return NotificationType.BREAK; }
            default -> { return NotificationType.UNKNOWN; }   
        }
    }
}
