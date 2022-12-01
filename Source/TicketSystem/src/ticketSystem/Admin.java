package ticketSystem;

import java.util.ArrayList;

import ticketSystem.database.Database;
import ticketSystem.database.dbException.ExDbUserNotFound;

public class Admin implements People{
    private Database db;

    private static Admin instance = new Admin();
    private Admin(){
        this.db = null;
    };

    
    /** 
     * Login function for the admin.
     * @param db
     * @param username
     * @param password
     * @return Admin
     * @throws ExDbUserNotFound
     */
    public static Admin login(Database db, String username, String password) throws ExDbUserNotFound {
        if(
            username.equals("admin") &&
            password.equals("admin")
        ) {
            System.out.print("admin login " + instance.toString());
            instance.db = db;
            return instance;
        }

        throw new ExDbUserNotFound();
    }

    
    /** 
     * Get users' orders by the given username.
     * @param username
     * @return ArrayList<Order>
     */
    public ArrayList<Order> getUserOrder(String username){
        return Order.queryOrderByUsername(this.db, username);
    }

    
    /** 
     * Get all orders.
     * @return ArrayList<Order>
     */
    public ArrayList<Order> getAllOrder(){
        return Order.queryAllOrder(this.db);
    }
    
}
