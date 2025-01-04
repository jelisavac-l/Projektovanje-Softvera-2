package controllers;

import domain.Evaluation;
import domain.EvaluationItem;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EvaluationControllerTest {

    @Test
    void getList() {
        try {
            List<Evaluation> evaluations = EvaluationController.getList();
            for(var e : evaluations) {
                System.out.println(e.getAthlete() + " " + e.getEvaluator() + " " + e.getItems().size());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void add() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }

    @Test
    void getItemsList() {
        List<EvaluationItem> items = null;
        try {
            items = EvaluationController.getItemsList(new Evaluation(
                1L,
                null,
                null,
                null,
                null,
                null,
                null
            ));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println(items);
    }

    @Test
    void addItems() {
        try {
            Evaluation evaluation = new Evaluation(1L, LocalDate.now(), "OK", 0.0, null, null, null); // Example Evaluation object
            List<EvaluationItem> items = new LinkedList<>();

            // Populate the list of EvaluationItems
            items.add(new EvaluationItem(evaluation, 11, 85L, ActivityController.getById(1L)));
            items.add(new EvaluationItem(evaluation, 12, 90L, ActivityController.getById(2L)));

            // Add items to the database
            EvaluationController.addItems(items, evaluation);
        } catch (Exception e) {
            System.err.println("Ne sljaka " + e.getMessage());
        }

        System.out.println("Items inserted successfully.");
    }

    @Test
    void testGetList() {
    }

    @Test
    void testAdd() {
    }

    @Test
    void testUpdate() {
    }

    @Test
    void testDelete() {
    }
}