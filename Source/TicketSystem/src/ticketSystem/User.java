package ticketSystem;

import java.util.ArrayList;

import ticketSystem.database.Database;
import ticketSystem.database.dao.flight.FlightDAO;
import ticketSystem.database.dao.flight.IFlightDAO;
import ticketSystem.database.dao.user.IUserDAO;
import ticketSystem.database.dao.user.UserDAO;
import ticketSystem.database.dbException.ExDbFlightNotFound;
import ticketSystem.database.dbException.ExDbOrderNotFound;
import ticketSystem.database.dbException.ExDbSeatInsufficient;
import ticketSystem.database.dbException.ExDbUserNotFound;

public class User implements People {
    private String username;
    private String password;

    public User() {
        this.username = null;
        this.password = null;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public static User login(Database db, String username, String password) throws ExDbUserNotFound {
        IUserDAO iUserDAO = UserDAO.getInstance();

        iUserDAO.queryUser(db, username, password);

        return new User(username, password);
    }

    public People changePwd(Database db, String username, String newPwd) throws ExDbUserNotFound {
        IUserDAO iUserDAO = UserDAO.getInstance();
        
        iUserDAO.changePwd(db, username, newPwd);
        
        this.password = newPwd;
        return this;
    }

    public Boolean deleteMe(Database db, String username, String password) throws ExDbUserNotFound {
        IUserDAO iUserDAO = UserDAO.getInstance();

        iUserDAO.deleteUser(db, username, password);

        return true;
    }


    public ArrayList<Order> addOrder(Database db, String flightSet, int changeNumber) throws ExDbSeatInsufficient, ExDbFlightNotFound{
        IFlightDAO iFlightDAO = FlightDAO.getInstance();
        for (String fIdx: flightSet.split(" ")){
            iFlightDAO.updateSeatByIndex(db, Integer.parseInt(fIdx), changeNumber);
        }
        
        ArrayList<Order> ret = Order.addOrder(db, new Order(flightSet, changeNumber));

        return ret;
    }

    public boolean cancelOrder(Database db, int orderIndex, String flightSet, int changeNumber) throws ExDbOrderNotFound{
        boolean bret = Order.cancelOrder(db, orderIndex);

        IFlightDAO iFlightDAO = FlightDAO.getInstance();
        for (String fIdx: flightSet.split(" ")){
            try {
                iFlightDAO.updateSeatByIndex(db, Integer.parseInt(fIdx), -changeNumber);
            } catch (ExDbSeatInsufficient | ExDbFlightNotFound e) {
                e.printStackTrace();
                System.out.println("These errors are not supposed to occur.");
                return false;
            } 
        }

        return bret;
    }

    // NOTE: to get a specific user's order list, please use Order.queryOrderByUsername(Database db, String username).

}
