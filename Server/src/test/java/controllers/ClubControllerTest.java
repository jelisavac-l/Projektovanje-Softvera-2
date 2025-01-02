package controllers;

import domain.Club;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class ClubControllerTest {

    @Test
    void getList() {
        try {
            List<Club> clubs = ClubController.getList();
            assertFalse(clubs.isEmpty());
            for(var c : clubs) {
                System.out.println(c.toString().substring(0, 2) + c.getId());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void getById() {
        try {
            Club club = ClubController.getById(1L);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void addClub() {
        try {
            int randomNumber = 100000 + new Random().nextInt(900000);
            ClubController.add(new Club(null, "Test" + randomNumber, "Test"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}