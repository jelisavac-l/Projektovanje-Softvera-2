package controllers;

import db.DatabaseConnection;
import domain.Role;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;


public class RoleController {

    public static List<Role> getList() throws SQLException {
        List<Role> roleList = new LinkedList<>();
        Connection conn = DatabaseConnection.getConnection();
        String query = "SELECT * FROM Roles";
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(query);
        while(rs.next()) {
            roleList.add(new Role(
                rs.getLong(1),
                rs.getString(2)
            ));
        }
        return roleList;
    }

    public static void add(Role role) throws SQLException {
        Connection conn = DatabaseConnection.getConnection();
        String query = "INSERT INTO Roles(name) VALUES(?)";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, role.getName());
        ps.executeUpdate();
    }

    /**
     * Updates role1 with data from role2.
     * @param role1 old role
     * @param role2 new role; id can be null
     * @throws SQLException on database error
     */
    public static void update(Role role1, Role role2) throws SQLException {
        Connection conn = DatabaseConnection.getConnection();
        String query = "UPDATE Roles SET name=? WHERE id=?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, role2.getName());
        ps.setLong(2, role1.getId());
        ps.executeUpdate();
    }

    public static void delete(Role role) throws SQLException {
        Connection conn = DatabaseConnection.getConnection();
        String query = "DELETE FROM Roles WHERE id=" + role.getId();
        Statement st = conn.createStatement();
        st.executeUpdate(query);
    }



}
