package domain;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Club implements DomainObject {

    private Long id;
    private String name;
    private String address;

    public Club(Long id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }

    public Club() {}

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

    @Override
    public String getAttributeValuesForInsert() {
        return "'" + name + "', '" + address + "'";
    }

    @Override
    public String getUpdatedAttributeValues() {
        return "name='" + name + "', address='" + address + "'";
    }

    @Override
    public String getColumnNames() {
        return "name, address";
    }

    @Override
    public String getTableName() {
        return "Clubs";
    }

    @Override
    public String getWhereCondition() {
        return this.getAlias() + ".id=" + id;
    }

    @Override
    public DomainObject getNewRecord(ResultSet rs) throws SQLException {
        return new Club(
            rs.getLong(1),   // id
            rs.getString(2), // name
            rs.getString(3)  // address
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
        return "c";
    }

    @Override
    public String getColumnNameByIndex(int i) {
        String[] cols = {"id", "name", "address"};
        return cols[i];
    }

}
