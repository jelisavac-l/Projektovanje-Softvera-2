package operations.activity;

import domain.DomainObject;
import operations.Operation;

public class ActivityUpdate extends Operation {

    @Override
    public boolean checkConstraints(DomainObject domainObject) {
        return true;
    }

    @Override
    public boolean executeOperation(DomainObject domainObject) {
        return databaseBroker.updateRecord(domainObject);
    }
}
