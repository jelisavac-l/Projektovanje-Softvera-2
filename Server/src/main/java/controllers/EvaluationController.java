package controllers;

import domain.Evaluation;
import domain.EvaluationItem;

import java.sql.SQLException;
import java.util.List;

public class EvaluationController {

    public static List<Evaluation> getList() throws SQLException {
        throw new UnsupportedOperationException();
    }

    public static void add(Evaluation evaluation) throws SQLException {
        throw new UnsupportedOperationException();
    }

    public static void update(Evaluation e1, Evaluation e2) throws SQLException {
        throw new UnsupportedOperationException();
    }

    public static void delete(Evaluation evaluation) throws SQLException {
        throw new UnsupportedOperationException();
    }

    /**
     * Retrieves the item list from the database to populate the Evaluation object.
     * @param evaluation The evaluation object, identified by its ID, which will be used to search the composite table.
     * @throws SQLException On database error.
     */
    private static List<EvaluationItem> getItemsList(Evaluation evaluation) throws SQLException {
        throw new UnsupportedOperationException();
    }

    /**
     * Preforms insert operation on the composite table. More optimal solution compared to the iterative assignment
     * of IDs after the Evaluation object has been inserted.
     * @param items Items list.
     * @param evaluation The evaluation object, identified by its ID, which will be used to assign items of the
     *                   composite table.
     * @throws SQLException On database error.
     */
    private static void addItems(List<EvaluationItem> items, Evaluation evaluation) throws SQLException {
        throw new UnsupportedOperationException();
    }


}
