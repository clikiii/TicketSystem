package ticketSystem;

import ticketSystem.database.Database;
import ticketSystem.database.DBException.ExDbDeleteUserFailed;
import ticketSystem.database.DBException.ExDbUserExisted;
import ticketSystem.database.DBException.ExDbUserNotFound;

public interface People {
    public People register(Database db, String username, String password) throws ExDbUserExisted;
    public People login(Database db, String username, String password) throws ExDbUserNotFound;
    public People changePwd(Database db, String username, String password) throws ExDbUserNotFound;
    public Boolean deleteMe(Database db, String username, String password) throws ExDbDeleteUserFailed, ExDbUserNotFound;
}
