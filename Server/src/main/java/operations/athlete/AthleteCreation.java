package operations.athlete;

import domain.DomainObject;
import operations.Operation;

public class AthleteCreation extends Operation {

    @Override
    public boolean checkConstraints(DomainObject domainObject) {
        return false;
    }

    @Override
    public boolean executeOperation(DomainObject domainObject) {
        return false;
    }
}
