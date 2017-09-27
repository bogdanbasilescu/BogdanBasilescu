package ro.bbasilescu.bogdanbasilescu.presentation.models;

public class User extends AbsBaseEntity {
    private String description;
    private String objective;

    // Default empty constructor
    public User() {
        super();
    }

    public User(int id, String name, String description, String objective) {
        super(id, name);
        this.description = description;
        this.objective = objective;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getObjective() {
        return objective;
    }

    public void setObjective(String objective) {
        this.objective = objective;
    }
}
