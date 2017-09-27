package ro.bbasilescu.bogdanbasilescu.presentation.models;

public class Project extends AbsBaseEntity {
    private String description;
    private String location;

    // Default empty constructor
    public Project() {
        super();
    }

    public Project(int id, String name, String description, String location) {
        super(id, name);
        this.description = description;
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
