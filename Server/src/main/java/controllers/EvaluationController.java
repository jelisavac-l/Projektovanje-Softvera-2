package controllers;

import db.DatabaseConnection;
import domain.*;

import java.sql.*;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

public class EvaluationController {

    public static List<Evaluation> getList() throws SQLException {
        String sql = "SELECT e.id, e.evaluationDate, e.conditions, e.athleteWeight, "
            + "evaluator.id AS evaluator_id, "
            + "athlete.id AS athlete_id "
            + "FROM Evaluations e "
            + "JOIN Evaluators evaluator ON e.evaluator = evaluator.id "
            + "JOIN Athletes athlete ON e.athlete = athlete.id";

        List<Evaluation> evaluations = new LinkedList<>();

        Connection connection = DatabaseConnection.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            Long id = resultSet.getLong("id");
            LocalDate evaluationDate = resultSet.getDate("evaluationDate").toLocalDate();
            String conditions = resultSet.getString("conditions");
            Double athleteWeight = resultSet.getDouble("athleteWeight");

            // Get evaluator and athlete ids
            Long evaluatorId = resultSet.getLong("evaluator_id");
            Long athleteId = resultSet.getLong("athlete_id");

            // Retrieve the Evaluator and Athlete objects using the getById() method
            Evaluator evaluator = EvaluatorController.getById(evaluatorId);  // Assuming EvaluatorDao.getById() method
            Athlete athlete = AthleteController.getById(athleteId);  // Assuming AthleteDao.getById() method

            Evaluation evaluation = new Evaluation(id, evaluationDate, conditions, athleteWeight, evaluator, athlete, null);

            // Retrieve the EvaluationItems list using the getItemsList method
            List<EvaluationItem> items = EvaluationController.getItemsList(evaluation);

            // Set the items to the Evaluation object
            evaluation.setItems(items);

            // Add the Evaluation to the list
            evaluations.add(evaluation);
        }

        return evaluations;
    }

    /**
     * Persist a new Evaluation (with items) to the database.
     * <i>Note: This method was optimized with a little help from the good people of the internet.</i>
     * @param evaluation Evaluation to be inserted.
     * @throws SQLException On database error.
     */
    public static void add(Evaluation evaluation) throws SQLException {
        String sql = "INSERT INTO Evaluation (evaluationDate, conditions, athleteWeight, evaluator_id, athlete_id) "
            + "VALUES (?, ?, ?, ?, ?)";

        // Get connection from your custom DatabaseConnection class
        Connection connection = DatabaseConnection.getConnection();

        // Start a transaction
        connection.setAutoCommit(false);  // Disable auto-commit to begin transaction

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            // Set values for the evaluation insert
            preparedStatement.setDate(1, Date.valueOf(evaluation.getEvaluationDate()));
            preparedStatement.setString(2, evaluation.getConditions());
            preparedStatement.setDouble(3, evaluation.getAthleteWeight());
            preparedStatement.setLong(4, evaluation.getEvaluator().getId());
            preparedStatement.setLong(5, evaluation.getAthlete().getId());

            preparedStatement.executeUpdate();

            // Get the generated ID of the newly inserted evaluation (for use in evaluation items)
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                Long evaluationId = generatedKeys.getLong(1);
                evaluation.setId(evaluationId);  // Set the ID in the Evaluation object

                EvaluationController.addItems(evaluation.getItems(), evaluation);
            }

            // Commit the transaction
            connection.commit();    // ZBOG OVOGA TWR DA MOZE ROLLBACK!
        } catch (SQLException e) {
            // Rollback the transaction in case of an error
            connection.rollback();
            throw e;  // Re-throw the exception
        } finally {
            // Set auto-commit back to true
            connection.setAutoCommit(true);
        }
    }

    // TODO: Proveriti jel sme...
    public static void update(Evaluation e1, Evaluation e2) throws SQLException {
        throw new UnsupportedOperationException();
    }

    // TODO: Proveriti jel sme...
    public static void delete(Evaluation evaluation) throws SQLException {
        throw new UnsupportedOperationException();
    }

    /**
     * Retrieves the item list from the database to populate the Evaluation object.
     * @param evaluation The evaluation object, identified by its ID, which will be used to search the composite table.
     * @throws SQLException On database error.
     */
    public static List<EvaluationItem> getItemsList(Evaluation evaluation) throws SQLException {
        String sql = "SELECT ei.evaluation, ei.serial, ei.result, ei.activity "
            + "FROM EvalItems ei "
            + "WHERE ei.evaluation = ?";

        List<EvaluationItem> evaluationItemsList = new LinkedList<>();

        Connection connection = DatabaseConnection.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setLong(1, evaluation.getId());

        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            Integer serial = resultSet.getInt("serial");
            Long result = resultSet.getLong("result");
            Long activityId = resultSet.getLong("activity");

            Activity activity = ActivityController.getById(activityId);

            EvaluationItem evaluationItem = new EvaluationItem(
                evaluation, serial, result, activity
            );
            evaluationItemsList.add(evaluationItem);
        }

        // Return the list of EvaluationItems
        return evaluationItemsList;
    }

    /**
     * Preforms insert operation on the composite table. More optimal solution compared to the iterative assignment
     * of IDs after the Evaluation object has been inserted.<br>
     * <i>Note: This method was optimized with a little help from the good people of the internet.</i>
     * @param items Items list.
     * @param evaluation The evaluation object, identified by its ID, which will be used to assign items of the
     *                   composite table.
     * @throws SQLException On database error.
     */
    public static void addItems(List<EvaluationItem> items, Evaluation evaluation) throws SQLException {
        String sql = "INSERT INTO EvalItems (evaluation, serial, result, activity) "
            + "VALUES (?, ?, ?, ?)";

        Connection connection = DatabaseConnection.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        // Disable auto-commit for batch processing (to optimize performance)
        connection.setAutoCommit(false);

        try {
            for (EvaluationItem item : items) {
                preparedStatement.setLong(1, evaluation.getId());
                preparedStatement.setInt(2, item.getSerial());
                preparedStatement.setLong(3, item.getResult());
                preparedStatement.setLong(4, item.getActivity().getId());

                // Add to batch
                preparedStatement.addBatch();
            }

            // Execute the batch of inserts
            int[] results = preparedStatement.executeBatch();

            // System.out.println("Rows affected: " + results.length);

            // Commit the transaction (all inserts)
            connection.commit();

        } catch (SQLException e) {
            // If something goes wrong, roll back the transaction
            connection.rollback();
            throw e; // Rethrow the exception
        } finally {
            // Restore auto-commit mode and close resources
            connection.setAutoCommit(true);
            preparedStatement.close();
            // connection.close();
        }
    }


}
