package operations.role;

import domain.DomainObject;
import operations.Operation;

/**
 * Operation for starting a new role (i.e. creating new ER)
 */
public class RoleStart extends Operation {
    @Override
    public boolean checkConstraints(DomainObject domainObject) {
        return true;
    }

    @Override
    public boolean executeOperation(DomainObject domainObject) {
        return databaseBroker.createRecord(domainObject);
    }
}
