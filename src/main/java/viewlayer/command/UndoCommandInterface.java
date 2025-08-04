/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package viewlayer.command;

/**
 *
 * @author Arthur Scharf
 */
public interface UndoCommandInterface 
{
    public void execute() throws UndoException;
}
