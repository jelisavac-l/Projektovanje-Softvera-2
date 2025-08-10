package operations;

import db.MySQLDatabaseBroker;
import domain.DomainObject;

import java.util.List;

/**
 * This class provides a generic solution that encompasses system operations 25 through 40 as detailed in
 * the documentation.
 * Note: I am particularly proud of the elegant solution presented in this method.
 */
public class ListRetriever {

    /**
     * Retrieves all records for a given domain object class.
     * <p>
     * This generic method creates an instance of the specified class
     * and uses it to query the database for all matching records.
     *
     * @param domainClass The class of the domain object to retrieve.
     * @param <T>         The type of the domain object, extending {@link DomainObject}.
     * @return A list of all records as {@link DomainObject}s, or {@code null} on failure.
     */
    public static <T extends DomainObject> List<DomainObject> retrieveByClass(Class<T> domainClass) {
        try {
            T domainObjectInstance = domainClass.getConstructor().newInstance();
            MySQLDatabaseBroker.getInstance().createConnection();
            return MySQLDatabaseBroker.getInstance().findRecords(domainObjectInstance);
        } catch (Exception e) {
            System.err.println("Failed to retrieve " + domainClass + " list:");
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Retrieves records for a given domain object class that match specific criteria.
     * <p>
     * This method creates a domain object instance and uses a custom
     * WHERE condition from the criteria object to filter the results.
     *
     * @param domainClass The class of the domain object to retrieve.
     * @param criteria    A domain object containing the WHERE condition for filtering.
     * @param <T>         The type of the domain object, extending {@link DomainObject}.
     * @return A list of matching records as {@link DomainObject}s, or {@code null} on failure.
     */
    public static <T extends DomainObject> List<DomainObject> retrieveByClass(Class<T> domainClass, DomainObject criteria) {
        try {
            T domainObjectInstance = domainClass.getConstructor().newInstance();
            MySQLDatabaseBroker.getInstance().createConnection();
            return MySQLDatabaseBroker.getInstance().findRecords(domainObjectInstance, criteria.getWhereCondition());
        } catch (Exception e) {
            System.err.println("Failed to retrieve " + domainClass + " list:");
            e.printStackTrace();
        }
        return null;
    }
}
