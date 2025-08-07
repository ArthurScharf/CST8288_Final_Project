/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package transportobjects;

import java.io.Serializable;

/**
 * Data transfer object for bus stops/transit stations.
 * Follows the java bean convention
 * 
 * @author Benjamin Gurth
 */
public class StopsDTO implements Serializable {
    
    private int id;
    private String stopName;
    private String coordinates;
    
    public StopsDTO() {
        this.id = -1;
        this.stopName = "DEFAULT";
        this.coordinates = "";
    }


    public int getId() {
        return id;
    }

    public String getStopName() {
        return stopName;
    }

    public String getCoordinates() {
        return coordinates;
    }

 
    public void setId(int id) {
        this.id = id;
    }

    public void setStopName(String stopName) {
        this.stopName = stopName;
    }

    public void setCoordinates(String coordinates) {
        this.coordinates = coordinates;
    }
    
    @Override
    public String toString() {
        return "StopsDTO{" +
                "id=" + id +
                ", stopName='" + stopName + '\'' +
                ", coordinates='" + coordinates + '\'' +
                '}';
    }
}