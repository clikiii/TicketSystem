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
    private ArrayList<Flight> flightSetObjList;
    private int number;

    public Order(String flightSet, int number) {
        // NOTE: NO order index before insertion.
        this.orderIndex = -1;
        this.flightSet = flightSet;
        this.number = number;

        this.flightSetObjList = null;
    }

    public Order(int orderIndex, String flightSet, int number, ArrayList<Flight> flightSetObjList){
        this.orderIndex = orderIndex;
        this.flightSet = flightSet;
        this.number = number;

        this.flightSetObjList = flightSetObjList;
    }

    private static ArrayList<Order> rsToAl(Database db, ResultSet rs) {
        ArrayList<Order> ret = new ArrayList<>();

        try {
            while(rs.next()) {
                // "001 002"
                ArrayList<Flight> flightSetObjList = new ArrayList<>();
                for (String s: rs.getString("flight_set").split(" ")){
                    int fIdx = Integer.parseInt(s);
                    flightSetObjList.addAll(Flight.queryFlightByIndex(db, fIdx));
                }

                Order order = new Order(
                    rs.getInt("order_index"),
                    rs.getString("flight_set"), 
                    rs.getInt("number"),
                    flightSetObjList
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
        return rsToAl(db, rs);
    }

    public static ArrayList<Order> queryAllOrder(Database db) {
        IOrderDAO iOrderDAO = OrderDAO.getInstance();
        ResultSet rs = iOrderDAO.queryAllOrder(db);
        return rsToAl(db, rs);
    }

    public static ArrayList<Order> addOrder(Database db, Order o) {
        IOrderDAO iOrderDAO = OrderDAO.getInstance();
        ResultSet rs = iOrderDAO.addOrder(db, o.flightSet, o.number);

        // NOTE: here the ArrayList only contanins one order which is the one newly created above.
        return rsToAl(db, rs);
    }

    public static boolean cancelOrder(Database db, int orderIndex) throws ExDbOrderNotFound {
        IOrderDAO iOrderDAO = OrderDAO.getInstance();
        Boolean bret = iOrderDAO.deleteOrder(db, orderIndex);
        return bret;
    }

}
