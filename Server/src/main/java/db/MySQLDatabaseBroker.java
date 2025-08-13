package db;

import domain.DomainObject;
import io.github.cdimascio.dotenv.Dotenv;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class MySQLDatabaseBroker implements DatabaseBroker {
    private final String databaseName;
    private Connection connection;
    private static MySQLDatabaseBroker instance;
    private final Dotenv dotenv = Dotenv.load();

    private MySQLDatabaseBroker() {
        this.databaseName = dotenv.get("MYSQL_DB");
    }

    public static MySQLDatabaseBroker getInstance() {
        if(instance == null) {
            instance = new MySQLDatabaseBroker();
        }
        return instance;
    }

    @Override
    public boolean createConnection() {
        try {
            if(connection != null && !connection.isClosed()) return true;
            String url;
            url = "jdbc:mysql://localhost:3306/" + databaseName;
            connection = DriverManager.getConnection(url, dotenv.get("MYSQL_USER"), dotenv.get("MYSQL_PASS"));
            connection.setAutoCommit(false);
        } catch (SQLException ex) {
            System.getLogger(DatabaseBroker.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            return false;
        }
        return true;
    }

    @Override
    public boolean createRecord(DomainObject doObj) {
        String query = "INSERT INTO `" + databaseName + "`.`" + doObj.getTableName() + "` (" +
            doObj.getColumnNames() + ") VALUES (" + doObj.getAttributeValuesForInsert() + ")";
        return executeUpdate(query);
    }

    @Override
    public boolean updateRecord(DomainObject doObj, DomainObject oldDoObj) {
        String query = "UPDATE `" + databaseName + "`.`" + doObj.getTableName() +
            "` SET " + doObj.getUpdatedAttributeValues() +
            " WHERE " + oldDoObj.getWhereCondition();
        return executeUpdate(query);
    }

    @Override
    public boolean updateRecord(DomainObject doObj) {
        String query = "UPDATE `" + databaseName + "`.`" + doObj.getTableName() +
            "` SET " + doObj.getUpdatedAttributeValues() +
            " WHERE " + doObj.getWhereCondition();
        return executeUpdate(query);
    }

    @Override
    public boolean deleteRecord(DomainObject doObj) {
        String query = "DELETE FROM `" + databaseName + "`.`" + doObj.getTableName() +
            "` WHERE " + doObj.getWhereCondition();
        return executeUpdate(query);
    }

    @Override
    public boolean deleteRecords(DomainObject doObj, String where) {
        String query = "DELETE FROM `" + databaseName + "`.`" + doObj.getTableName() +
            "` WHERE " + where;
        return executeUpdate(query);
    }

    @Override
    public DomainObject findRecord(DomainObject doObj) {
        ResultSet rs = null;
        Statement statement = null;
        String query = "SELECT * FROM `" + databaseName + "`.`" + doObj.getTableName() + "` AS " +
            doObj.getAlias() + " " + doObj.getJoinClause() +
            " WHERE " + doObj.getWhereCondition();

        try {
            statement = connection.createStatement();
            rs = statement.executeQuery(query);
            if (rs.next()) {
                doObj = doObj.getNewRecord(rs);
            } else {
                doObj = null;
            }
        } catch (SQLException ex) {
            System.getLogger(DatabaseBroker.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        } finally {
            closeResources(null, statement, rs);
        }
        return doObj;
    }

    @Override
    public List<DomainObject> findRecords(DomainObject doObj, String where) {
        ResultSet rs = null;
        Statement statement = null;
        String query = "SELECT * FROM `" + databaseName + "`.`" + doObj.getTableName() + "` AS " +
            doObj.getAlias() + " " + doObj.getJoinClause() + " WHERE " + where;
        List<DomainObject> list = new LinkedList<>();

        try {
            statement = connection.createStatement();
            rs = statement.executeQuery(query);
            while (rs.next()) {
                list.add(doObj.getNewRecord(rs));
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            System.getLogger(DatabaseBroker.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        } finally {
            closeResources(null, statement, rs);
        }
        return list;
    }

    public List<DomainObject> findRecords(DomainObject doObj) {
        ResultSet rs = null;
        Statement statement = null;
        String query = "SELECT * FROM `" + databaseName + "`.`" + doObj.getTableName() + "` AS " +
            doObj.getAlias() + " " + doObj.getJoinClause();
        List<DomainObject> list = new LinkedList<>();

        try {
            statement = connection.createStatement();
            rs = statement.executeQuery(query);
            while (rs.next()) {
                list.add(doObj.getNewRecord(rs));

            }
        } catch (SQLException ex) {
            System.getLogger(DatabaseBroker.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        } finally {
            closeResources(null, statement, rs);
        }
        return list;
    }

    @Override
    public boolean commitTransaction() {
        try {
            connection.commit();
        } catch (SQLException ex) {
            return false;
        }
        return true;
    }

    @Override
    public boolean rollbackTransaction() {
        try {
            connection.rollback();
        } catch (SQLException ex) {
            return false;
        }
        return true;
    }

    @Override
    public void closeConnection() {
        closeResources(connection, null, null);
    }

    @Override
    public DomainObject getRecord(DomainObject doObj, int index) {
        ResultSet rs = null;
        Statement statement = null;
        String query = "SELECT * FROM `" + databaseName + "`.`" + doObj.getTableName() +
            "` ORDER BY " + doObj.getColumnNameByIndex(0) +
            " ASC LIMIT " + index + ",1";

        try {
            statement = connection.createStatement();
            rs = statement.executeQuery(query);
            if (rs.next()) {
                doObj = doObj.getNewRecord(rs);
            } else {
                doObj = null;
            }
        } catch (SQLException ex) {
            doObj = null;
            System.getLogger(DatabaseBroker.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        } finally {
            closeResources(null, statement, rs);
        }
        return doObj;
    }

    @Override
    public int getRecordCount(DomainObject doObj) {
        ResultSet rs = null;
        Statement statement = null;
        int rowCount = 0;
        String query = "SELECT * FROM `" + databaseName + "`.`" + doObj.getTableName() + "`;";

        try {
            statement = connection.createStatement();
            rs = statement.executeQuery(query);
            if (rs.last()) {
                rowCount = rs.getRow();
            }
        } catch (SQLException ex) {
            System.getLogger(DatabaseBroker.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        } finally {
            closeResources(null, statement, rs);
        }
        return rowCount;
    }

    @Override
    public int getRecordPosition(DomainObject doObj) {
        ResultSet rs = null;
        Statement statement = null;
        String query = "SELECT COUNT(*) AS position FROM `" + databaseName + "`.`" + doObj.getTableName() +
            "` WHERE " + doObj.getColumnNameByIndex(0) + " < " + doObj.getPrimaryKey();

        try {
            statement = connection.createStatement();
            rs = statement.executeQuery(query);
            if (rs.next()) {
                return rs.getInt("position");
            }
        } catch (SQLException ex) {
            System.getLogger(DatabaseBroker.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        } finally {
            closeResources(null, statement, rs);
        }
        return -1;
    }

    @Override
    public boolean isDatabaseConnected() {
        try {
            return connection != null && !connection.isClosed();
        } catch (SQLException ex) {
            System.getLogger(DatabaseBroker.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            return false;
        }
    }

    private boolean executeUpdate(String query) {
        Statement statement = null;
        boolean success = false;
        try {
            statement = connection.createStatement();
            int affectedRows = statement.executeUpdate(query);
            success = affectedRows > 0;
        } catch (SQLException ex) {
            System.getLogger(DatabaseBroker.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        } finally {
            closeResources(null, statement, null);
        }
        return success;
    }

    private void closeResources(Connection conn, Statement stmt, ResultSet rs) {
        if (rs != null) {
            try { rs.close(); } catch (SQLException ex) { logError(ex); }
        }
        if (stmt != null) {
            try { stmt.close(); } catch (SQLException ex) { logError(ex); }
        }
        if (conn != null) {
            try { conn.close(); } catch (SQLException ex) { logError(ex); }
        }
    }

    private void logError(SQLException ex) {
        System.getLogger(DatabaseBroker.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
    }
}
