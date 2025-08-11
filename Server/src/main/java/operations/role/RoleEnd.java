package operations.role;

import domain.DomainObject;
import domain.ER;
import domain.Role;
import operations.Operation;

import java.time.LocalDate;

public class RoleEnd extends Operation {

    @Override
    public boolean checkConstraints(DomainObject domainObject) {
        return domainObject instanceof ER;
    }

    @Override
    public boolean executeOperation(DomainObject domainObject) {
        ER r = (ER) domainObject;
        r.setEndDate(LocalDate.now());
        return databaseBroker.updateRecord(r);
    }
}
