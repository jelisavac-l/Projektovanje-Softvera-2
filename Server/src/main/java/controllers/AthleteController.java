package controllers;

import db.DatabaseConnection;

import domain.Athlete;
import domain.Club;
import operations.ListRetriever;
import operations.athlete.AthleteCreation;
import operations.athlete.AthleteDeletion;
import operations.athlete.AthleteUpdate;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class AthleteController {

    public static List<Athlete> getList() throws SQLException {
        var ldo = ListRetriever.retrieveByClass(Athlete.class);
        List<Athlete> athletes = new LinkedList<>();
        if(ldo != null)
            ldo.forEach(d -> athletes.add((Athlete) d));
        return athletes;
    }

    public static Athlete getById(Long id) throws SQLException {
        Athlete athlete = new Athlete(id, null, null, null, null, null, null, null);
        var list = ListRetriever.retrieveByClass(Athlete.class, athlete);
        if (list != null) {
            return (Athlete) list.get(0);
        } else return null;
    }


    public static boolean add(Athlete athlete) throws SQLException {
        return new AthleteCreation().commonExecution(athlete);
    }

    public static boolean update(Athlete athlete) throws SQLException {
        return new AthleteUpdate().commonExecution(athlete);
    }

    public static boolean delete(Athlete athlete) throws SQLException {
        return new AthleteDeletion().commonExecution(athlete);
    }
}
