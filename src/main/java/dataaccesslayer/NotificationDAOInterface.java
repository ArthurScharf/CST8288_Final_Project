/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dataaccesslayer;

import java.util.ArrayList;
import transportobjects.NotificationDTO;

/**
 * @author Arthur Scharf
 */
public interface NotificationDAOInterface 
{
    public ArrayList<NotificationDTO> getByType(NotificationType type) throws Exception;
    
    public void create(NotificationDTO dto) throws Exception;
}
