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
    private String takeOffTime;
    private String landingTime;
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

    public String getTakeOffTime() {
        return this.takeOffTime;
    }

    public String getLandingTime() {
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
        String takeOffTime,
        String landingTime,
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

    
    private static ArrayList<Flight> rsToAl(ResultSet rs) {
        ArrayList<Flight> ret = new ArrayList<>();

        try {
            while(rs.next()) {
                Flight flight = new Flight(
                    rs.getInt("flight_index"),
                    rs.getString("fid"), 
                    rs.getString("departure"), 
                    rs.getString("destination"), 
                    rs.getString("take_off_time"), 
                    rs.getString("landing_time"), 
                    rs.getInt("total_seats"),
                    rs.getInt("available_seats"), 
                    rs.getString("sell_status"), 
                    rs.getInt("price")
                );

                ret.add(flight);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        return ret;
    }

    public static ArrayList<Flight> queryAllFlight(Database db) {
        IFlightDAO iFlightDAO = FlightDAO.getInstance();
        ResultSet rs = iFlightDAO.queryAllFlight(db);
        return rsToAl(rs);
    }

    public static ArrayList<Flight> queryFlightByFid(Database db, int flightIndex) {
        IFlightDAO iFlightDAO = FlightDAO.getInstance();
        ResultSet rs = iFlightDAO.queryFlightByIndex(db, flightIndex);
        return rsToAl(rs);
    }

    public static boolean deleteFlightByFid(Database db, int flightIndex) throws ExDbFlightNotFound {
        IFlightDAO iFlightDAO = FlightDAO.getInstance();
        Boolean bret = iFlightDAO.deleteFlightByIndex(db, flightIndex);
        return bret;
    }

    // TODO: mind the dates
    // TODO: mind the status
    public static ArrayList<Flight> queryByDepart(Database db, String departure, Date startDate) {
        IFlightDAO iFlightDAO = FlightDAO.getInstance();
        ResultSet rs = iFlightDAO.queryByDepart(db, departure, startDate);
        return rsToAl(rs);
    }

    public static ArrayList<Flight> queryByDest(Database db, String destination, Date startDate) {
        IFlightDAO iFlightDAO = FlightDAO.getInstance();
        ResultSet rs = iFlightDAO.queryByDest(db, destination, startDate);
        return rsToAl(rs);
    }

    public static ArrayList<Flight> queryByDepartAndDest(Database db, String departure, String destination, Date startDate) {
        IFlightDAO iFlightDAO = FlightDAO.getInstance();
        ResultSet rs = iFlightDAO.queryByDepartAndDest(db, departure, destination, startDate);
        return rsToAl(rs);
    }

    public static boolean updateSeatByIndex(Database db, int flightIndex, int changeNumber) throws ExDbSeatInsufficient, ExDbFlightNotFound {
        IFlightDAO iFlightDAO = FlightDAO.getInstance();
        boolean bret = iFlightDAO.updateSeatByIndex(db, flightIndex, changeNumber);
        return bret;
    }
}
