package ticketSystem.database.dao.order;

import java.sql.ResultSet;

import ticketSystem.database.Database;
import ticketSystem.database.DBException.ExDbOrderNotFound;

public interface IOrderDAO {
    public ResultSet queryOrderByUsername(Database db, String username);
    public ResultSet addOrder(Database db, String flightSet, int number);
    public boolean deleteOrder(Database db, int orderIndex) throws ExDbOrderNotFound;
    public ResultSet queryAllOrder(Database db);
}
