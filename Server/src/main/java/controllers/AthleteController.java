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


    public static void add(Athlete athlete) throws SQLException {
        new AthleteCreation().commonExecution(athlete);
    }

    public static void update(Athlete athlete) throws SQLException {
        new AthleteUpdate().commonExecution(athlete);
    }

    public static void delete(Athlete athlete) throws SQLException {
        new AthleteDeletion().commonExecution(athlete);
    }
}
