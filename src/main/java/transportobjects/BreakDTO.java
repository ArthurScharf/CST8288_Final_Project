/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package transportobjects;

import java.io.Serializable;

/**
 * Data Transfer Object for operator breaks
 * 
 * @author Benjamin Gurth
 */
public class BreakDTO implements Serializable {
    
    private int breakId;
    private int operatorId;
    private String operatorName;
    private String breakType;
    private String status;
    
    public BreakDTO() {
        this.breakId = -1;
        this.operatorId = -1;
        this.operatorName = "DEFAULT";
        this.breakType = "REST";
        this.status = "ACTIVE";
    }
    
    public BreakDTO(int operatorId, String breakType) {
        this();
        this.operatorId = operatorId;
        this.breakType = breakType;
    }

    public int getBreakId() {
        return breakId;
    }

    public void setBreakId(int breakId) {
        this.breakId = breakId;
    }

    public int getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(int operatorId) {
        this.operatorId = operatorId;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public String getBreakType() {
        return breakType;
    }

    public void setBreakType(String breakType) {
        this.breakType = breakType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    public boolean isActive() {
        return "ACTIVE".equals(status);
    }
}