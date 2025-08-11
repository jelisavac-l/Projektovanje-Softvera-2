package controllers;

import db.DatabaseConnection;
import domain.*;
import operations.ListRetriever;
import operations.evaluation.EvaluationCreation;
import operations.evaluation.EvaluationUpdate;

import java.sql.*;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

public class EvaluationController {

    public static List<Evaluation> getList() throws SQLException {
        throw new UnsupportedOperationException(); // TODO TODO TODO !!!!!
    }


    public static void add(Evaluation evaluation) throws SQLException {
        new EvaluationCreation().commonExecution(evaluation);
    }


    public static List<EvaluationItem> getItemsList(Evaluation evaluation) throws SQLException {
        throw new UnsupportedOperationException(); // TODO TODO TODO !!!!!
    }


    public static void invalidate(Evaluation evaluation) throws SQLException {
        evaluation.setValid(false);
        new EvaluationUpdate().commonExecution(evaluation);
    }


}
