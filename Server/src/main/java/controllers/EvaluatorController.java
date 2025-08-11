package controllers;

import db.DatabaseConnection;
import domain.Activity;
import domain.DomainObject;
import domain.Evaluator;
import domain.Role;
import operations.ListRetriever;
import operations.evaluator.EvaluatorCreation;
import operations.evaluator.EvaluatorDeletion;
import operations.evaluator.EvaluatorUpdate;

import java.sql.*;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

public class EvaluatorController {

    public static List<Evaluator> getList() throws SQLException {
        List<Evaluator> evaluators = new LinkedList<>();
        List<DomainObject> ldo = ListRetriever.retrieveByClass(Evaluator.class);
        if(ldo != null)
            ldo.forEach(d -> evaluators.add((Evaluator) d));
        return evaluators;
    }

    public static Evaluator getById(Long id) throws SQLException {
        Evaluator evaluator = new Evaluator();
        evaluator.setId(id);
        var list = ListRetriever.retrieveByClass(Evaluator.class, evaluator);
        if(list != null)
            return (Evaluator) list.get(0);
        else return null;
    }

    public static Evaluator getByCredentials(Evaluator e) throws SQLException {
        throw new UnsupportedOperationException();
    }

    public static void add(Evaluator evaluator) throws SQLException {
        new EvaluatorCreation().commonExecution(evaluator);
    }

    public static void update(Evaluator evaluator) throws SQLException {
        new EvaluatorUpdate().commonExecution(evaluator);
    }

    public static void delete(Evaluator evaluator) throws SQLException {
        new EvaluatorDeletion().commonExecution(evaluator);
    }

    public static List<Role> getCurrentRoleList(Evaluator evaluator) throws SQLException {
        throw new UnsupportedOperationException();
    }

    public static List<Role> getAllRoleList(Evaluator evaluator) throws SQLException {
        throw new UnsupportedOperationException();
    }

    public static void startRole(Evaluator evaluator, Role role) throws SQLException {
        throw new UnsupportedOperationException();

    }

    public static void endRole(Evaluator evaluator, Role role) throws SQLException {
        throw new UnsupportedOperationException();
    }

}
