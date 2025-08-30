package operations;

import db.DatabaseBroker;
import db.MySQLDatabaseBroker;
import domain.DomainObject;

import javax.xml.crypto.Data;
import java.sql.SQLException;

public abstract class Operation {

    protected DatabaseBroker databaseBroker = MySQLDatabaseBroker.getInstance();

    /**
     * Executes a system operation using the Template Method pattern.
     * <p>
     * This method handles the transaction lifecycle by opening a connection,
     * calling abstract methods to check constraints and execute the operation,
     * and then committing or rolling back the transaction based on the result.
     *
     * @param domainObject The data object for the operation.
     * @return {@code true} if the operation was successful; otherwise, {@code false}.
     * @throws SQLException if a database error occurs.
     * @see #checkConstraints(DomainObject)
     * @see #executeOperation(DomainObject)
     */
    synchronized public boolean commonExecution(DomainObject domainObject) throws SQLException {
        boolean signal = false;
        try {
            databaseBroker.createConnection();
            if (checkConstraints(domainObject)) {
                signal = executeOperation(domainObject);
                if(signal) {
                    databaseBroker.commitTransaction();
                } else databaseBroker.rollbackTransaction();
            }
        } catch (Exception e) {
            System.out.println("Rollback: " + e.getMessage());
            databaseBroker.rollbackTransaction();
        }

        /*
        Note: I have observed many of my colleagues opening a new connection for each system operation execution and
             then immediately closing it. I do not consider this to be an optimal solution; however, should I be
             proven wrong, I will adapt to that approach.
        */

        return signal;
    }

    /**
     * Validates the business constraints for the operation.
     * <p>
     * This method is called by the template method before the main operation is executed.
     * Subclasses must implement this to define specific validation rules.
     *
     * @param domainObject The object to validate.
     * @return {@code true} if all constraints are met; {@code false} otherwise.
     */
    public abstract boolean checkConstraints(DomainObject domainObject);

    /**
     * Executes the core logic of the system operation.
     * <p>
     * This method is the main step of the template, called only after constraints
     * have been successfully checked. Subclasses must implement this to define
     * the specific business logic.
     *
     * @param domainObject The data object to be used in the operation.
     * @return {@code true} if the operation was successful; {@code false} otherwise.
     */
    public abstract boolean executeOperation(DomainObject domainObject);


}
