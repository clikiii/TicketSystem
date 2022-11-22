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

    public static Admin login(Database db, String username, String password) throws ExDbUserNotFound {
        if(
            username == "admin" &&
            password == "admin"
        ) {
            instance.db = db;
            return instance;
        }

        throw new ExDbUserNotFound();
    }

    public ArrayList<Order> getUserOrder(String username){
        return Order.queryOrderByUsername(this.db, username);
    }

    public ArrayList<Order> getAllOrder(){
        return Order.queryAllOrder(this.db);
    }
    
}
