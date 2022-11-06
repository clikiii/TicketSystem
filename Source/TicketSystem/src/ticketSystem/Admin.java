package ticketSystem;

import ticketSystem.database.Database;
import ticketSystem.database.dbException.ExDbUserNotFound;

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
