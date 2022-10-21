package ticketSystem;

import ticketSystem.database.Database;
import ticketSystem.database.dao.user.UserDAO;

public class Main {
    public static void main(String[] args) {
        // System.out.println("Hello world!");
        Database db = new Database();
        UserDAO userDAO = new UserDAO();
        // userDAO.addUser(db, "BBB", "123");
        // userDAO.changePwd(db, "AAA", "456");
        userDAO.queryUser(db, "BBB", "123");
        // userDAO.deleteUser(db, "AAA", "456");
        db.closeConn();
    }
}
