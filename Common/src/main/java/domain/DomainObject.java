package domain;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Represents a generic database entity that can be persisted, updated, retrieved, and mapped
 * from relational database records.
 * <p>
 * Implementations of this interface should provide the logic required for mapping between
 * object fields and database table columns, as well as for generating SQL fragments
 * (e.g., column names, value lists, WHERE clauses, JOINs) for common CRUD operations.
 * <p>
 * This interface extends {@link java.io.Serializable} so that implementing classes
 * can be serialized for network transfer.
 */
public interface DomainObject extends Serializable {

    // Returns attribute values for INSERT
    String getAttributeValuesForInsert();

    // Returns attribute assignments for UPDATE
    String getUpdatedAttributeValues();

    // Returns column names
    String getColumnNames();

    // Returns table name
    String getTableName();

    // Returns WHERE clause condition
    String getWhereCondition();

    // Returns a new domain object created from a ResultSet row
    DomainObject getNewRecord(ResultSet rs) throws SQLException;

    // Returns primary key column name
    String getPrimaryKey();

    // Returns join clause (e.g. for JOIN queries)
    String getJoinClause();

    // Returns table alias
    String getAlias();

    // Returns column name by index
    String getColumnNameByIndex(int i);
}
