package operations.evaluator;

import domain.DomainObject;
import domain.Evaluator;
import operations.Operation;
import org.mindrot.jbcrypt.BCrypt;

public class EvaluatorLogin extends Operation {

    @Override
    public boolean checkConstraints(DomainObject domainObject) {
        Evaluator evaluator = (Evaluator) domainObject;
        return evaluator.getUsername() != null && evaluator.getPassword() != null;
    }

    @Override
    public boolean executeOperation(DomainObject domainObject) {
        Evaluator evaluator = (Evaluator) domainObject;
        String where = evaluator.getAlias() + ".username=" + evaluator.getUsername();
        Evaluator found = (Evaluator) databaseBroker.findRecords(domainObject, where);
        return BCrypt.checkpw(evaluator.getPassword(), found.getPassword());
    }
}
