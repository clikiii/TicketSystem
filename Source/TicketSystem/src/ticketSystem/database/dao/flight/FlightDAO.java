package ticketSystem.database.dao.flight;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;

import ticketSystem.Flight;
import ticketSystem.database.Database;
import ticketSystem.database.dbException.ExDbFlightNotFound;
import ticketSystem.database.dbException.ExDbSeatInsufficient;

public class FlightDAO implements IFlightDAO {
    private static FlightDAO instance = new FlightDAO();
    private FlightDAO(){};
    
    /** 
     * The singleton instance getter.
     * @return FlightDAO
     */
    public static FlightDAO getInstance() {
        return instance;
    }

    /**
     * Get the timestamp of the next day from the database given by a start date.
     * @param startDate
     * @return the timestamp of the next day
     */
    private static String getTomorrowTs(String startDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
        try {
            Date today = formatter.parse(formatter.format( new Date(Long.parseLong(startDate)*1000) ));
            return String.valueOf((int)(today.getTime()/1000 + 24*60*60));

        } catch (NumberFormatException | ParseException e) {
            e.printStackTrace();
        }

        return null;
    }

    
    /** 
     * Get all flights from the database.
     * @param db
     * @return ArrayList<Flight>
     */
    @Override
    public ArrayList<Flight> queryAllFlight(Database db) {
        Connection conn = db.connect();
        Statement stmt = null;
        ResultSet rs = null;

        try {
            conn = db.connect();
            stmt = conn.createStatement();
            String sqlSelect = "select * from ticketdb.flight;";
            rs = stmt.executeQuery(String.format(sqlSelect));

            return Flight.rsToAl(rs);

        } catch (SQLException e) {
            e.printStackTrace();
            return  null;
        } finally {
            Database.closeStmt(stmt);
        }

    }

    
    /** 
     * Get the flight from the database by the given index.
     * @param db
     * @param flightIndex
     * @return ArrayList<Flight>
     */
    @Override
    public ArrayList<Flight> queryFlightByIndex(Database db, int flightIndex) {
        Connection conn = db.connect();
        Statement stmt = null;
        ResultSet rs = null;

        try {
            conn = db.connect();
            stmt = conn.createStatement();
            String sqlSelect = "select * from ticketdb.flight where flight_index = '%d';";
            rs = stmt.executeQuery(String.format(sqlSelect, flightIndex));

            return Flight.rsToAl(rs);

        } catch (SQLException e) {
            e.printStackTrace();
            return  null;
        } finally {
            Database.closeStmt(stmt);
        }

    }

    
    /** 
     * Delete the flight from the database by the given index.
     * @param db
     * @param flightIndex
     * @return boolean
     * @throws ExDbFlightNotFound
     */
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

            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            Database.closeStmt(stmt);
        }

    }
    
    
    /** 
     * Get the flight from the database by the given departure location and the start date.
     * @param db
     * @param departure
     * @param startDate
     * @return ArrayList<Flight>
     */
    @Override
    public ArrayList<Flight> queryByDepart(Database db, String departure, String startDate) {
        Connection conn = db.connect();
        Statement stmt = null;
        ResultSet rs = null;

        int endDate = Integer.parseInt(getTomorrowTs(startDate));

        try {
            conn = db.connect();
            stmt = conn.createStatement();
            String sqlSelect = "select * from ticketdb.flight where departure = '%s' and cast(take_off_time as signed) > '%d' and cast(take_off_time as signed) < '%d' and sell_status = 'SELLING';";
            rs = stmt.executeQuery(String.format(sqlSelect, departure, Integer.parseInt(startDate), endDate));

            return Flight.rsToAl(rs);

        } catch (SQLException e) {
            e.printStackTrace();
            return  null;
        } finally {
            Database.closeStmt(stmt);
        }
    }

    
    /** 
     * Get the flight from the database by the given destination location and the start date.
     * @param db
     * @param destination
     * @param startDate
     * @return ArrayList<Flight>
     */
    @Override
    public ArrayList<Flight> queryByDest(Database db, String destination, String startDate) {
        Connection conn = db.connect();
        Statement stmt = null;
        ResultSet rs = null;

        try {
            conn = db.connect();
            stmt = conn.createStatement();
            String sqlSelect = "select * from ticketdb.flight where destination = '%s' and cast(take_off_time as signed) > '%d' and sell_status = 'SELLING';";
            rs = stmt.executeQuery(String.format(sqlSelect, destination, Integer.parseInt(startDate)));

            return Flight.rsToAl(rs);

        } catch (SQLException e) {
            e.printStackTrace();
            return  null;
        } finally {
            Database.closeStmt(stmt);
        }
    }
    
    
    /** 
     * Get the flight from the database by the given destination and departure locations and the start date.
     * @param db
     * @param departure
     * @param destination
     * @param startDate
     * @return ArrayList<Flight>
     */
    @Override
    public ArrayList<Flight> queryByDepartAndDest(Database db, String departure, String destination, String startDate) {
        Connection conn = db.connect();
        Statement stmt = null;
        ResultSet rs = null;

        int endDate = Integer.parseInt(getTomorrowTs(startDate));

        try {
            conn = db.connect();
            stmt = conn.createStatement();
            String sqlSelect = "select * from ticketdb.flight where departure = '%s' and destination = '%s' and cast(take_off_time as signed) > '%d' and cast(take_off_time as signed) < '%d' and sell_status = 'SELLING';";
            System.out.println("query " + String.format(sqlSelect, departure, destination, Integer.parseInt(startDate), endDate));
            rs = stmt.executeQuery(String.format(sqlSelect, departure, destination, Integer.parseInt(startDate), endDate));

            return Flight.rsToAl(rs);
            
        } catch (SQLException e) {
            e.printStackTrace();
            return  null;
        } finally {
            Database.closeStmt(stmt);
        }

    }

    
    /** 
     * Update the seat number of the flight with the given index.
     * @param db
     * @param flightIndex
     * @param changeNumber
     * @return boolean
     * @throws ExDbSeatInsufficient
     * @throws ExDbFlightNotFound
     */
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

            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            Database.closeStmt(stmt);
        }

    }
}
