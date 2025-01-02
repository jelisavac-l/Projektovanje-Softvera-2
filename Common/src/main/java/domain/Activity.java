package domain;

import java.io.Serializable;

public class Activity extends ADomainClass implements Serializable {

    private Long id;
    private String name;
    private String unit;

    @Override
    public String getDBClassName() {
        return "Activities";
    }

    @Override
    public String getAttributeNames() {
        return "id, name, unit";
    }

    public Activity(Long id, String name, String unit) {
        this.id = id;
        this.name = name;
        this.unit = unit;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    @Override
    public String toString() {
        return name + " (" + unit + ")";

    }
}
