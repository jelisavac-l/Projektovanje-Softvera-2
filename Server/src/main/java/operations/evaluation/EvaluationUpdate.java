package operations.evaluation;

import domain.DomainObject;
import operations.Operation;

public class EvaluationUpdate extends Operation {

    @Override
    public boolean checkConstraints(DomainObject domainObject) {
        return false;
    }

    @Override
    public boolean executeOperation(DomainObject domainObject) {
        return false;
    }
}
