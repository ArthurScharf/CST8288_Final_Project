/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package businesslayer;

import java.util.ArrayList;

/**
 *
 * @author User
 */
public class TestDataGenerator 
{
    public static ArrayList<String> execute()
    {
        ArrayList<String> results = new ArrayList<>();
        results.add("Hello World");
        results.add("Goodbye Moon");
        results.add("Visiting Jupiter");
        
        return results;
        /* Setting an attribute in request scope. The JSP that includes this
           servlet will have access to this information
        */
        // request.setAttribute("results", results);
    }
}
