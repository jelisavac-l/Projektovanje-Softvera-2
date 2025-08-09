package operations.club;

import domain.DomainObject;
import operations.Operation;

public class ClubUpdate extends Operation {

    @Override
    public boolean checkConstraints(DomainObject domainObject) {
        return false;
    }

    @Override
    public boolean executeOperation(DomainObject domainObject) {
        return false;
    }
}
