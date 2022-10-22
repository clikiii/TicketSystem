package ticketSystem;

import ticketSystem.database.Database;
import ticketSystem.database.dao.user.UserDAO;

public class Main {
    public static void main(String[] args) {
        // System.out.println("Hello world!");
        Database db = new Database();
        People currentUser = new User().register(db, "CCC", "789");
        db.closeConn();
    }
}
