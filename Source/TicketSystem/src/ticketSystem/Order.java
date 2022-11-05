package ticketSystem;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import ticketSystem.database.Database;
import ticketSystem.database.DBException.ExDbOrderNotFound;
import ticketSystem.database.dao.order.IOrderDAO;
import ticketSystem.database.dao.order.OrderDAO;

public class Order {
    private String flightSet;
    private int number;

    public Order(String flightSet, int number) {
        this.flightSet = flightSet;
        this.number = number;
    }

    private static ArrayList<Order> rsToAl(ResultSet rs) {
        ArrayList<Order> ret = new ArrayList<>();

        try {
            while(rs.next()) {
                Order order = new Order(
                    rs.getString("flight_set"), 
                    rs.getInt("number")
                );

                ret.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        return ret;
    }


    public static ArrayList<Order> queryOrderByUsername(Database db, String username){
        IOrderDAO iOrderDAO = OrderDAO.getInstance();
        ResultSet rs = iOrderDAO.queryOrderByUsername(db, username);
        return rsToAl(rs);
    }

    public static ArrayList<Order> queryAllOrder(Database db) {
        IOrderDAO iOrderDAO = OrderDAO.getInstance();
        ResultSet rs = iOrderDAO.queryAllOrder(db);
        return rsToAl(rs);
    }

    public static ArrayList<Order> addOrder(Database db, String flightSet, int number) {
        IOrderDAO iOrderDAO = OrderDAO.getInstance();
        ResultSet rs = iOrderDAO.addOrder(db, flightSet, number);

        // NOTE: here the ArrayList only contanins one order which is the one newly created above.
        return rsToAl(rs);
    }

    public static boolean deleteOrder(Database db, int orderIndex) throws ExDbOrderNotFound {
        IOrderDAO iOrderDAO = OrderDAO.getInstance();
        Boolean bret = iOrderDAO.deleteOrder(db, orderIndex);
        return bret;
    }

}
