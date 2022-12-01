package ticketSystem.database;

import java.sql.*;

// NOTE: Database class uses singleton.
public class Database{
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3311/ticketdb?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";

    static final String USER = "root";
    static final String PASS = "123456";

    private Connection conn;

    public Database() {
        this.conn = null;
    }

    
    /** 
     * Connect database.
     * @return Connection
     */
    public Connection connect() {

        if (this.conn != null) return conn;

        try {
            Class.forName(JDBC_DRIVER);
            System.out.println("Connecting database...");

            this.conn = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println(this.conn.toString());

            return this.conn;
        } catch (ClassNotFoundException e) {
            System.out.println("Connection failed to establish!");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Connection failed to establish!");
            e.printStackTrace();
        }
        
        return null;

    }

    
    /** 
     * Close the given result set.
     * @param rs
     */
    public static void closeRs(ResultSet rs) {
        try {
            if (rs != null) rs.close();
        } catch (SQLException e) {
            System.out.println("ResultSet failed to close!");
            e.printStackTrace();
        }
    }

    
    /** 
     * Close the given statement.
     * @param stmt
     */
    public static void closeStmt(Statement stmt) {
        try {
            if (stmt != null) stmt.close();
        } catch (SQLException e) {
            System.out.println("Statement failed to close!");
            e.printStackTrace();
        }
    }

    /**
     * Close database connection.
     */
    public void closeConn() {
        try {
            if (this.conn != null){
                System.out.println(this.conn.toString());
                this.conn.close();
                System.out.println("Database: GoodBye!");
            }
        } catch (SQLException e) {
            System.out.println("Connection failed to close!");
            e.printStackTrace();
        }
    }

}
