package db;

import domain.Athlete;
import domain.Club;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class MySQLDatabaseBrokerTest {

    static MySQLDatabaseBroker msdb;

    @BeforeAll
    static void establishConnection() {
        msdb = MySQLDatabaseBroker.getInstance();
        msdb.createConnection();
    }

    @Test
    void createConnection() {
        assertTrue(MySQLDatabaseBroker.getInstance().createConnection());
    }

    @Test
    void createRecord() {
        Athlete athlete = new Athlete(null, "Mile", "Dizna",
            LocalDate.now(), false, 190, 87d,
            new Club(32L, null, null));
        msdb.createRecord(athlete);
        msdb.commitTransaction();

    }

    @Test
    void updateRecord() {
    }

    @Test
    void testUpdateRecord() {
        Athlete athleteStari = new Athlete(2L, "Matija", "Lukic",
            LocalDate.now(), false, 183, 80d,
            new Club(1L, null, null));
        Athlete athlete = new Athlete(null, "Matija", "Lukic",
            LocalDate.now(), true, 183, 75d,
            new Club(2L, null, null));
        msdb.updateRecord(athlete, athleteStari);
        msdb.commitTransaction();
    }

    @Test
    void deleteRecord() {
    }

    @Test
    void deleteRecords() {
    }

    @Test
    void findRecord() {
    }

    @Test
    void findRecords() {
    }

    @Test
    void commitTransaction() {
    }

    @Test
    void rollbackTransaction() {
    }

    @Test
    void closeConnection() {
    }

    @Test
    void getRecord() {
    }

    @Test
    void getRecordCount() {
    }

    @Test
    void getRecordPosition() {
    }

    @Test
    void isDatabaseConnected() {
    }
}