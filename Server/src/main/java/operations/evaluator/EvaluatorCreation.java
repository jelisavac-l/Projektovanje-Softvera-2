package operations.evaluator;

import domain.DomainObject;
import domain.Evaluator;
import operations.Operation;
import org.mindrot.jbcrypt.BCrypt;

public class EvaluatorCreation extends Operation {

    @Override
    public boolean checkConstraints(DomainObject domainObject) {
        return true;
    }

    @Override
    public boolean executeOperation(DomainObject domainObject) {
        Evaluator evaluator = (Evaluator) domainObject;
        evaluator.setPassword(BCrypt.hashpw(evaluator.getPassword(), BCrypt.gensalt()));
        return databaseBroker.createRecord(evaluator);
    }
}
