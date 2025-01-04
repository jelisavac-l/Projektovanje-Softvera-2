package controllers;

import db.DatabaseConnection;
import domain.ER;
import domain.Evaluator;
import domain.Role;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

public class ERController {

    public static List<ER> getList() throws SQLException {
        List<ER> erList = new LinkedList<>();

        String sql = "SELECT * FROM ER";

        Connection connection = DatabaseConnection.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        ResultSet resultSet = preparedStatement.executeQuery();

        String evaluatorQuery = "SELECT * FROM Evaluators WHERE id = ?";
        String roleQuery = "SELECT * FROM Roles WHERE id = ?";

        PreparedStatement evaluatorStatement = connection.prepareStatement(evaluatorQuery);
        PreparedStatement roleStatement = connection.prepareStatement(roleQuery);

        while (resultSet.next()) {
            Long evaluatorId = resultSet.getLong("evaluator");
            Long roleId = resultSet.getLong("role");
            LocalDate startDate = resultSet.getDate("startDate").toLocalDate();
            LocalDate endDate = resultSet.getDate("endDate") != null ? resultSet.getDate("endDate").toLocalDate() : null;

            evaluatorStatement.setLong(1, evaluatorId);
            ResultSet evaluatorResultSet = evaluatorStatement.executeQuery();
            Evaluator evaluator = null;
            if (evaluatorResultSet.next()) {
                evaluator = new Evaluator(
                    evaluatorResultSet.getLong("id"),
                    evaluatorResultSet.getString("firstName"),
                    evaluatorResultSet.getString("lastName"),
                    evaluatorResultSet.getString("username"),
                    evaluatorResultSet.getString("password"),
                    evaluatorResultSet.getString("email"),
                    evaluatorResultSet.getString("title"),
                    evaluatorResultSet.getDate("hireDate").toLocalDate(),
                    evaluatorResultSet.getBoolean("active")
                );
            }
            evaluatorResultSet.close();

            roleStatement.setLong(1, roleId);
            ResultSet roleResultSet = roleStatement.executeQuery();
            Role role = null;
            if (roleResultSet.next()) {
                role = new Role(
                    roleResultSet.getLong("id"),
                    roleResultSet.getString("name")
                );
            }
            roleResultSet.close();

            if (evaluator != null && role != null) {
                ER er = new ER(evaluator, role, startDate, endDate);
                erList.add(er);
            }
        }

//        resultSet.close();
//        preparedStatement.close();
//        evaluatorStatement.close();
//        roleStatement.close();


        // Return the list of ER objects
        return erList;
    }

}
