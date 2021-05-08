package io.project.database;
import io.project.entities.Employee;

import java.sql.*;

public class DBManagment {
    private static final String url = "postgres://gvfmqimz:kEfzcA...@hattie.db.elephantsql.com:5432/gvfmqimz";
    private static final String user = "gvfmqimz";
    private static final String pass = "kEfzcAjK-loVLrWOQUfwniLf8TQvZmnf";

    public DBManagment() {}

    public static Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, pass);
            System.out.println("Polaczenie z baza danych OK ! ");
        } catch (SQLException e) {
            System.out.println("Error" + e.getMessage());
        }

        return conn;
    }


    public static void closeAll(Connection con, ResultSet res, PreparedStatement pst) {
        if (res != null) {
            try {
                res.close();
            } catch (SQLException e) {}
        }
        if (pst != null) {
            try {
                pst.close();
            } catch (SQLException e) {}
        }
        if (con != null) {
            try {
                con.close();
            } catch (SQLException e) {}
        }
    }

    public static Employee login(String username, String password) {
        String sql = "select * from employee_role where username="+ username + " and password=" + password;

        Connection conn = connect();

        return new Employee();
    }
}
