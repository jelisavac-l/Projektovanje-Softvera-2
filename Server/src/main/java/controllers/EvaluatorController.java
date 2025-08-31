package controllers;

import db.DatabaseConnection;
import db.MySQLDatabaseBroker;
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

    public static Evaluator login(Evaluator evaluator) throws SQLException {
        if(!new EvaluatorLogin().commonExecution(evaluator)) {
            return null;
        }
        String where = evaluator.getAlias() + ".username='" + evaluator.getUsername() + "'";
        return (Evaluator) MySQLDatabaseBroker.getInstance().findRecords(evaluator, where).get(0);
    }

    public static Evaluator getByCredentials(Evaluator e) throws SQLException {
        throw new UnsupportedOperationException();
    }

    public static boolean add(Evaluator evaluator) throws SQLException {
        return new EvaluatorCreation().commonExecution(evaluator);
    }

    public static boolean update(Evaluator evaluator) throws SQLException {
        return new EvaluatorUpdate().commonExecution(evaluator);
    }

    public static boolean delete(Evaluator evaluator) throws SQLException {
        return new EvaluatorDeletion().commonExecution(evaluator);
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

    public static boolean startRole(Evaluator evaluator, Role role) throws SQLException {
        ER er = new ER(evaluator, role, LocalDate.now(), null);
        return new RoleStart().commonExecution(er);
    }

    public static boolean endRole(Evaluator evaluator, Role role) throws SQLException {
        ER er = new ER(evaluator, role, null, null);
        return new RoleEnd().commonExecution(er);
    }

}
