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

    
    /** 
     * Get the username.
     * @return String
     */
    public String getUsername() {
        return username;
    }

    
    /** 
     * Get the password.
     * @return String
     */
    public String getPassword() {
        return password;
    }
    

    public User(Database db, String username, String password) {
        this.username = username;
        this.password = password;
        this.db = db;
    }

    
    /** 
     * Login a user.
     * @param db
     * @param username
     * @param password
     * @return User
     * @throws ExDbUserNotFound
     * @throws ExDbPwdIsWrong
     */
    public static User login(Database db, String username, String password) throws ExDbUserNotFound, ExDbPwdIsWrong {
        IUserDAO iUserDAO = UserDAO.getInstance();

        iUserDAO.queryUser(db, username, password);

        return new User(db, username, password);
    }

    
    /** 
     * Change the password for a specific user.
     * @param newPwd
     * @return People
     * @throws ExDbUserNotFound
     */
    public People changePwd(String newPwd) throws ExDbUserNotFound {
        IUserDAO iUserDAO = UserDAO.getInstance();
        
        iUserDAO.changePwd(this.db, this.username, newPwd);
        
        this.password = newPwd;
        return this;
    }

    
    /** 
     * Close the account for a specific user.
     * @return Boolean
     * @throws ExDbUserNotFound
     */
    public Boolean deleteMe() throws ExDbUserNotFound {
        IUserDAO iUserDAO = UserDAO.getInstance();

        iUserDAO.deleteUser(this.db, this.username, this.password);

        return true;
    }


    
    /** 
     * Make an order.
     * @param flightSet
     * @param changeNumber
     * @param username
     * @return ArrayList<Order>
     * @throws ExDbSeatInsufficient
     * @throws ExDbFlightNotFound
     */
    public ArrayList<Order> addOrder(String flightSet, int changeNumber, String username) throws ExDbSeatInsufficient, ExDbFlightNotFound{
        IFlightDAO iFlightDAO = FlightDAO.getInstance();
        for (String fIdx: flightSet.split(" ")){
            iFlightDAO.updateSeatByIndex(this.db, Integer.parseInt(fIdx), changeNumber);
        }

        ArrayList<Order> ret = Order.addOrder(this.db, new Order(flightSet, changeNumber, username));

        return ret;
    }

    
    /** 
     * Cancel an existed order.
     * @param orderIndex
     * @param flightSet
     * @param changeNumber
     * @return boolean
     * @throws ExDbOrderNotFound
     */
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

    
    /** 
     * Get the orders of the user.
     * @return ArrayList<Order>
     */
    public ArrayList<Order> getMyOrder(){
        return Order.queryOrderByUsername(this.db, username);
    }

}
