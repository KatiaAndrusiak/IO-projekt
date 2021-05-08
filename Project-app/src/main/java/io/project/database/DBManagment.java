package io.project.database;

import io.project.entities.Employee;

import java.sql.*;

public class DBManagment {


    public DBManagment() { }


    private static Connection conn = connect();

    public static Connection connect() {
        String propFileName = "config.properties";

        String dbURL;
        String dbUser;
        String dbPassword;

        Connection conn = null;


        dbURL = "jdbc:postgresql://hattie.db.elephantsql.com:5432/gvfmqimz";
        dbUser = "gvfmqimz";
        dbPassword = "kEfzcAjK-loVLrWOQUfwniLf8TQvZmnf";

        try {
            conn = DriverManager.getConnection(dbURL, dbUser, dbPassword);
            System.out.print("Connected to the PostgreSQL server successfully");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }

        return conn;
    }

    public static void close() throws SQLException {
        conn.close();
    }

    public static Connection getConn() {
        return conn;
    }

    public static void setConn(Connection conn) {
        DBManagment.conn = conn;
    }

    public static void closeAll(ResultSet res, PreparedStatement pst) {
        if (res != null) {
            try {
                res.close();
            } catch (SQLException e) {
            }
        }
        if (pst != null) {
            try {
                pst.close();
            } catch (SQLException e) {
            }
        }
    }

    public static Employee login(String username, String password) throws SQLException {
        String sql = "select * from employee_data where username ='" + username + "' and password ='" + password + "';";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.execute();
        System.out.println("Test");
        return new Employee();
    }
}
