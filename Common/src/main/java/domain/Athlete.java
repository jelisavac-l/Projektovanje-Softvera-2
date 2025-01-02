package domain;

import java.io.Serializable;
import java.time.LocalDate;

public class Athlete extends ADomainClass implements Serializable {

    private Long id;
    private String firstName;
    private String lastName;
    private LocalDate birthday;
    private Boolean gender;
    private Integer height; // cm
    private Double currentWeight; // kg
    private Club club;

    public Athlete(Long id, String firstName, String lastName, LocalDate birthday, Boolean gender, Integer height, Double currentWeight, Club club) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.gender = gender;
        this.height = height;
        this.currentWeight = currentWeight;
        this.club = club;
    }

    @Override
    public String getDBClassName() {
        return "Athletes";
    }

    @Override
    public String getAttributeNames() {
        return "id, firstName, lastName, birthday, gender, height, currentWeight, club";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public Boolean getGender() {
        return gender;
    }

    public void setGender(Boolean gender) {
        this.gender = gender;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Double getCurrentWeight() {
        return currentWeight;
    }

    public void setCurrentWeight(Double currentWeight) {
        this.currentWeight = currentWeight;
    }

    public Club getClub() {
        return club;
    }

    public void setClub(Club club) {
        this.club = club;

    }

    @Override
    public String toString() {
        return firstName + " " + lastName + " (" + club + ")";
    }
}
