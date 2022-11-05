package ticketSystem;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import ticketSystem.database.Database;
import ticketSystem.database.DBException.ExDbFlightrNotFound;
import ticketSystem.database.dao.flight.FlightDAO;
import ticketSystem.database.dao.flight.IFlightDAO;

public class Flight {
    private String fid;
    private String departure;
    private String destination;
    private Date takeOffTime;
    private Date landingTime;
    private int totalSeats;
    private int availableSeats;
    private String sellStatus;
    private int price;

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
                    rs.getString("fid"), 
                    rs.getString("departure"), 
                    rs.getString("destination"), 
                    rs.getDate("takeOffTime"), 
                    rs.getDate("landingTime"), 
                    rs.getInt("totalSeats"),
                    rs.getInt("availableSeats"), 
                    rs.getString("sellStatus"), 
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

    public static ArrayList<Flight> queryFlightByFid(Database db, String fid) {
        IFlightDAO iFlightDAO = FlightDAO.getInstance();
        ResultSet rs = iFlightDAO.queryFlightByFid(db, fid);
        return rsToAl(rs);
    }

    public static boolean deleteFlightByFid(Database db, String fid) throws ExDbFlightrNotFound {
        IFlightDAO iFlightDAO = FlightDAO.getInstance();
        Boolean bret = iFlightDAO.deleteFlightByFid(db, fid);
        return bret;
    }

    // TODO: mind the dates
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

}
