package ticketSystem.database.dao.order;

import java.util.ArrayList;

import ticketSystem.Order;
import ticketSystem.database.Database;
import ticketSystem.database.dbException.ExDbOrderNotFound;

public interface IOrderDAO {
    public ArrayList<Order> queryOrderByUsername(Database db, String username);
    public ArrayList<Order> addOrder(Database db, String flightSet, int number, String username);
    public boolean deleteOrder(Database db, int orderIndex) throws ExDbOrderNotFound;
    public ArrayList<Order> queryAllOrder(Database db);
}
