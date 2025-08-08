package db;

import domain.DomainObject;

import java.util.List;

public interface DatabaseBroker {

    /**
     * Establishes a connection to the database.
     *
     * @return true if the connection was successfully established, false otherwise
     */
    public abstract boolean createConnection();

    /**
     * Creates (inserts) a new record in the database.
     *
     * @param domainObject the object to insert
     * @return true if the record was successfully created, false otherwise
     */
    public abstract boolean createRecord(DomainObject domainObject);

    /**
     * Updates an existing record in the database using both the new and old values.
     *
     * @param newDO the updated main object
     * @param oldDO the previous state of the object
     * @return true if the record was successfully updated, false otherwise
     */
    public abstract boolean updateRecord(DomainObject newDO, DomainObject oldDO);

    /**
     * Updates an existing record in the database using only the new values.
     *
     * @param domainObject the updated object
     * @return true if the record was successfully updated, false otherwise
     */
    public abstract boolean updateRecord(DomainObject domainObject);

    /**
     * Deletes a record from the database.
     *
     * @param domainObject the object to delete
     * @return true if the record was successfully deleted, false otherwise
     */
    public abstract boolean deleteRecord(DomainObject domainObject);

    /**
     * Deletes multiple records from the database matching the given WHERE condition.
     *
     * @param domainObject the object type to delete
     * @param where the SQL WHERE clause (without the keyword)
     * @return true if the records were successfully deleted, false otherwise
     */
    public abstract boolean deleteRecords(DomainObject domainObject, String where);

    /**
     * Finds a single record in the database.
     *
     * @param domainObject the  object type to search for
     * @return the found object, or null if not found
     */
    public abstract DomainObject findRecord(DomainObject domainObject);

    /**
     * Finds multiple records in the database matching the given WHERE condition.
     *
     * @param domainObject the object type to search for
     * @param where the SQL WHERE clause (without the keyword)
     * @return a list of matching objects
     */
    public abstract List<DomainObject> findRecords(DomainObject domainObject, String where);

    /**
     * Commits the current transaction.
     *
     * @return true if the commit was successful, false otherwise
     */
    public abstract boolean commitTransaction();

    /**
     * Rolls back the current transaction.
     *
     * @return true if the rollback was successful, false otherwise
     */
    public abstract boolean rollbackTransaction();

    /**
     * Closes the database connection.
     */
    public abstract void closeConnection();

    /**
     * Returns a specific record from a collection by index.
     *
     * @param domainObject the object type
     * @param index the position of the desired record
     * @return the object at the specified index
     */
    public abstract DomainObject getRecord(DomainObject domainObject, int index);

    /**
     * Returns the number of records for the given object type.
     *
     * @param domainObject the object type
     * @return the number of records
     */
    public abstract int getRecordCount(DomainObject domainObject);

    /**
     * Returns the position (index) of a specific record in a collection.
     *
     * @param domainObject the object to locate
     * @return the index position of the record, or -1 if not found
     */
    public abstract int getRecordPosition(DomainObject domainObject);

    /**
     * Checks whether the database connection is currently active.
     *
     * @return true if connected, false otherwise
     */
    public abstract boolean isDatabaseConnected();
}

