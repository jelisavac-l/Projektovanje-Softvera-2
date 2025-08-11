package operations.evaluator;

import domain.DomainObject;
import domain.Evaluator;
import operations.Operation;

public class EvaluatorLogin extends Operation {

    @Override
    public boolean checkConstraints(DomainObject domainObject) {
        Evaluator evaluator = (Evaluator) domainObject;
        return evaluator.getUsername() != null && evaluator.getPassword() != null;
    }

    @Override
    public boolean executeOperation(DomainObject domainObject) {
        Evaluator evaluator = (Evaluator) domainObject;
        String where = evaluator.getAlias() + ".username=" + evaluator.getUsername() + " AND " +
            evaluator.getAlias() + ".password=" + evaluator.getPassword();
        return databaseBroker.findRecords(domainObject, where) != null;
    }
}
