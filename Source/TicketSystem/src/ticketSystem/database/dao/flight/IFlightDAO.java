package ticketSystem.database.dao.flight;

import java.sql.ResultSet;
import java.util.Date;

import ticketSystem.database.Database;
import ticketSystem.database.dbException.ExDbFlightNotFound;
import ticketSystem.database.dbException.ExDbSeatInsufficient;

public interface IFlightDAO {
    public ResultSet queryAllFlight(Database db);
    public ResultSet queryFlightByIndex(Database db, int flightIndex);
    public boolean deleteFlightByIndex(Database db, int flightIndex) throws ExDbFlightNotFound;
    public ResultSet queryByDepart(Database db, String departure, Date startDate);
    public ResultSet queryByDest(Database db, String destination, Date startDate);
    public ResultSet queryByDepartAndDest(Database db, String departure, String destination, Date startDate);
    public boolean updateSeatByIndex(Database db, int flightIndex, int changeNumber) throws ExDbSeatInsufficient, ExDbFlightNotFound;
}
