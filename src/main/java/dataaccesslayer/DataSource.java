/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dataaccesslayer;

import java.io.InputStream;
import java.io.IOException;
import java.util.Properties;

import java.sql.Connection;
import java.sql.DriverManager;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Arthur Scharf
 */
public enum DataSource 
{
    // SINGLETON Pattern. Threadsafe
    INSTANCE;
    
    private Connection connection;
    
    private String[] info;
    
    private DataSource() throws ExceptionInInitializerError
    {
        info = new String[3];
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            openPropertyFile(info);
            connection = DriverManager.getConnection(info[0], info[1], info[2]);
        } catch (SQLException e) {
            throw new ExceptionInInitializerError(
                    "Critical failure establishing database connection: " 
                    +  "url:      " + info[0] + "\n" 
                    +  "username: " + info[1] + "\n"
                    +  "password: " + info[2] + "\n"
                    + e.getMessage());
        } catch (IOException e) {
            throw new ExceptionInInitializerError(
                       "Critical failure opening `property` file \n" 
                    +  "url:      " + info[0] + "\n" 
                    +  "username: " + info[1] + "\n"
                    +  "password: " + info[2] + "\n"
                    + e.getMessage());
        } catch (ClassNotFoundException e)
        {
            Logger.getLogger(DataSource.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    
    /* This code was heavily inspired by the code from the lectures */
    private static void openPropertyFile(String[] info) throws IOException
    {
        Properties properties = new Properties();
        
        try (InputStream in = DataSource.class.getClassLoader().getResourceAsStream("database.properties"))
        {
            properties.load(in);
        } catch (IOException e)
        {
            throw e;
        }
        
        String url = properties.getProperty("jdbc.url");
        String username = properties.getProperty("jdbc.username");
        String password = properties.getProperty("jdbc.password");
        
        info[0] = url;
        info[1] = username;
        info[2] = password;
    }
    
    public Connection getConnection() { return connection; }
}
