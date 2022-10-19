package ticketSystem.database.dao.user;

public interface IUserDAO {
    public boolean addUser(String username, String password); // register
    public boolean changePwd(String username, String newPwd);
    public boolean queryUser(String username, String password); // login
    public boolean deleteUser(String username);
}
