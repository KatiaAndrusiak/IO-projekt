package io.project.database;

import io.project.alert.AlertBox;
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

    public static Employee login(String username, String password) {
        try {
            String sql = "select * from employee_data where username ='" + username + "' and password ='" + password + "';";
            PreparedStatement ps = conn.prepareStatement(sql);
            Employee user = null;
            ResultSet res = ps.executeQuery();

            if (res.next()) {
                user = new Employee();
                System.out.println("BD working");
                user.setId(res.getInt("id_employee"));
                String sql1 = "select * from employee_data_view where id_employee =  " + res.getInt("id_employee");
                PreparedStatement ps1 = conn.prepareStatement(sql1);
                ResultSet res1 = ps1.executeQuery();
                if (res1.next()) {
                    user.setRole(res1.getString("role"));
                    user.setFirstName(res1.getString("first_name"));
                    user.setLastName(res1.getString("last_name"));
                    user.setDOB(res1.getDate("dob").toLocalDate());
                    user.setPhone(res1.getString("phone"));
                    user.setPosition(res1.getString("position"));
                    user.setCategory(res1.getString("category"));
                    user.setSalary(res1.getString("salary"));
                    user.setPPE(res1.getDate("ppe").toLocalDate());
                    user.setCourseHoursSum(res1.getInt("course_hours_sum"));
                    user.setEmploymentDate(res1.getDate("employment_date").toLocalDate());
                }
                closeAll(res1, ps1);
            } else {
                AlertBox.errorAlert("Brak uzytkownika w bazie!", "Sprawdź swoje dane i sprobuj jeszcze raz");
            }
            closeAll(res, ps);
            return user;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

}
