package controllers;

import db.DatabaseConnection;
import domain.Activity;
import domain.DomainObject;
import domain.Role;
import operations.ListRetriever;
import operations.role.RoleCreation;
import operations.role.RoleDeletion;
import operations.role.RoleUpdate;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;


public class RoleController {

    public static List<Role> getList() throws SQLException {
        List<Role> roles = new LinkedList<>();
        List<DomainObject> ldo = ListRetriever.retrieveByClass(Role.class);
        if(ldo != null)
            ldo.forEach(d -> roles.add((Role) d));
        return roles;
    }

    public static boolean add(Role role) throws SQLException {
        return new RoleCreation().commonExecution(role);
    }

    public static boolean update(Role role) throws SQLException {
        return new RoleUpdate().commonExecution(role);
    }

    public static boolean delete(Role role) throws SQLException {
        return new RoleDeletion().commonExecution(role);
    }



}
