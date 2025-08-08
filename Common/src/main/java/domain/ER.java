package domain;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class ER implements DomainObject {

    private Evaluator evaluator;
    private Role role;
    private LocalDate startDate;
    private LocalDate endDate;

    public ER(Evaluator evaluator, Role role, LocalDate startDate, LocalDate endDate) {
        this.evaluator = evaluator;
        this.role = role;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Evaluator getEvaluator() {
        return evaluator;
    }

    public void setEvaluator(Evaluator evaluator) {
        this.evaluator = evaluator;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    @Override
    public String getAttributeValuesForInsert() {
        return evaluator.getId() + ", " + role.getId() + ", '" + startDate + "', '" + endDate + "'";
    }

    @Override
    public String getUpdatedAttributeValues() {
        return "evaluator=" + evaluator.getId() + ", role=" + role.getId() +
            ", startDate='" + startDate + "', endDate='" + endDate + "'";
    }

    @Override
    public String getColumnNames() {
        return "evaluator, role, startDate, endDate";
    }

    @Override
    public String getTableName() {
        return "ER";
    }

    @Override
    public String getWhereCondition() {
        return "evaluator = " + this.evaluator + " AND role = " + this.role + " AND startDate = " + this.startDate;
    }

    @Override
    public DomainObject getNewRecord(ResultSet rs) throws SQLException {
        return new ER(
            new Evaluator(
                rs.getLong(this.evaluator.getAlias() + ".id"),
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null
            ),
            new Role(
                rs.getLong(this.role.getAlias() + ".id"),
                null
            ),
            rs.getObject(this.getAlias() + ".startDate", java.time.LocalDate.class),
            rs.getObject(this.getAlias() + ".endDate", java.time.LocalDate.class)
        );
    }

    @Override
    public String getPrimaryKey() {
        return "evaluator = " + this.evaluator + " AND role = " + this.role + " AND startDate = " + this.startDate;
    }

    @Override
    public String getJoinClause() {
        return "JOIN " +
            this.evaluator.getTableName() +
            " " +
            this.evaluator.getAlias() +
            " ON " +
            this.evaluator.getAlias() +
            ".id=" +
            this.getAlias() +
            ".evaluator JOIN " +
            this.role.getTableName() +
            " " +
            this.role.getAlias() +
            " ON " +
            this.role.getAlias() +
            ".id=" +
            this.getAlias() +
            ".role";
    }

    @Override
    public String getAlias() {
        return "asoc";
    }

    @Override
    public String getColumnNameByIndex(int i) {
        String[] cols = {"evaluator", "role", "startDate", "endDate"};
        return cols[i];
    }

}
