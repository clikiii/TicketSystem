package ticketSystem.database.dao.flight;

import java.sql.*;
import java.util.Date;

import ticketSystem.database.Database;
import ticketSystem.database.dbException.ExDbFlightNotFound;
import ticketSystem.database.dbException.ExDbSeatInsufficient;

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
    public ResultSet queryFlightByIndex(Database db, int flightIndex) {
        Connection conn = db.connect();
        Statement stmt = null;
        ResultSet rs = null;

        try {
            conn = db.connect();
            stmt = conn.createStatement();
            String sqlSelect = "select * from ticketdb.flight where flight_index = '%d';";
            rs = stmt.executeQuery(String.format(sqlSelect, flightIndex));
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
    public boolean deleteFlightByIndex(Database db, int flightIndex) throws ExDbFlightNotFound {
        Connection conn = db.connect();
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.createStatement();
            String sqlSelect = "select count(*) from ticketdb.flight where flight_index = '%d';";
            rs = stmt.executeQuery(String.format(sqlSelect, flightIndex));
            rs.next();
            if (rs.getInt("count(*)") == 0) {
                throw new ExDbFlightNotFound();
            }else{
                String sqlUpdate = "delete from ticketdb.flight where flight_index = '%d';";
                stmt.executeUpdate(String.format(sqlUpdate, flightIndex));
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

    @Override
    public boolean updateSeatByIndex(Database db, int flightIndex, int changeNumber) throws ExDbSeatInsufficient, ExDbFlightNotFound {
        /**
         * add one: changeNumber == 1;
         * cancel one: changeNumber == -1
         */

        Connection conn = db.connect();
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.createStatement();
            String sqlSelect = "select count(*) from ticketdb.flight where flight_index = '%d';";
            rs = stmt.executeQuery(String.format(sqlSelect, flightIndex));
            rs.next();
            if (rs.getInt("count(*)") == 0) throw new ExDbFlightNotFound();
            else{
                sqlSelect = "select available_seats from ticketdb.flight where flight_index = '%d';";
                // NOTE: cuz there is a finally block, I do not think that there's a resource leakage.
                rs = stmt.executeQuery(String.format(sqlSelect, flightIndex));
                rs.next();
                int newSeat = rs.getInt("available_seats") - changeNumber;
                
                if (newSeat < 0) throw new ExDbSeatInsufficient();
                else if (newSeat == 0){
                    String sqlUpdate = "update ticketdb.flight set sell_status = 'SOLD OUT' WHERE flight_index = '%d';";
                    stmt.executeUpdate(String.format(sqlUpdate, flightIndex));
                }

                String sqlUpdate = "update ticketdb.flight set available_seats = '%d' WHERE flight_index = '%d';";
                stmt.executeUpdate(String.format(sqlUpdate, newSeat, flightIndex));
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
}
