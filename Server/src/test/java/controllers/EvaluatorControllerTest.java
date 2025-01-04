package controllers;

import domain.Evaluator;
import domain.Role;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class EvaluatorControllerTest {

    @Test
    void getList() {
        try {
            List<Evaluator> evaluators = EvaluatorController.getList();
            for(var e : evaluators) {
                System.out.println(e + " " + e.getId());
            }
            assertFalse(evaluators.isEmpty());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void getById() {
        try {
            Evaluator e = EvaluatorController.getById(1L);
            assertNotNull(e);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Test
    void add() {
        int randomNumber = 100000 + new Random().nextInt(900000);
        try {
            EvaluatorController.add(new Evaluator(null,
                "Test"+randomNumber,
                "Testanovski",
                randomNumber + " user " + randomNumber,
                "admin",
                "test@sistem.rs",
                "Nepostojeci covek",
                LocalDate.now(),
                true));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void update() {
    }

//    @Test
//    void delete() {
//        try {
//            EvaluatorController.delete(new Evaluator(
//                3L,
//                null,
//                null,
//                null,
//                null,
//                null,
//                null,
//                null,
//                null));
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }

    @Test
    void getCurrentRoleList() {
        try {
            List<Role> roles = EvaluatorController.getCurrentRoleList(EvaluatorController.getById(1L));
            System.out.println(roles);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void getAllRoleList() {
    }

    @Test
    void startRole() {
    }

    @Test
    void endRole() {
    }
}