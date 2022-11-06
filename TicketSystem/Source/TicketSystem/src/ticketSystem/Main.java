package ticketSystem;

import ticketSystem.database.Database;
import ticketSystem.database.DBException.ExDbUserExisted;


public class Main {
    public static void main(String[] args) {
        // System.out.println("Hello world!");
        Database db = new Database();
        try {
            People currentUser = new User().register(db, "EEE", "789");
        } catch (ExDbUserExisted e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        db.closeConn();
    }
}
