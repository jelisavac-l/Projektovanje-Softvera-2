package controllers;

import db.DatabaseConnection;
import domain.Evaluator;
import domain.Role;

import java.sql.*;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

public class EvaluatorController {

    public static List<Evaluator> getList() throws SQLException {
        String sql = "SELECT * FROM Evaluators";

        List<Evaluator> evaluators = new LinkedList<>();

        Connection connection = DatabaseConnection.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            // Map each row to an Evaluator object
            Long id = resultSet.getLong("id");
            String firstName = resultSet.getString("firstName");
            String lastName = resultSet.getString("lastName");
            String userName = resultSet.getString("username");
            String userPassword = resultSet.getString("password");
            String email = resultSet.getString("email");
            String title = resultSet.getString("title");
            LocalDate hireDate = resultSet.getDate("hireDate").toLocalDate();
            Boolean active = resultSet.getBoolean("active");

            Evaluator evaluator = new Evaluator(id, firstName, lastName, userName, userPassword, email, title, hireDate, active);
            evaluators.add(evaluator);
        }

        return evaluators;
    }

    public static Evaluator getById(Long id) throws SQLException {
        String sql = "SELECT * FROM Evaluators WHERE id = ?";

        Connection connection = DatabaseConnection.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setLong(1, id);

        ResultSet resultSet = preparedStatement.executeQuery();

        Evaluator evaluator = null;
        if (resultSet.next()) {
            Long evaluatorId = resultSet.getLong("id");
            String firstName = resultSet.getString("firstName");
            String lastName = resultSet.getString("lastName");
            String userName = resultSet.getString("username");
            String userPassword = resultSet.getString("password");
            String email = resultSet.getString("email");
            String title = resultSet.getString("title");
            LocalDate hireDate = resultSet.getDate("hireDate").toLocalDate();
            Boolean active = resultSet.getBoolean("active");

            evaluator = new Evaluator(evaluatorId, firstName, lastName, userName, userPassword, email, title, hireDate, active);
        }

        return evaluator;
    }

    public static Evaluator getByCredentials(Evaluator e) throws SQLException {
        String sql = "SELECT * FROM Evaluators WHERE username = ? AND password = ?";

        Connection connection = DatabaseConnection.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setString(1, e.getUsername());
        preparedStatement.setString(2, e.getPassword());

        ResultSet resultSet = preparedStatement.executeQuery();

        Evaluator evaluator = null;
        if (resultSet.next()) {
            Long evaluatorId = resultSet.getLong("id");
            String firstName = resultSet.getString("firstName");
            String lastName = resultSet.getString("lastName");
            String userName = resultSet.getString("username");
            String userPassword = resultSet.getString("password");
            String email = resultSet.getString("email");
            String title = resultSet.getString("title");
            LocalDate hireDate = resultSet.getDate("hireDate").toLocalDate();
            Boolean active = resultSet.getBoolean("active");

            evaluator = new Evaluator(evaluatorId, firstName, lastName, userName, userPassword, email, title, hireDate, active);
        }

        return evaluator;
    }

    public static void add(Evaluator evaluator) throws SQLException {
        String sql = "INSERT INTO Evaluators (firstName, lastName, username, password, email, title, hireDate, active) "
            + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        Connection connection = DatabaseConnection.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setString(1, evaluator.getFirstName());
        preparedStatement.setString(2, evaluator.getLastName());
        preparedStatement.setString(3, evaluator.getUsername());
        preparedStatement.setString(4, evaluator.getPassword());
        preparedStatement.setString(5, evaluator.getEmail());
        preparedStatement.setString(6, evaluator.getTitle());
        preparedStatement.setDate(7, Date.valueOf(evaluator.getHireDate()));
        preparedStatement.setBoolean(8, evaluator.getActive());

        preparedStatement.executeUpdate();
    }

    public static void update(Evaluator e1, Evaluator e2) throws SQLException {
        String sql = "UPDATE Evaluators SET firstName = ?, lastName = ?, username = ?, password = ?, "
            + "email = ?, title = ?, hireDate = ?, active = ? WHERE id = ?";

        Connection connection = DatabaseConnection.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setString(1, e2.getFirstName());
        preparedStatement.setString(2, e2.getLastName());
        preparedStatement.setString(3, e2.getUsername());
        preparedStatement.setString(4, e2.getPassword());
        preparedStatement.setString(5, e2.getEmail());
        preparedStatement.setString(6, e2.getTitle());
        preparedStatement.setDate(7, Date.valueOf(e2.getHireDate()));
        preparedStatement.setBoolean(8, e2.getActive());

        preparedStatement.setLong(9, e1.getId());

        preparedStatement.executeUpdate();

        preparedStatement.close();
        connection.close();
    }

    public static void delete(Evaluator evaluator) throws SQLException {
        String sql = "DELETE FROM Evaluators WHERE id = ?";

        Connection connection = DatabaseConnection.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setLong(1, evaluator.getId());

        preparedStatement.executeUpdate();

    }

    public static List<Role> getCurrentRoleList(Evaluator evaluator) throws SQLException {
        List<Role> currentRoles = new LinkedList<>();

        String sql = "SELECT r.* FROM Roles r "
            + "JOIN ER e ON r.id = e.role "
            + "WHERE e.evaluator = ? "
            + "AND e.startDate <= CURRENT_DATE "
            + "AND (e.endDate IS NULL OR e.endDate >= CURRENT_DATE)";

        Connection connection = DatabaseConnection.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setLong(1, evaluator.getId());

        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            long roleId = resultSet.getLong("id");
            String roleName = resultSet.getString("name");
            // Create a Role object (assuming a Role constructor that takes id and name)
            Role role = new Role(roleId, roleName);
            currentRoles.add(role);
        }

        return currentRoles;
    }

    public static List<Role> getAllRoleList(Evaluator evaluator) throws SQLException {
        List<Role> allRoles = new LinkedList<>();

        String sql = "SELECT r.* FROM Roles r "
            + "JOIN ER e ON r.id = e.role "
            + "WHERE e.evaluator = ?";

        Connection connection = DatabaseConnection.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setLong(1, evaluator.getId());

        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            Long roleId = resultSet.getLong("id");
            String roleName = resultSet.getString("name");
            Role role = new Role(roleId, roleName);
            allRoles.add(role);
        }

        return allRoles;
    }

    public static void startRole(Evaluator evaluator, Role role) throws SQLException {
        String sql = "INSERT INTO ER (evaluator, role, startDate, endDate)\n" +
            "VALUES (?, ?, ?, ?);";

        Connection connection = DatabaseConnection.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setLong(1, evaluator.getId());
        preparedStatement.setLong(2, role.getId());
        preparedStatement.setDate(3, Date.valueOf(LocalDate.now()));
        preparedStatement.setDate(4, null);

        preparedStatement.executeUpdate();    }

    public static void endRole(Evaluator evaluator, Role role) throws SQLException {
        String sql = "UPDATE ER SET endDate = CURRENT_DATE WHERE evaluator = ? AND role = ?";

        Connection connection = DatabaseConnection.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setLong(1, evaluator.getId());
        preparedStatement.setLong(2, role.getId());

        preparedStatement.executeUpdate();

        preparedStatement.close();
        connection.close();    }

}
