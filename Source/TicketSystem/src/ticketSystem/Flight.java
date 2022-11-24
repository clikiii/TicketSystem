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

    public int getFlightIndex() {
        return flightIndex;
    }

    public String getFid() {
        return this.fid;
    }

    public String getDeparture() {
        return this.departure;
    }

    public String getDestination() {
        return this.destination;
    }

    public Date getTakeOffTime() {
        return this.takeOffTime;
    }

    public Date getLandingTime() {
        return this.landingTime;
    }

    public int getTotalSeats() {
        return this.totalSeats;
    }

    public int getAvailableSeats() {
        return this.availableSeats;
    }

    public String getSellStatus() {
        return this.sellStatus;
    }

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


    private static String dateToTs(Date date){
        return String.valueOf((int)(date.getTime()/1000));
    }
    
    private static Date tsToDate(String ts){
        return new Date(Long.parseLong(ts)*1000);
    }

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
            System.out.println("success" + ret.size());
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            System.out.println("finally" + rs.toString());
            Database.closeRs(rs);
        }

        return ret;
    }

    public static ArrayList<Flight> queryAllFlight(Database db) {
        IFlightDAO iFlightDAO = FlightDAO.getInstance();
        return iFlightDAO.queryAllFlight(db);
    }

    public static ArrayList<Flight> queryFlightByIndex(Database db, int flightIndex) {
        IFlightDAO iFlightDAO = FlightDAO.getInstance();
        return iFlightDAO.queryFlightByIndex(db, flightIndex);
    }

    public static boolean deleteFlightByIndex(Database db, int flightIndex) throws ExDbFlightNotFound {
        IFlightDAO iFlightDAO = FlightDAO.getInstance();
        return iFlightDAO.deleteFlightByIndex(db, flightIndex);
    }

    // TODO: mind the dates
    // TODO: mind the status
    public static ArrayList<Flight> queryByDepart(Database db, String departure, Date startDate) {
        IFlightDAO iFlightDAO = FlightDAO.getInstance();
        return iFlightDAO.queryByDepart(db, departure, dateToTs(startDate));
    }

    public static ArrayList<Flight> queryByDest(Database db, String destination, Date startDate) {
        IFlightDAO iFlightDAO = FlightDAO.getInstance();
        return iFlightDAO.queryByDest(db, destination, dateToTs(startDate));
    }

    public static ArrayList<Flight> queryByDepartAndDest(Database db, String departure, String destination, Date startDate) {
        IFlightDAO iFlightDAO = FlightDAO.getInstance();
        return iFlightDAO.queryByDepartAndDest(db, departure, destination, dateToTs(startDate));
    }

    public static boolean updateSeatByIndex(Database db, int flightIndex, int changeNumber) throws ExDbSeatInsufficient, ExDbFlightNotFound {
        IFlightDAO iFlightDAO = FlightDAO.getInstance();
        return iFlightDAO.updateSeatByIndex(db, flightIndex, changeNumber);
    }
}
