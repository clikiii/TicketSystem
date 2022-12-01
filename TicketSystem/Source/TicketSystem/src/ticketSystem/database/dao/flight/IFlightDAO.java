package ticketSystem.database.dao.flight;

import java.util.ArrayList;

import ticketSystem.Flight;
import ticketSystem.database.Database;
import ticketSystem.database.dbException.ExDbFlightNotFound;
import ticketSystem.database.dbException.ExDbSeatInsufficient;

public interface IFlightDAO {
    public ArrayList<Flight> queryAllFlight(Database db);
    public ArrayList<Flight> queryFlightByIndex(Database db, int flightIndex);
    public boolean deleteFlightByIndex(Database db, int flightIndex) throws ExDbFlightNotFound;
    public ArrayList<Flight> queryByDepart(Database db, String departure, String startDate);
    public ArrayList<Flight> queryByDest(Database db, String destination, String startDate);
    public ArrayList<Flight> queryByDepartAndDest(Database db, String departure, String destination, String startDate);
    public boolean updateSeatByIndex(Database db, int flightIndex, int changeNumber) throws ExDbSeatInsufficient, ExDbFlightNotFound;
}
