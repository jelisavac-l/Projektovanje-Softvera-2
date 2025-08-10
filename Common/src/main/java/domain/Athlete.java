package domain;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class Athlete implements DomainObject {

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

    public Athlete() {}

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

    public void resolveNullPointers() {
        if(this.club == null) this.club = new Club();
    }

    @Override
    public String toString() {
        return firstName + " " + lastName + " (" + club + ")";
    }

    @Override
    public String getAttributeValuesForInsert() {
        return "'" + this.firstName + "', '" +
            this.lastName + "', '" +
            this.birthday + "', " +
            this.gender + ", " +
            this.height + ", " +
            this.currentWeight + ", " +
            this.club.getId();
    }

    @Override
    public String getUpdatedAttributeValues() {
        return "firstName='" + firstName + "', " +
            "lastName='" + lastName + "', " +
            "birthday='" + birthday + "', " +
            "gender=" + gender + ", " +
            "height=" + height + ", " +
            "currentWeight=" + currentWeight + ", " +
            "club=" + club.getId();
    }

    @Override
    public String getColumnNames() {
        return "firstName, lastName, birthday, gender, height, currentWeight, club";
    }

    @Override
    public String getTableName() {
        return "Athletes";
    }

    @Override
    public String getWhereCondition() {
        return this.getAlias() + ".id=" + id;
    }

    @Override
    public DomainObject getNewRecord(ResultSet rs) throws SQLException {
        Club c = new Club(
            rs.getLong(this.club.getAlias() + ".id"),
            rs.getString(this.club.getAlias() + ".name"),
            rs.getString(this.club.getAlias() + ".address"));
        return new Athlete(
            rs.getLong(getAlias() + ".id"),
            rs.getString(getAlias() + ".firstName"),
            rs.getString(getAlias() + ".lastName"),
            rs.getObject(getAlias() + ".birthday", LocalDate.class),
            rs.getBoolean(getAlias() + ".gender"),
            rs.getInt(getAlias() + ".height"),
            rs.getDouble(getAlias() + ".currentWeight"),
            c
        );
    }

    @Override
    public String getPrimaryKey() {
        return "id=" + id;
    }

    @Override
    public String getJoinClause() {
        resolveNullPointers();
        return "JOIN " +
            this.club.getTableName() +
            " " +
            this.club.getAlias() +
            " ON " +
            this.club.getAlias() +
            ".id=" +
            this.getAlias() +
            ".club";
    }

    @Override
    public String getAlias() {
        return "al";
    }

    @Override
    public String getColumnNameByIndex(int i) {
        String[] cols = {"id", "firstName", "lastName", "birthday", "gender", "height", "currentWeight", "club"};
        return cols[i];
    }
}
