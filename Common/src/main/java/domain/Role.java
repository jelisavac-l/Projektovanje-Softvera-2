package domain;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Role implements DomainObject {

    private Long id;
    private String name;

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

    @Override
    public String getAttributeValuesForInsert() {
        return "'" + name + "'";
    }

    @Override
    public String getUpdatedAttributeValues() {
        return "name='" + name + "'";
    }

    @Override
    public String getColumnNames() {
        return "name";
    }

    @Override
    public String getTableName() {
        return "Roles";
    }

    @Override
    public String getWhereCondition() {
        return "id=" + id;
    }

    @Override
    public DomainObject getNewRecord(ResultSet rs) throws SQLException {
        return new Role(
            rs.getLong(1),
            rs.getString(2)
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
        return "r";
    }

    @Override
    public String getColumnNameByIndex(int i) {
        String[] cols = {"id", "name"};
        return cols[i];
    }

}
