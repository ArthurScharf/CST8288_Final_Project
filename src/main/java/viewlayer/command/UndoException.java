/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Exception.java to edit this template
 */
package viewlayer.command;

/**
 *
 * @author Arthur Scharf
 */
public class UndoException extends Exception {

    /**
     * Creates a new instance of <code>UndoException</code> without detail
     * message.
     */
    public UndoException() {
    }

    /**
     * Constructs an instance of <code>UndoException</code> with the specified
     * detail message.
     *
     * @param msg the detail message.
     */
    public UndoException(String msg) {
        super(msg);
    }
}
