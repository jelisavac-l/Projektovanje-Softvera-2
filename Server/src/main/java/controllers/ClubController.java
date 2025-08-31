package controllers;

import db.DatabaseConnection;
import domain.Club;
import domain.DomainObject;
import operations.ListRetriever;
import operations.club.ClubCreation;
import operations.club.ClubDeletion;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class ClubController {

    public static List<Club> getList() {
        List<Club> clubs = new LinkedList<>();
        List<DomainObject> ldo = ListRetriever.retrieveByClass(Club.class);
        if(ldo != null)
            ldo.forEach(d -> clubs.add((Club) d));
        return clubs;
    }

    public static Club getById(Long id) {
        Club club = new Club(id, null, null);
        var list = ListRetriever.retrieveByClass(Club.class, club);
        if(list != null)
            return (Club) list.get(0);
        else return null;
    }

    public static boolean add(Club club) throws SQLException {
        return new ClubCreation().commonExecution(club);
    }

    public static boolean update(Club club) throws SQLException {
        return new ClubCreation().commonExecution(club);
    }

    public static boolean delete(Club club) throws SQLException {
        return new ClubDeletion().commonExecution(club);
    }

}
