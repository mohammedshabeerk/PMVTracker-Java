package co.ccc.pmv.enums;

public enum Role {

    ADMIN("Admin"),
    SERVICE_ENGINEER("Service Engineer"),
    DRIVER("Driver"),
    MANAGER("Manager");

    private final String description;

    Role(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
