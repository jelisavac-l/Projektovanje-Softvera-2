package domain;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Activity implements DomainObject {

    private Long id;
    private String name;
    private String unit;

    public Activity(Long id, String name, String unit) {
        this.id = id;
        this.name = name;
        this.unit = unit;
    }

    public Activity() {}

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

    @Override
    public String getAttributeValuesForInsert() {
        return "'" + this.name + "', '" + this.unit + "'";
    }

    @Override
    public String getUpdatedAttributeValues() {
        return "name='" + name + "', unit='" + unit + "'";
    }

    @Override
    public String getColumnNames() {
        return "name, unit";
    }

    @Override
    public String getTableName() {
        return "Activities";
    }

    @Override
    public String getWhereCondition() {
        return this.getAlias() + ".id =" + id;
    }

    @Override
    public DomainObject getNewRecord(ResultSet rs) throws SQLException {
        return new Activity(rs.getLong(1), rs.getString(2), rs.getString(3));
    }

    @Override
    public String getPrimaryKey() {
        throw new UnsupportedOperationException("Not implemented yet!");
    }

    @Override
    public String getJoinClause() {
        return "";
    }

    @Override
    public String getAlias() {
        return "ac";
    }

    @Override
    public String getColumnNameByIndex(int i) {
        String[] cols = {
            "id",
            "firstName",
            "lastName",
            "birthday",
            "gender",
            "height",
            "currentWeight",
            "club"
        };
        return cols[i];
    }
}
