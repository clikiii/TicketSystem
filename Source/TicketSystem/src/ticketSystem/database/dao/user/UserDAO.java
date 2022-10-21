package ticketSystem.database.dao.user;

import ticketSystem.database.Database;
import ticketSystem.database.DBException.ExDbDeleteUserFailed;
import ticketSystem.database.DBException.ExDbUserExisted;
import ticketSystem.database.DBException.ExDbUserNotFound;
import ticketSystem.database.DBException.ExDbUserRegisterFailed;
import ticketSystem.database.DBException.ExEbChangePwdFailed;

import java.sql.*;

public class UserDAO implements IUserDAO{

    @Override
    public boolean addUser(Database db, String username, String password) {
        try {
            Connection conn = db.connect();
            Statement stmt = conn.createStatement();
            String sqlSelect = "select count(*) from ticketdb.user where username = '%s';";
            ResultSet rs = stmt.executeQuery(String.format(sqlSelect, username));
            rs.next();
            if (rs.getInt("count(*)") > 0) {
                rs.close();
                stmt.close();
                throw new ExDbUserExisted();
            }else{
                rs.close();
                String sqlInsert = "insert into ticketdb.user (username, password) values ('%s','%s');";
                if (stmt.executeUpdate(String.format(sqlInsert, username, password)) == 0){
                    stmt.close();
                    throw new ExDbUserRegisterFailed();
                }
            }
        } catch (SQLException | ExDbUserExisted | ExDbUserRegisterFailed e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return true;
    }

    @Override
    public boolean changePwd(Database db, String username, String newPwd) {
        try {
            Connection conn = db.connect();
            Statement stmt = conn.createStatement();
            String sqlSelect = "select count(*) from ticketdb.user where username = '%s';";
            ResultSet rs = stmt.executeQuery(String.format(sqlSelect, username));
            rs.next();
            if (rs.getInt("count(*)") == 0) {
                rs.close();
                stmt.close();
                throw new ExDbUserNotFound();
            }else{
                rs.close();
                String sqlUpdate = "update ticketdb.user set password='%s' WHERE username = '%s';";
                if (stmt.executeUpdate(String.format(sqlUpdate, newPwd, username)) == 0){
                    stmt.close();
                    throw new ExEbChangePwdFailed();
                }
            }
        } catch (SQLException | ExDbUserNotFound | ExEbChangePwdFailed e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return true;
    }

    @Override
    public boolean queryUser(Database db, String username, String password) {
        try {
            Connection conn = db.connect();
            Statement stmt = conn.createStatement();
            String sqlSelect = "select count(*) from ticketdb.user where username = '%s' and password = '%s';";
            ResultSet rs = stmt.executeQuery(String.format(sqlSelect, username, password));
            rs.next();
            if (rs.getInt("count(*)") == 0) {
                rs.close();
                stmt.close();
                throw new ExDbUserNotFound();
            }else{
                rs.close();
                stmt.close();
            }
        } catch (SQLException | ExDbUserNotFound e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return true;
    }

    // TODO: error handling
    @Override
    public boolean deleteUser(Database db, String username, String password) {
        try {
            Connection conn = db.connect();
            Statement stmt = conn.createStatement();
            String sqlDelete = "delete from ticketdb.user where username = '%s' and password = '%s';";
            if(stmt.executeUpdate(String.format(sqlDelete, username, password)) == 0) {
                stmt.close();
                throw new ExDbDeleteUserFailed();
            }
            
        } catch (SQLException | ExDbDeleteUserFailed e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return true;
    }
    
}
