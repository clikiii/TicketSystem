package ticketSystem;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import ticketSystem.database.Database;
import ticketSystem.database.dao.order.IOrderDAO;
import ticketSystem.database.dao.order.OrderDAO;
import ticketSystem.database.dbException.ExDbOrderNotFound;

public class Order {
    private int orderIndex;
    private String flightSet;
    private int number;

    public Order(String flightSet, int number) {
        // NOTE: NO order index before insertion.
        this.orderIndex = -1;
        this.flightSet = flightSet;
        this.number = number;
    }

    public Order(int orderIndex, String flightSet, int number){
        this.orderIndex = orderIndex;
        this.flightSet = flightSet;
        this.number = number;
    }

    private static ArrayList<Order> rsToAl(ResultSet rs) {
        ArrayList<Order> ret = new ArrayList<>();
        if (rs == null) {
            return ret;
        }

        try {
            while(rs.next()) {
                Order order = new Order(
                    rs.getInt("order_index"),
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
        if (rs == null) {
            return null;
        }
        return rsToAl(rs);
    }

    public static ArrayList<Order> queryAllOrder(Database db) {
        IOrderDAO iOrderDAO = OrderDAO.getInstance();
        ResultSet rs = iOrderDAO.queryAllOrder(db);
        return rsToAl(rs);
    }

    public static ArrayList<Order> addOrder(Database db, Order o) {
        IOrderDAO iOrderDAO = OrderDAO.getInstance();
        ResultSet rs = iOrderDAO.addOrder(db, o.flightSet, o.number);

        if (rs != null) {
            // NOTE: here the ArrayList only contanins one order which is the one newly created above.
            return rsToAl(rs);
        }
        return null;
    }

    public static boolean cancelOrder(Database db, int orderIndex) throws ExDbOrderNotFound {
        IOrderDAO iOrderDAO = OrderDAO.getInstance();
        Boolean bret = iOrderDAO.deleteOrder(db, orderIndex);
        return bret;
    }

}
