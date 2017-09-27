package ro.bbasilescu.bogdanbasilescu.presentation.models;

public abstract class AbsBaseEntity {
    private int id;
    private String name;

    public AbsBaseEntity() {
        // Default empty constructor
    }

    public AbsBaseEntity(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
