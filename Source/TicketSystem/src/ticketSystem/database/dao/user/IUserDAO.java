package ticketSystem.database.dao.user;

import ticketSystem.database.Database;
import ticketSystem.database.dbException.ExDbPwdIsWrong;
import ticketSystem.database.dbException.ExDbUserExisted;
import ticketSystem.database.dbException.ExDbUserNotFound;

public interface IUserDAO {
    public boolean addUser(Database db, String username, String password) throws ExDbUserExisted; // register
    public boolean changePwd(Database db, String username, String newPwd) throws ExDbUserNotFound;
    public boolean queryUser(Database db, String username, String password) throws ExDbUserNotFound, ExDbPwdIsWrong; // login
    public boolean deleteUser(Database db, String username, String password) throws ExDbUserNotFound;
}