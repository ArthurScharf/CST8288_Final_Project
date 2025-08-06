package transportobjects; // Assuming this is your DTOs package

import dataaccesslayer.MaintenanceStatus;




/**
 * Data Transfer Object for MaintenanceTask table.
 * Represents a single row from the MaintenanceTask table.
 */
public class MaintenanceTaskDTO {
    private int taskId;
    private String description;
    private MaintenanceStatus status;

    // Default constructor
    public MaintenanceTaskDTO() {
    }

    // Constructor with all fields
    public MaintenanceTaskDTO(int taskId, String description, MaintenanceStatus status) {
        this.taskId = taskId;
        this.description = description;
        this.status = status;
    }

    // --- Getters ---
    public int getTaskId() {
        return taskId;
    }

    public String getDescription() {
        return description;
    }

    public MaintenanceStatus getStatus() {
        return status;
    }

    // --- Setters ---
    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStatus(MaintenanceStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "MaintenanceTaskDTO{" +
               "taskId=" + taskId +
               ", description='" + description + '\'' +
               ", status=" + status +
               '}';
    }
}
