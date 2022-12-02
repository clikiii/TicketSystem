package ticketSystem;

import ticketSystem.database.Database;
import ticketSystem.database.dao.user.IUserDAO;
import ticketSystem.database.dao.user.UserDAO;
import ticketSystem.database.dbException.ExDbUserExisted;

public interface People {

    /**
     * Register a new user.
     * @param db
     * @param username
     * @param password
     * @return
     * @throws ExDbUserExisted
     */
    public static User register(Database db, String username, String password) throws ExDbUserExisted{
        IUserDAO iUserDAO = UserDAO.getInstance();

        iUserDAO.addUser(db, username, password);

        return new User(db, username, password);
    }


    /**
     * Check the username if is exist.
     * @param db
     * @param username
     * @return
     */
    public static boolean checkUsernameExist(Database db, String username) {
        IUserDAO iUserDAO = UserDAO.getInstance();

        return iUserDAO.checkUsernameExist(db, username);
    }
}
