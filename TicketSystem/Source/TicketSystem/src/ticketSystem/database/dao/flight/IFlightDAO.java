package ticketSystem.database.dao.flight;

import java.sql.ResultSet;

import ticketSystem.database.Database;
import ticketSystem.database.DBException.ExDbFlightrNotFound;

public interface IFlightDAO {
    public ResultSet queryAllFlight(Database db);
    public ResultSet queryFlightByFid(Database db, String fid);
    public boolean deleteFlightByFid(Database db, String fid) throws ExDbFlightrNotFound;
}
