package ticketSystem.database;

import java.sql.*;

// NOTE: Database class uses singleton.
public class Database{
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/testdb?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";

    static final String USER = "root";
    static final String PASS = "20020215";

    private Connection conn;

    public Database() {
        this.conn = null;
    }

    public Connection connect() {

        if (this.conn != null) return conn;

        try {
            Class.forName(JDBC_DRIVER);
            System.out.println("Connecting database...");

            this.conn = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println(this.conn.toString());

            return this.conn;
        } catch (ClassNotFoundException e1) {
            // TODO Auto-generated catch block
            System.out.println("Connection failed to establish!");
            e1.printStackTrace();
        } catch (SQLException e1) {
            System.out.println("Connection failed to establish!");
            // TODO Auto-generated catch block
            e1.printStackTrace();
        } finally {
            try {
                if (this.conn != null) conn.close();
            } catch (SQLException e) {
                System.out.println("Connection failed to close!");
                // TODO Auto-generated catch block
                e.printStackTrace();

                return null;
            }
        }

        return null;

    }

    public void closeConn() {
        try {
            if (this.conn != null){
                conn.close();
                System.out.println("Database: GoodBye!");
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            System.out.println("Connection failed to close!");
            e.printStackTrace();
        }
    }

}
