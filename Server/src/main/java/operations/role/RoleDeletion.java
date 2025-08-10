package operations.role;

import domain.DomainObject;
import operations.Operation;

public class RoleDeletion extends Operation {

    @Override
    public boolean checkConstraints(DomainObject domainObject) {
        return true;
    }

    @Override
    public boolean executeOperation(DomainObject domainObject) {
        return databaseBroker.deleteRecord(domainObject);
    }
}
