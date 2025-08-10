package domain;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class Evaluator implements DomainObject {

    private Long id;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String email;
    private String title;
    private LocalDate hireDate;
    private Boolean active;

    public Evaluator(Long id, String firstName, String lastName, String username, String password, String email, String title, LocalDate hireDate, Boolean active) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.email = email;
        this.title = title;
        this.hireDate = hireDate;
        this.active = active;
    }

    public Evaluator() {}

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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getHireDate() {
        return hireDate;
    }

    public void setHireDate(LocalDate hireDate) {
        this.hireDate = hireDate;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }

    @Override
    public String getAttributeValuesForInsert() {
        return "'" + firstName + "', '" + lastName + "', '" + username + "', '" +
            password + "', '" + email + "', '" + title + "', '" + hireDate + "', " + active;
    }

    @Override
    public String getUpdatedAttributeValues() {
        return "firstName='" + firstName + "', lastName='" + lastName + "', username='" + username +
            "', password='" + password + "', email='" + email + "', title='" + title +
            "', hireDate='" + hireDate + "', active=" + active;
    }

    @Override
    public String getColumnNames() {
        return "firstName, lastName, username, password, email, title, hireDate, active";
    }

    @Override
    public String getTableName() {
        return "Evaluators";
    }

    @Override
    public String getWhereCondition() {
        return "id=" + id;
    }

    @Override
    public DomainObject getNewRecord(ResultSet rs) throws SQLException {
        return new Evaluator(
            rs.getLong(1),
            rs.getString(2),
            rs.getString(3),
            rs.getString(4),
            rs.getString(5),
            rs.getString(6),
            rs.getString(7),
            rs.getObject(8, java.time.LocalDate.class),
            rs.getBoolean(9)
        );
    }

    @Override
    public String getPrimaryKey() {
        return "id=" + id;
    }

    @Override
    public String getJoinClause() {
        return "";
    }

    @Override
    public String getAlias() {
        return "er";
    }

    @Override
    public String getColumnNameByIndex(int i) {
        String[] cols = {"id", "firstName", "lastName", "username", "password", "email", "title", "hireDate", "active"};
        return cols[i];
    }

}
