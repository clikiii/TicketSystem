package ticketSystem.database.dao.user;

import ticketSystem.database.Database;

public interface IUserDAO {
    public boolean addUser(Database db, String username, String password); // register
    public boolean changePwd(Database db, String username, String newPwd);
    public boolean queryUser(Database db, String username, String password); // login
    public boolean deleteUser(Database db, String username, String password);
}
