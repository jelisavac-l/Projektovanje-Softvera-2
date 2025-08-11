package operations.evaluation;

import domain.DomainObject;
import domain.Evaluation;
import domain.EvaluationItem;
import operations.Operation;

import java.util.List;

public class EvaluationCreation extends Operation {

    @Override
    public boolean checkConstraints(DomainObject domainObject) {
        return domainObject instanceof Evaluation;
    }

    @Override
    public boolean executeOperation(DomainObject domainObject) {
        Evaluation evaluation = (Evaluation) domainObject;
        var signal = databaseBroker.createRecord(evaluation);
        List<EvaluationItem> items = evaluation.getItems();
        for (EvaluationItem item : items) {
            signal = signal && databaseBroker.createRecord(item);
        }
        return signal;
    }
}
