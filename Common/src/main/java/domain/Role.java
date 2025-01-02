package domain;

import java.io.Serializable;

public class Role extends ADomainClass implements Serializable {

    private Long id;
    private String name;

    @Override
    public String getDBClassName() {
        return "Roles";
    }

    @Override
    public String getAttributeNames() {
        return "id, name";
    }

    public Role(Long id, String name) {
        this.id = id;
        this.name = name;
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

    @Override
    public String toString() {
        return name;
    }
}
