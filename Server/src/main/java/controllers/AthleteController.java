package controllers;

import db.DatabaseConnection;

import domain.Athlete;
import domain.Club;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class AthleteController {

    public static List<Athlete> getList() throws SQLException {
        List<Athlete> athleteList = new LinkedList<>();
        Connection conn = DatabaseConnection.getConnection();

        String query = "SELECT * FROM Athletes";
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(query);
        while(rs.next()) {
            Club temp = ClubController.getById(rs.getLong(8));
            athleteList.add(
                    new Athlete(
                            rs.getLong(1),
                            rs.getString(2),
                            rs.getString(3),
                            rs.getDate(4).toLocalDate(),
                            rs.getBoolean(5),
                            rs.getInt(6),
                            rs.getDouble(7),
                            temp
                    )
            );
        }
        return athleteList;
    }

    public static Athlete getById(Long id) throws SQLException {
        Athlete athlete = null;
        Connection conn = DatabaseConnection.getConnection();

        String query = "SELECT * FROM Athletes WHERE id=" + id;
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(query);
        while(rs.next()) {
            Club temp = ClubController.getById(rs.getLong(8));
            athlete = new Athlete(
                    rs.getLong(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getDate(4).toLocalDate(),
                    rs.getBoolean(5),
                    rs.getInt(6),
                    rs.getDouble(7),
                    temp

            );
        }
        return athlete;
    }


    public static void add(Athlete athlete) throws SQLException {
        Connection conn = DatabaseConnection.getConnection();
        String query = "INSERT INTO Athletes(firstName, lastName, birthday, gender, height, currentWeight, club)" +
            "VALUES(?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, athlete.getFirstName());
        ps.setString(2, athlete.getLastName());
        ps.setDate(3, Date.valueOf(athlete.getBirthday()));
        ps.setBoolean(4, athlete.getGender());
        ps.setInt(5, athlete.getHeight());
        ps.setDouble(6, athlete.getCurrentWeight());
        ps.setLong(7, athlete.getClub().getId());

        ps.executeUpdate();
    }

    public static void update(Athlete ath1, Athlete ath2) throws SQLException {
        Connection conn = DatabaseConnection.getConnection();
        String query = "UPDATE Athletes SET firstName=?, lastName=?, birthday=?, gender=?, height=?, currentWeight=?" +
            "club=? WHERE id=?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, ath2.getFirstName());
        ps.setString(2, ath2.getLastName());
        ps.setDate(3, Date.valueOf(ath2.getBirthday()));
        ps.setBoolean(4, ath2.getGender());
        ps.setInt(5, ath2.getHeight());
        ps.setDouble(6, ath2.getCurrentWeight());
        ps.setLong(7, ath2.getClub().getId());
        ps.setLong(8, ath1.getId());

        ps.executeUpdate();
    }

    public static void delete(Athlete athlete) throws SQLException {
        Connection conn = DatabaseConnection.getConnection();
        String query = "DELETE FROM Athletes WHERE id=?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setLong(1, athlete.getId());
        ps.executeUpdate();
    }
}
