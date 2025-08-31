package controllers;

import domain.*;
import operations.ListRetriever;
import operations.evaluation.EvaluationCreation;
import operations.evaluation.EvaluationUpdate;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EvaluationController {

    public static List<Evaluation> getList() throws SQLException {
        var domainEvaluations = ListRetriever.retrieveByClass(Evaluation.class);
        var evaluations = new ArrayList<Evaluation>();
        if(domainEvaluations != null)
            domainEvaluations.forEach(d -> {
                Evaluation e = (Evaluation) d;
                e.setItems(getItemsList(e));
                evaluations.add(e);
            });
        return evaluations;
    }


    public static boolean add(Evaluation evaluation) throws SQLException {
        return new EvaluationCreation().commonExecution(evaluation);
    }


    public static List<EvaluationItem> getItemsList(Evaluation evaluation){
        var doItems = ListRetriever.retrieveByClass(EvaluationItem.class, evaluation);
        List<EvaluationItem> evaluationItems = new ArrayList<>();
        if(doItems != null)
            doItems.forEach(i -> evaluationItems.add((EvaluationItem) i));
        return evaluationItems;
    }


    public static boolean invalidate(Evaluation evaluation) throws SQLException {
        evaluation.setValid(false);
        return new EvaluationUpdate().commonExecution(evaluation);
    }


}
