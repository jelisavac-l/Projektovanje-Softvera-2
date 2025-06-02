package controllers;

import domain.Athlete;
import domain.Club;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class AthleteControllerTest {

    @Test
    void getList() {
        try {
            List<Athlete> athleteList = AthleteController.getList();
            assertFalse(athleteList.isEmpty());
            for(var a : athleteList) {
                System.out.println(a);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void add() {
        int randomNumber = 100000 + new Random().nextInt(900000);
        try {
            AthleteController.add(new Athlete(null,
                "Test-" + randomNumber,
                "Testanović",
                LocalDate.now(),
                false,
                180,
                75.0,
                new Club(1L, null, null)
                ));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void getById() {
        try {
            Athlete athlete = AthleteController.getById(120L);
            System.out.println(athlete);
            assertNotNull(athlete);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}