package ticketSystem;

import java.util.ArrayList;

import ticketSystem.database.Database;
import ticketSystem.database.dao.flight.FlightDAO;
import ticketSystem.database.dao.flight.IFlightDAO;
import ticketSystem.database.dao.user.IUserDAO;
import ticketSystem.database.dao.user.UserDAO;
import ticketSystem.database.dbException.ExDbFlightNotFound;
import ticketSystem.database.dbException.ExDbOrderNotFound;
import ticketSystem.database.dbException.ExDbPwdIsWrong;
import ticketSystem.database.dbException.ExDbSeatInsufficient;
import ticketSystem.database.dbException.ExDbUserNotFound;

public class User implements People {
    private String username;
    private String password;
    private Database db;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
    

    public User(Database db, String username, String password) {
        this.username = username;
        this.password = password;
        this.db = db;
    }

    public static User login(Database db, String username, String password) throws ExDbUserNotFound, ExDbPwdIsWrong {
        IUserDAO iUserDAO = UserDAO.getInstance();

        if (iUserDAO.queryUser(db, username, password)) {
            return new User(db, username, password);
        }

        return null;
    }

    public People changePwd(String newPwd) throws ExDbUserNotFound {
        IUserDAO iUserDAO = UserDAO.getInstance();
        
        if (iUserDAO.changePwd(this.db, this.username, newPwd)) {
            this.password = newPwd;
            return this;
        }
        
        return null;
    }

    public Boolean deleteMe() throws ExDbUserNotFound {
        IUserDAO iUserDAO = UserDAO.getInstance();

        iUserDAO.deleteUser(this.db, this.username, this.password);

        return true;
    }


    public ArrayList<Order> addOrder(String flightSet, int changeNumber, String username) throws ExDbSeatInsufficient, ExDbFlightNotFound{
        IFlightDAO iFlightDAO = FlightDAO.getInstance();
        for (String fIdx: flightSet.split(" ")){
            iFlightDAO.updateSeatByIndex(this.db, Integer.parseInt(fIdx), changeNumber);
        }

        ArrayList<Order> ret = Order.addOrder(this.db, new Order(flightSet, changeNumber, username));

        return ret;
    }

    public boolean cancelOrder(int orderIndex, String flightSet, int changeNumber) throws ExDbOrderNotFound{
        boolean bret = Order.cancelOrder(this.db, orderIndex);

        IFlightDAO iFlightDAO = FlightDAO.getInstance();
        for (String fIdx: flightSet.split(" ")){
            try {
                iFlightDAO.updateSeatByIndex(this.db, Integer.parseInt(fIdx), -changeNumber);
            } catch (ExDbSeatInsufficient | ExDbFlightNotFound e) {
                e.printStackTrace();
                System.out.println("These errors are not supposed to occur.");
                return false;
            } 
        }

        return bret;
    }

    public ArrayList<Order> getMyOrder(){
        return Order.queryOrderByUsername(this.db, username);
    }

}
