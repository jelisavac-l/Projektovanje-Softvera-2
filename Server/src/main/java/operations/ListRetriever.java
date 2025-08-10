package operations;

import db.MySQLDatabaseBroker;
import domain.DomainObject;

import java.util.List;

public class ListRetriever {

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
}
