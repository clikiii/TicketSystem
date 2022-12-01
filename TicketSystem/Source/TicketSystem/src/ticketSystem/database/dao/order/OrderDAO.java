package ticketSystem.database.dao.order;

import java.sql.*;
import java.util.ArrayList;

import ticketSystem.Order;
import ticketSystem.database.Database;
import ticketSystem.database.dbException.ExDbOrderNotFound;

public class OrderDAO implements IOrderDAO{
    private static OrderDAO instance = new OrderDAO();
    private OrderDAO(){};
    public static OrderDAO getInstance() {
        return instance;
    }

    @Override
    public ArrayList<Order> queryOrderByUsername(Database db, String username) {
        Connection conn = db.connect();
        Statement stmt = null;
        ResultSet rs = null;

        try {
            conn = db.connect();
            stmt = conn.createStatement();
            String sqlSelect = "select * from ticketdb.order where username = '%s';";
            rs = stmt.executeQuery(String.format(sqlSelect, username));

            return Order.rsToAl(db, rs);
            
        } catch (SQLException e) {
            e.printStackTrace();
            return  null;
        } finally {
            Database.closeStmt(stmt);
        }

    }

    @Override
    public ArrayList<Order> addOrder(Database db, String flightSet, int number, String username) {
        Connection conn = db.connect();
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.createStatement();
            String sqlInsert = "insert into ticketdb.order (flight_set, number, username) values ('%s','%d','%s');";
            stmt.executeUpdate(String.format(sqlInsert, flightSet, number, username));
            rs = stmt.executeQuery("SELECT * from ticketdb.order where order_index = (SELECT max(order_index) FROM ticketdb.order);");
        
            System.out.println("add order success");
            return Order.rsToAl(db, rs);
        
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            Database.closeStmt(stmt);
        }

    }

    @Override
    public boolean deleteOrder(Database db, int orderIndex) throws ExDbOrderNotFound {
        Connection conn = db.connect();
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.createStatement();
            String sqlSelect = "select count(*) from ticketdb.order where order_index = '%s';";
            rs = stmt.executeQuery(String.format(sqlSelect, orderIndex));
            rs.next();
            if (rs.getInt("count(*)") == 0) {
                throw new ExDbOrderNotFound();
            }else{
                String sqlUpdate = "delete from ticketdb.order where order_index = '%s';";
                stmt.executeUpdate(String.format(sqlUpdate, orderIndex));
            }       
        
            return true;
        
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            Database.closeStmt(stmt);
        }

    }

    @Override
    public ArrayList<Order> queryAllOrder(Database db) {
        Connection conn = db.connect();
        Statement stmt = null;
        ResultSet rs = null;

        try {
            conn = db.connect();
            stmt = conn.createStatement();
            String sqlSelect = "select * from ticketdb.order;";
            rs = stmt.executeQuery(String.format(sqlSelect));

            return Order.rsToAl(db, rs);

        } catch (SQLException e) {
            e.printStackTrace();
            return  null;
        } finally {
            Database.closeStmt(stmt);
        }
    }
    
}
