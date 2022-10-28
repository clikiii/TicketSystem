package ticketSystem;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import ticketSystem.database.Database;
import ticketSystem.database.DBException.ExDbFlightrNotFound;
import ticketSystem.database.dao.flight.FlightDAO;
import ticketSystem.database.dao.flight.IFlightDAO;

public class Flight {
    private String fid;
    private String departure;
    private String destination;
    private String take_off_time;
    private String landing_time;
    private int total_seats;
    private int available_seats;
    private String sell_status;
    private double price;

    public Flight(
        String fid,
        String departure,
        String destination,
        String take_off_time,
        String landing_time,
        int total_seats,
        int available_seats,
        String sell_status,
        double price
    ) {
        this.fid = fid;
        this.departure = departure;
        this.destination = destination;
        this.take_off_time = take_off_time;
        this.landing_time = landing_time;
        this.total_seats = total_seats;
        this.available_seats = available_seats;
        this.sell_status = sell_status;
        this.price = price;
    }

    private ArrayList<Flight> rsToAl(ResultSet rs) {
        ArrayList<Flight> ret = new ArrayList<>();

        try {
            while(rs.next()) {
                Flight flight = new Flight(
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

    public ArrayList<Flight> queryAllFlight(Database db) {
        IFlightDAO iFlightDAO = FlightDAO.getInstance();
        ResultSet rs = iFlightDAO.queryAllFlight(db);
        return rsToAl(rs);
    }

    public ArrayList<Flight> queryFlightByFid(Database db, String fid) {
        IFlightDAO iFlightDAO = FlightDAO.getInstance();
        ResultSet rs = iFlightDAO.queryFlightByFid(db, fid);
        return rsToAl(rs);
    }

    public boolean deleteFlightByFid(Database db, String fid) throws ExDbFlightrNotFound {
        IFlightDAO iFlightDAO = FlightDAO.getInstance();
        Boolean bret = iFlightDAO.deleteFlightByFid(db, fid);

        return bret;
    }
}
