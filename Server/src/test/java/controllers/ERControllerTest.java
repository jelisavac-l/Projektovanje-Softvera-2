package controllers;

import domain.ER;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ERControllerTest {

    @Test
    void getList() {
        try {
            List<ER> erList = ERController.getList();
            for(var er : erList) {
                System.out.println(er);
            }
            assertFalse(erList.isEmpty());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}