/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package transportobjects;

/**
 * DTO for events that trigger Observers. Stored in the database and processed
 * later upon client access. 
 * 
 * Follows the Java bean convention
 * 
 * @author Arthur Scharf
 */
public class NotificationDTO
{
    private int id;
    private NotificationType type;
    private String data;

    public NotificationDTO()
    {
        id = -1;
        type = NotificationType.UNKNOWN;
        data = "";
    }
    
    public int getId() {
        return id;
    }
    
    public NotificationType getType()
    {
        return type;
    }

    public String getData() {
        return data;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public void setType(NotificationType type)
    {
        this.type = type;
    }

    public void setData(String data) {
        this.data = data;
    }
}
