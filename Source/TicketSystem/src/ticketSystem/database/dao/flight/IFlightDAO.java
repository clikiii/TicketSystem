package ticketSystem.database.dao.flight;

import java.sql.ResultSet;
import java.util.Date;

import ticketSystem.database.Database;
import ticketSystem.database.DBException.ExDbFlightNotFound;

public interface IFlightDAO {
    public ResultSet queryAllFlight(Database db);
    public ResultSet queryFlightByFid(Database db, String fid);
    public boolean deleteFlightByFid(Database db, String fid) throws ExDbFlightNotFound;
    public ResultSet queryByDepart(Database db, String departure, Date startDate);
    public ResultSet queryByDest(Database db, String destination, Date startDate);
    public ResultSet queryByDepartAndDest(Database db, String departure, String destination, Date startDate);
}
