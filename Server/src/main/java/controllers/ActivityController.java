package controllers;

import db.DatabaseConnection;
import domain.Activity;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class ActivityController {

    public static List<Activity> getList() throws SQLException {
        List<Activity> activityList = new LinkedList<>();
        Connection conn = DatabaseConnection.getConnection();
        String query = "SELECT * FROM Activities";
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(query);
        while(rs.next()) {
            activityList.add(new Activity(
                rs.getLong(1),
                rs.getString(2),
                rs.getString(3)
            ));
        }
        return activityList;
    }

    public static Activity getById(Long activityId) throws SQLException {
        String sql = "SELECT id, name, unit FROM Activities WHERE id = ?";
        Activity activity = null;

        Connection connection = DatabaseConnection.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setLong(1, activityId);

        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            String name = resultSet.getString("name");
            String unit = resultSet.getString("unit");
            activity = new Activity(activityId, name, unit);
        }

        return activity;
    }

    public static void add(Activity activity) throws SQLException {
        Connection conn = DatabaseConnection.getConnection();
        String query = "INSERT INTO Activities(name, unit) VALUES (?, ?)";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, activity.getName());
        ps.setString(2, activity.getUnit());
        ps.executeUpdate();
    }

    public static void update(Activity act1, Activity act2) throws SQLException {
        Connection conn = DatabaseConnection.getConnection();
        String query = "UPDATE Activities SET name=?, unit=? WHERE id=?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1,act2.getName());
        ps.setString(2, act2.getUnit());
        ps.setLong(3, act1.getId());
        ps.executeUpdate();
    }

    public static void delete(Activity activity) throws SQLException {
        throw new UnsupportedOperationException();
    }

}
