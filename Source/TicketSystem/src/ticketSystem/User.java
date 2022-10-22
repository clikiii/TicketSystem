package ticketSystem;

import java.util.ArrayList;

import ticketSystem.Exception.ExOrderAddFailed;
import ticketSystem.Exception.ExOrderRemoveFailed;
import ticketSystem.database.Database;
import ticketSystem.database.dao.user.IUserDAO;
import ticketSystem.database.dao.user.UserDAO;

public class User implements People {
    private String username;
    private String password;
    private ArrayList<Order> myOrders;

    public User() {
        this.username = null;
        this.password = null;
        myOrders = null;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.myOrders = new ArrayList<>();
    }

    @Override
    public People register(Database db, String username, String password) {
        IUserDAO iUserDao = new UserDAO();

        iUserDao.addUser(db, username, password);

        this.username = username;
        this.password = password;
        this.myOrders = new ArrayList<>();
        return this;
    }

    @Override
    public People login(Database db, String username, String password) {
        IUserDAO iUserDAO = new UserDAO();

        iUserDAO.queryUser(db, username, password);

        this.username = username;
        this.password = password;
        this.myOrders = new ArrayList<>();
        return this;
    }

    @Override
    public People changePwd(Database db, String username, String newPwd) {
        IUserDAO iUserDAO = new UserDAO();
        
        if (iUserDAO.queryUser(db, username, password))
            iUserDAO.changePwd(db, username, newPwd);
        
        this.password = newPwd;

        return this;
    }

    @Override
    public Boolean deleteMe(Database db, String username, String password) {
        IUserDAO iUserDAO = new UserDAO();

        iUserDAO.deleteUser(db, username, password);

        return true;
    }


    public ArrayList<Order> addOrder(Order o) throws ExOrderAddFailed {
        if (!myOrders.add(o)) {
            throw new ExOrderAddFailed();
        };
        return myOrders;
    }

    public ArrayList<Order> cancelOrder(Order o) throws ExOrderRemoveFailed {
        if (myOrders.remove(o)) {
            throw new ExOrderRemoveFailed();
        };
        return myOrders;
    }
    
}
