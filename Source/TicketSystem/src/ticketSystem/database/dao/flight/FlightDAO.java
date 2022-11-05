package ticketSystem.database.dao.flight;

import java.sql.*;
import java.util.Date;

import ticketSystem.database.Database;
import ticketSystem.database.DBException.ExDbFlightrNotFound;

public class FlightDAO implements IFlightDAO {
    private static FlightDAO instance = new FlightDAO();
    private FlightDAO(){};
    public static FlightDAO getInstance() {
        return instance;
    }

    @Override
    public ResultSet queryAllFlight(Database db) {
        Connection conn = db.connect();
        Statement stmt = null;
        ResultSet rs = null;

        try {
            conn = db.connect();
            stmt = conn.createStatement();
            String sqlSelect = "select * from ticketdb.flight;";
            rs = stmt.executeQuery(String.format(sqlSelect));
        } catch (SQLException e) {
            e.printStackTrace();
            return  null;
        } finally {
            db.closeRs(rs);
            db.closeStmt(stmt);
        }

        return rs;
    }

    @Override
    public ResultSet queryFlightByFid(Database db, String fid) {
        Connection conn = db.connect();
        Statement stmt = null;
        ResultSet rs = null;

        try {
            conn = db.connect();
            stmt = conn.createStatement();
            String sqlSelect = "select * from ticketdb.flight where fid = '%s';";
            rs = stmt.executeQuery(String.format(sqlSelect, fid));
        } catch (SQLException e) {
            e.printStackTrace();
            return  null;
        } finally {
            db.closeRs(rs);
            db.closeStmt(stmt);
        }

        return rs;
    }

    @Override
    public boolean deleteFlightByFid(Database db, String fid) throws ExDbFlightrNotFound {
        Connection conn = db.connect();
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.createStatement();
            String sqlSelect = "select count(*) from ticketdb.flight where fid = '%s';";
            rs = stmt.executeQuery(String.format(sqlSelect, fid));
            rs.next();
            if (rs.getInt("count(*)") == 0) {
                throw new ExDbFlightrNotFound();
            }else{
                String sqlUpdate = "delete from ticketdb.flight where fid = '%s';";
                stmt.executeUpdate(String.format(sqlUpdate, fid));
            }       
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            db.closeRs(rs);
            db.closeStmt(stmt);
        }

        return true;
    }
    
    // TODO: add date
    @Override
    public ResultSet queryByDepart(Database db, String departure, Date startDate) {
        Connection conn = db.connect();
        Statement stmt = null;
        ResultSet rs = null;

        try {
            conn = db.connect();
            stmt = conn.createStatement();
            String sqlSelect = "select * from ticketdb.flight where departure = '%s';";
            rs = stmt.executeQuery(String.format(sqlSelect, departure));
        } catch (SQLException e) {
            e.printStackTrace();
            return  null;
        } finally {
            db.closeRs(rs);
            db.closeStmt(stmt);
        }

        return rs;
    }

    @Override
    public ResultSet queryByDest(Database db, String destination, Date startDate) {
        Connection conn = db.connect();
        Statement stmt = null;
        ResultSet rs = null;

        try {
            conn = db.connect();
            stmt = conn.createStatement();
            String sqlSelect = "select * from ticketdb.flight where destination = '%s';";
            rs = stmt.executeQuery(String.format(sqlSelect, destination));
        } catch (SQLException e) {
            e.printStackTrace();
            return  null;
        } finally {
            db.closeRs(rs);
            db.closeStmt(stmt);
        }

        return rs;
    }
    
    @Override
    public ResultSet queryByDepartAndDest(Database db, String departure, String destination, Date startDate) {
        Connection conn = db.connect();
        Statement stmt = null;
        ResultSet rs = null;

        try {
            conn = db.connect();
            stmt = conn.createStatement();
            String sqlSelect = "select * from ticketdb.flight where departure = '%s and destination = '%s';";
            rs = stmt.executeQuery(String.format(sqlSelect, departure, destination));
        } catch (SQLException e) {
            e.printStackTrace();
            return  null;
        } finally {
            db.closeRs(rs);
            db.closeStmt(stmt);
        }

        return rs;
    }
    
}
