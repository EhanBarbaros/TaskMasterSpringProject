package connection;

import java.sql.*;

public class ConnectDB {


    private static final String DB_URL = "jdbc:postgresql://localhost:5432/TaskMaster";
    private static final String USER = "postgres";
    private static final String PASS = "snypr4151";

    public Connection connect() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }

    public void closeConnection(Connection conn) {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
