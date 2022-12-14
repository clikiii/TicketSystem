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
    private String username;

    
    /** 
     * Get the order index.
     * @return int
     */
    public int getOrderIndex() {
        return this.orderIndex;
    }

    
    /** 
     * Get the flight set.
     * @return String
     */
    public String getFlightSet() {
        return this.flightSet;
    }

    
    /** 
     * Get the flight set object list.
     * @return ArrayList<Flight>
     */
    public ArrayList<Flight> getFlightSetObjList() {
        return this.flightSetObjList;
    }

    
    /** 
     * Get the order number.
     * @return int
     */
    public int getNumber() {
        return this.number;
    }

    
    /** 
     * Get the user's name who makes the order.
     * @return String
     */
    public String getUsername() {
        return this.username;
    }


    public Order(String flightSet, int number, String username) {
        // NOTE: NO order index before insertion.
        this.orderIndex = -1;
        this.flightSet = flightSet;
        this.number = number;

        this.flightSetObjList = null;
        this.username = username;
    }

    public Order(
        int orderIndex, 
        String flightSet, 
        int number, 
        ArrayList<Flight> flightSetObjList, 
        String username
    ){
        this.orderIndex = orderIndex;
        this.flightSet = flightSet;
        this.number = number;

        this.flightSetObjList = flightSetObjList;
        this.username = username;
    }

    
    /** 
     * Convert the given result set to an arraylist.
     * @param db
     * @param rs
     * @return ArrayList<Order>
     */
    public static ArrayList<Order> rsToAl(Database db, ResultSet rs) {
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
                    flightSetObjList,
                    rs.getString("username")
                );

                ret.add(order);
            }

            System.out.println("Order success " + ret.size());
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            Database.closeRs(rs);
        }

        return ret;
    }


    
    /** 
     * Get the order by the given username.
     * @param db
     * @param username
     * @return ArrayList<Order>
     */
    public static ArrayList<Order> queryOrderByUsername(Database db, String username){
        IOrderDAO iOrderDAO = OrderDAO.getInstance();
        return iOrderDAO.queryOrderByUsername(db, username);
    }

    
    /** 
     * Get all orders.
     * @param db
     * @return ArrayList<Order>
     */
    public static ArrayList<Order> queryAllOrder(Database db) {
        IOrderDAO iOrderDAO = OrderDAO.getInstance();
        return iOrderDAO.queryAllOrder(db);
    }

    
    /** 
     * Add an order.
     * @param db
     * @param o
     * @return ArrayList<Order>
     */
    public static ArrayList<Order> addOrder(Database db, Order o) {
        IOrderDAO iOrderDAO = OrderDAO.getInstance();
        return iOrderDAO.addOrder(db, o.flightSet, o.number, o.username);
        // NOTE: here the ArrayList only contanins one order which is the one newly created above.
    }

    
    /** 
     * Cancel an order.
     * @param db
     * @param orderIndex
     * @return boolean
     * @throws ExDbOrderNotFound
     */
    public static boolean cancelOrder(Database db, int orderIndex) throws ExDbOrderNotFound {
        IOrderDAO iOrderDAO = OrderDAO.getInstance();
        Boolean bret = iOrderDAO.deleteOrder(db, orderIndex);
        return bret;
    }

}
