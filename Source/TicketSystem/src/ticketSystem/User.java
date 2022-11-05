package ticketSystem;

import java.util.ArrayList;

import ticketSystem.database.Database;
import ticketSystem.database.DBException.ExDbDeleteUserFailed;
import ticketSystem.database.DBException.ExDbFlightNotFound;
import ticketSystem.database.DBException.ExDbOrderNotFound;
import ticketSystem.database.DBException.ExDbSeatInsufficient;
import ticketSystem.database.DBException.ExDbUserExisted;
import ticketSystem.database.DBException.ExDbUserNotFound;
import ticketSystem.database.dao.flight.FlightDAO;
import ticketSystem.database.dao.flight.IFlightDAO;
import ticketSystem.database.dao.user.IUserDAO;
import ticketSystem.database.dao.user.UserDAO;

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

    @Override
    public People register(Database db, String username, String password) throws ExDbUserExisted {
        IUserDAO iUserDAO = UserDAO.getInstance();

        iUserDAO.addUser(db, username, password);

        this.username = username;
        this.password = password;
        return this;
    }

    @Override
    public People login(Database db, String username, String password) throws ExDbUserNotFound {
        IUserDAO iUserDAO = UserDAO.getInstance();

        iUserDAO.queryUser(db, username, password);

        this.username = username;
        this.password = password;
        return this;
    }

    @Override
    public People changePwd(Database db, String username, String newPwd) throws ExDbUserNotFound {
        IUserDAO iUserDAO = UserDAO.getInstance();
        
        iUserDAO.changePwd(db, username, newPwd);
        
        this.password = newPwd;
        return this;
    }

    @Override
    public Boolean deleteMe(Database db, String username, String password) throws ExDbDeleteUserFailed, ExDbUserNotFound {
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
