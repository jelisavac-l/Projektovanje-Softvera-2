package operations.activity;

import domain.Activity;
import domain.DomainObject;
import operations.Operation;

public class ActivityCreation extends Operation {

    @Override
    public boolean checkConstraints(DomainObject domainObject) {
        return domainObject instanceof Activity;
    }

    @Override
    public boolean executeOperation(DomainObject domainObject) {
        return databaseBroker.createRecord(domainObject);
    }
}
