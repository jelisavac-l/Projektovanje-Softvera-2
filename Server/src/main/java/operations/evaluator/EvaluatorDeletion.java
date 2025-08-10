package operations.evaluator;

import domain.DomainObject;
import operations.Operation;

public class EvaluatorDeletion extends Operation {

    @Override
    public boolean checkConstraints(DomainObject domainObject) {
        return true;
    }

    @Override
    public boolean executeOperation(DomainObject domainObject) {
        return databaseBroker.deleteRecord(domainObject);
    }
}
