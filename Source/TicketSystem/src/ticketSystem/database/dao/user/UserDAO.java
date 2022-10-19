package ticketSystem.database.dao.user;

import ticketSystem.database.Database;
import ticketSystem.database.DBException.ExDbUserNotFound;

import java.sql.*;

public class UserDAO implements IUserDAO{

    @Override
    public boolean addUser(String username, String password) {
        Connection conn = (new Database()).connect();
        Statement stmt = conn.createStatement();
        String sqlSelect = "select username from ticketdb.user where username = '%s';";
        ResultSet rs = stmt.executeQuery(String.format(sqlSelect, username));
        rs.last();
        if (rs.getRow() > 0) {
            throw new ExDbUserNotFound();
        }

        return false;
    }

    @Override
    public boolean changePwd(String username, String newPwd) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean queryUser(String username, String password) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean deleteUser(String username) {
        // TODO Auto-generated method stub
        return false;
    }
    
}
