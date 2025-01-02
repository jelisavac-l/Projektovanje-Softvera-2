package controllers;

import db.DatabaseConnection;
import domain.Athlete;
import domain.Club;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class ClubController {

    public static List<Club> getList() throws SQLException {
        List<Club> clubList = new LinkedList<>();
        Connection conn = DatabaseConnection.getConnection();

        String query = "SELECT * FROM Clubs";
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(query);
        while (rs.next()) {
            clubList.add(new Club(
                rs.getLong(1),
                rs.getString(2),
                rs.getString(3)
            ));
        }

        return clubList;
    }

    public static Club getById(Long id) throws SQLException {
        Connection conn = DatabaseConnection.getConnection();

        String query = "SELECT * FROM Clubs WHERE id=" + id;
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(query);
        if (rs.next()) {
            return new Club(
                rs.getLong(1),
                rs.getString(2),
                rs.getString(3)
            );
        } else throw new IllegalArgumentException("Invalid id.");
    }

    public static void add(Club c) throws SQLException {
        Connection conn = DatabaseConnection.getConnection();
        String query = "INSERT INTO Clubs(name, address) VALUES(?, ?)";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, c.getName());
        ps.setString(2, c.getAddress());
        ps.executeUpdate();
    }

    /**
     *
     * @param c1 old athlete
     * @param c2 new athlete
     * @throws SQLException for db errors
     */
    public static void update(Club c1, Club c2) throws SQLException {
        Connection conn = DatabaseConnection.getConnection();
        String query = "UPDATE Clubs SET name=?, address=? WHERE id=?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, c2.getName());
        ps.setString(2, c2.getAddress());
        ps.setLong(3, c1.getId());

        ps.executeUpdate();
    }

}
