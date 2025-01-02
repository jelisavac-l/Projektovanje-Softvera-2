package controllers;

import domain.Role;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class RoleControllerTest {

    @Test
    void getList() {
        try {
            List<Role> lr = RoleController.getList();
            System.out.println(lr);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void add() {
        int randomNumber = 100000 + new Random().nextInt(900000);
        try {
            RoleController.add(new Role(null, "Test" + randomNumber));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}