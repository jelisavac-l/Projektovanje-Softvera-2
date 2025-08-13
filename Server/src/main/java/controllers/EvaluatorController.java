package controllers;

import db.DatabaseConnection;
import domain.*;
import operations.ListRetriever;
import operations.evaluator.EvaluatorCreation;
import operations.evaluator.EvaluatorDeletion;
import operations.evaluator.EvaluatorLogin;
import operations.evaluator.EvaluatorUpdate;
import operations.role.RoleCreation;
import operations.role.RoleEnd;
import operations.role.RoleStart;
import operations.role.RoleUpdate;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
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

    public static boolean login(Evaluator evaluator) throws SQLException {
        return new EvaluatorLogin().commonExecution(evaluator);
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
        List<Role> roles = new ArrayList<>();
        var ldo = ListRetriever.retrieveByClass(ER.class, evaluator);
        if(ldo != null)
            ldo.forEach(d -> {
                ER e = (ER) d;
                if(e.getEndDate() != null)
                    roles.add(e.getRole());
            });
        return roles;
    }

    public static List<Role> getAllRoleList(Evaluator evaluator) throws SQLException {
        List<Role> roles = new ArrayList<>();
        var ldo = ListRetriever.retrieveByClass(ER.class, evaluator);
        if(ldo != null)
            ldo.forEach(d -> {
                ER e = (ER) d;
                roles.add(e.getRole());
            });
        return roles;
    }

    public static void startRole(Evaluator evaluator, Role role) throws SQLException {
        ER er = new ER(evaluator, role, LocalDate.now(), null);
        new RoleStart().commonExecution(er);
    }

    public static void endRole(Evaluator evaluator, Role role) throws SQLException {
        ER er = new ER(evaluator, role, null, null);
        new RoleEnd().commonExecution(er);
    }

}
