package domain;

import java.io.Serializable;

public class Club extends ADomainClass implements Serializable {

    private Long id;
    private String name;
    private String address;

    @Override
    public String getDBClassName() {
        return "Clubs";
    }

    @Override
    public String getAttributeNames() {
        return "id, name, address";
    }

    public Club(Long id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return name;
    }
}
