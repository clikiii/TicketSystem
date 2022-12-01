package ticketSystem;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import ticketSystem.database.Database;
import ticketSystem.database.dao.flight.FlightDAO;
import ticketSystem.database.dao.flight.IFlightDAO;
import ticketSystem.database.dbException.ExDbFlightNotFound;
import ticketSystem.database.dbException.ExDbSeatInsufficient;

public class Flight {
    private int flightIndex;
    private String fid;
    private String departure;
    private String destination;
    private Date takeOffTime;
    private Date landingTime;
    private int totalSeats;
    private int availableSeats;
    private String sellStatus;
    private int price;

    
    /** 
     * Get the flight index.
     * @return int
     */
    public int getFlightIndex() {
        return flightIndex;
    }

    
    /** 
     * Get the fid.
     * @return String
     */
    public String getFid() {
        return this.fid;
    }

    
    /** 
     * Get the departure location.
     * @return String
     */
    public String getDeparture() {
        return this.departure;
    }

    
    /** 
     * Get the destination location.
     * @return String
     */
    public String getDestination() {
        return this.destination;
    }

    
    /** 
     * Get the take off time.
     * @return Date
     */
    public Date getTakeOffTime() {
        return this.takeOffTime;
    }

    
    /** 
     * Get the landing time.
     * @return Date
     */
    public Date getLandingTime() {
        return this.landingTime;
    }

    
    /** 
     * Get the total seat number.
     * @return int
     */
    public int getTotalSeats() {
        return this.totalSeats;
    }

    
    /** 
     * Get the available seat number.
     * @return int
     */
    public int getAvailableSeats() {
        return this.availableSeats;
    }

    
    /** 
     * Get the selling status.
     * @return String
     */
    public String getSellStatus() {
        return this.sellStatus;
    }

    
    /** 
     * Get the ticket price.
     * @return int
     */
    public int getPrice() {
        return this.price;
    }


    public Flight(
        int flightIndex,
        String fid,
        String departure,
        String destination,
        Date takeOffTime,
        Date landingTime,
        int totalSeats,
        int availableSeats,
        String sellStatus,
        int price
    ) {
        this.flightIndex = flightIndex;
        this.fid = fid;
        this.departure = departure;
        this.destination = destination;
        this.takeOffTime = takeOffTime;
        this.landingTime = landingTime;
        this.totalSeats = totalSeats;
        this.availableSeats = availableSeats;
        this.sellStatus = sellStatus;
        this.price = price;
    }


    
    /** 
     * Convert the given date object to a timestamp.
     * @param date
     * @return String
     */
    private static String dateToTs(Date date){
        return String.valueOf((int)(date.getTime()/1000));
    }
    
    
    /** 
     * Convert the given timestamp to a date object.
     * @param ts
     * @return Date
     */
    private static Date tsToDate(String ts){
        return new Date(Long.parseLong(ts)*1000);
    }

    
    /** 
     * Convert the given result set to an arraylist. 
     * @param rs
     * @return ArrayList<Flight>
     */
    public static ArrayList<Flight> rsToAl(ResultSet rs) {
        ArrayList<Flight> ret = new ArrayList<>();

        try {
            while(rs.next()) {
                Flight flight = new Flight(
                    rs.getInt("flight_index"),
                    rs.getString("fid"), 
                    rs.getString("departure"), 
                    rs.getString("destination"), 
                    tsToDate(rs.getString("take_off_time")), 
                    tsToDate(rs.getString("landing_time")), 
                    rs.getInt("total_seats"),
                    rs.getInt("available_seats"), 
                    rs.getString("sell_status"), 
                    rs.getInt("price")
                );

                ret.add(flight);
            }
            System.out.println("Flight success " + ret.size());
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            Database.closeRs(rs);
        }

        return ret;
    }

    
    /** 
     * Get all flights.
     * @param db
     * @return ArrayList<Flight>
     */
    public static ArrayList<Flight> queryAllFlight(Database db) {
        IFlightDAO iFlightDAO = FlightDAO.getInstance();
        return iFlightDAO.queryAllFlight(db);
    }

    
    /** 
     * Get a flight by the given index.
     * @param db
     * @param flightIndex
     * @return ArrayList<Flight>
     */
    public static ArrayList<Flight> queryFlightByIndex(Database db, int flightIndex) {
        IFlightDAO iFlightDAO = FlightDAO.getInstance();
        return iFlightDAO.queryFlightByIndex(db, flightIndex);
    }

    
    /** 
     * Delete a flight by the given index.
     * @param db
     * @param flightIndex
     * @return boolean
     * @throws ExDbFlightNotFound
     */
    public static boolean deleteFlightByIndex(Database db, int flightIndex) throws ExDbFlightNotFound {
        IFlightDAO iFlightDAO = FlightDAO.getInstance();
        return iFlightDAO.deleteFlightByIndex(db, flightIndex);
    }

    
    /** 
     * Get the flights by the given departure location.
     * @param db
     * @param departure
     * @param startDate
     * @return ArrayList<Flight>
     */
    public static ArrayList<Flight> queryByDepart(Database db, String departure, Date startDate) {
        IFlightDAO iFlightDAO = FlightDAO.getInstance();
        return iFlightDAO.queryByDepart(db, departure, dateToTs(startDate));
    }

    
    /** 
     * Get the flights by the given destination location.
     * @param db
     * @param destination
     * @param startDate
     * @return ArrayList<Flight>
     */
    public static ArrayList<Flight> queryByDest(Database db, String destination, Date startDate) {
        IFlightDAO iFlightDAO = FlightDAO.getInstance();
        return iFlightDAO.queryByDest(db, destination, dateToTs(startDate));
    }

    
    /** 
     * Get the flights by the given departure and destination locations.
     * @param db
     * @param departure
     * @param destination
     * @param startDate
     * @return ArrayList<Flight>
     */
    public static ArrayList<Flight> queryByDepartAndDest(Database db, String departure, String destination, Date startDate) {
        IFlightDAO iFlightDAO = FlightDAO.getInstance();
        return iFlightDAO.queryByDepartAndDest(db, departure, destination, dateToTs(startDate));
    }

    
    /** 
     * Update seat number of a flight with the given index.
     * @param db
     * @param flightIndex
     * @param changeNumber
     * @return boolean
     * @throws ExDbSeatInsufficient
     * @throws ExDbFlightNotFound
     */
    public static boolean updateSeatByIndex(Database db, int flightIndex, int changeNumber) throws ExDbSeatInsufficient, ExDbFlightNotFound {
        IFlightDAO iFlightDAO = FlightDAO.getInstance();
        return iFlightDAO.updateSeatByIndex(db, flightIndex, changeNumber);
    }
}
