package ticketSystem.database.dao.user;

import ticketSystem.database.Database;
import ticketSystem.database.DBException.ExDbUserExisted;
import ticketSystem.database.DBException.ExDbUserNotFound;

public interface IUserDAO {
    public boolean addUser(Database db, String username, String password) throws ExDbUserExisted; // register
    public boolean changePwd(Database db, String username, String newPwd) throws ExDbUserNotFound;
    public boolean queryUser(Database db, String username, String password) throws ExDbUserNotFound; // login
    public boolean deleteUser(Database db, String username, String password) throws ExDbUserNotFound;
}
