package ticketSystem;

import ticketSystem.database.Database;
import ticketSystem.database.dao.user.IUserDAO;
import ticketSystem.database.dao.user.UserDAO;
import ticketSystem.database.dbException.ExDbUserExisted;

public interface People {
    public static User register(Database db, String username, String password) throws ExDbUserExisted{
        IUserDAO iUserDAO = UserDAO.getInstance();

        iUserDAO.addUser(db, username, password);

        return new User(db, username, password);
    }
}
