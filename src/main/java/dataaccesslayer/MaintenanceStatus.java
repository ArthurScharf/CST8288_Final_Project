package dataaccesslayer;

public enum MaintenanceStatus {
    PENDING,
    IN_PROGRESS,
    COMPLETED,
    CANCELLED;

    // Helper method to convert String from DB to enum
    public static MaintenanceStatus fromString(String statusString) {
        for (MaintenanceStatus status : MaintenanceStatus.values()) {
            if (status.name().equalsIgnoreCase(statusString)) {
                return status;
            }
        }
        // Handle cases where the string doesn't match any enum value
        throw new IllegalArgumentException("Unknown MaintenanceStatus: " + statusString);
    }
}