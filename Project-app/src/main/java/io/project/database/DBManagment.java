package io.project.database;
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
            System.out.println(e.getMessage());
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
}
