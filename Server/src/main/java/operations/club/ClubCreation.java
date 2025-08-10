package operations.club;

import domain.DomainObject;
import operations.Operation;

public class ClubCreation extends Operation {

    @Override
    public boolean checkConstraints(DomainObject domainObject) {
        return true;
    }

    @Override
    public boolean executeOperation(DomainObject domainObject) {
        return databaseBroker.createRecord(domainObject);
    }
}
