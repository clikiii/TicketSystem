package ticketSystem.database.dao.user;

import ticketSystem.database.Database;
import ticketSystem.database.dbException.ExDbPwdIsWrong;
import ticketSystem.database.dbException.ExDbUserExisted;
import ticketSystem.database.dbException.ExDbUserNotFound;

import java.sql.*;

public class UserDAO implements IUserDAO{
    private static UserDAO instance = new UserDAO();
    private UserDAO(){};
    
    /** 
     * The singleton instance getter.
     * @return UserDAO
     */
    public static UserDAO getInstance() {
        return instance;
    }

    
    /** 
     * Add a new user to the database.
     * @param db
     * @param username
     * @param password
     * @return boolean
     * @throws ExDbUserExisted
     */
    @Override
    public boolean addUser(Database db, String username, String password) throws ExDbUserExisted {
        Connection conn = db.connect();
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.createStatement();
            String sqlSelect = "select count(*) from ticketdb.user where username = '%s';";
            rs = stmt.executeQuery(String.format(sqlSelect, username));
            rs.next();
            if (rs.getInt("count(*)") > 0) {
                throw new ExDbUserExisted();
            }else{
                String sqlInsert = "insert into ticketdb.user (username, password) values ('%s','%s');";
                stmt.executeUpdate(String.format(sqlInsert, username, password));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            Database.closeRs(rs);
            Database.closeStmt(stmt);
        }

        return true;
    }

    
    /** 
     * Change the password of a user.
     * @param db
     * @param username
     * @param newPwd
     * @return boolean
     * @throws ExDbUserNotFound
     */
    @Override
    public boolean changePwd(Database db, String username, String newPwd) throws ExDbUserNotFound {
        Connection conn = db.connect();
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.createStatement();
            String sqlSelect = "select count(*) from ticketdb.user where username = '%s';";
            rs = stmt.executeQuery(String.format(sqlSelect, username));
            rs.next();
            if (rs.getInt("count(*)") == 0) {
                throw new ExDbUserNotFound();
            }else{
                String sqlUpdate = "update ticketdb.user set password='%s' WHERE username = '%s';";
                stmt.executeUpdate(String.format(sqlUpdate, newPwd, username));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            Database.closeRs(rs);
            Database.closeStmt(stmt);
        }

        return true;
    }

    
    /** 
     * Get a user by the given username.
     * @param db
     * @param username
     * @param password
     * @return boolean
     * @throws ExDbUserNotFound
     * @throws ExDbPwdIsWrong
     */
    @Override
    public boolean queryUser(Database db, String username, String password) throws ExDbUserNotFound, ExDbPwdIsWrong {
        Connection conn = db.connect();
        Statement stmt = null;
        ResultSet rs = null;
        try {
            conn = db.connect();
            stmt = conn.createStatement();
            String sqlSelect = "select count(*) from ticketdb.user where username = '%s' and password = '%s';";
            rs = stmt.executeQuery(String.format(sqlSelect, username, password));
            rs.next();
            if (rs.getInt("count(*)") == 0) {
                throw new ExDbPwdIsWrong();
            }else{
                rs.close();
                stmt.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            Database.closeRs(rs);
            Database.closeStmt(stmt);
        }

        return true;
    }

    
    /** 
     * Delete a user from the database by the fiven username.
     * @param db
     * @param username
     * @param password
     * @return boolean
     * @throws ExDbUserNotFound
     */
    @Override
    public boolean deleteUser(Database db, String username, String password) throws ExDbUserNotFound {
        Connection conn = db.connect();
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.createStatement();
            String sqlSelect = "select count(*) from ticketdb.user where username = '%s';";
            rs = stmt.executeQuery(String.format(sqlSelect, username));
            rs.next();
            if (rs.getInt("count(*)") == 0) {
                throw new ExDbUserNotFound();
            }else{
                String sqlUpdate = "delete from ticketdb.user where username = '%s' and password = '%s';";
                stmt.executeUpdate(String.format(sqlUpdate, username, password));
            }       
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            Database.closeRs(rs);
            Database.closeStmt(stmt);
        }

        return true;
    }
    
    
    /** 
     * Check the given username if existed.
     * @param db
     * @param username
     * @return boolean
     */
    @Override
    public boolean checkUsernameExist(Database db, String username) {
        Connection conn = db.connect();
        Statement stmt = null;
        ResultSet rs = null;
        try {
            conn = db.connect();
            stmt = conn.createStatement();
            String sqlSelect = "select count(*) from ticketdb.user where username = '%s';";
            rs = stmt.executeQuery(String.format(sqlSelect, username));
            rs.next();

            return (rs.getInt("count(*)") == 0);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            Database.closeRs(rs);
            Database.closeStmt(stmt);
        }

    }
}
