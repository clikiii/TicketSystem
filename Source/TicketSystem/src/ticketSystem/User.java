package ticketSystem;

import java.util.ArrayList;

import ticketSystem.Exception.ExOrderAddFailed;
import ticketSystem.Exception.ExOrderRemoveFailed;
import ticketSystem.database.Database;
import ticketSystem.database.DBException.ExDbDeleteUserFailed;
import ticketSystem.database.DBException.ExDbUserExisted;
import ticketSystem.database.DBException.ExDbUserNotFound;
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


    // public ArrayList<Order> addOrder(Order o) throws ExOrderAddFailed {
    //     if (!myOrders.add(o)) {
    //         throw new ExOrderAddFailed();
    //     };
    //     return myOrders;
    // }

    // public ArrayList<Order> cancelOrder(Order o) throws ExOrderRemoveFailed {
    //     if (myOrders.remove(o)) {
    //         throw new ExOrderRemoveFailed();
    //     };
    //     return myOrders;
    // }

}
