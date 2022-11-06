package ticketSystem;

import ticketSystem.database.Database;
import ticketSystem.database.DBException.ExDbUserExisted;
import ticketSystem.database.DBException.ExDbUserNotFound;
import ticketSystem.database.dao.user.IUserDAO;
import ticketSystem.database.dao.user.UserDAO;

public class Admin implements People{
    private static Admin instance = new Admin();
    private Admin(){};

    public static Admin login(Database db, String username, String password) throws ExDbUserNotFound {
        if(
            username == "admin" &&
            password == "admin"
        ) return instance;

        throw new ExDbUserNotFound();
    }
    
}
