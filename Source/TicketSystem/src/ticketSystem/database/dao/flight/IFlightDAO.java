package ticketSystem.database.dao.flight;

import java.sql.ResultSet;

import ticketSystem.database.Database;

public interface IFlightDAO {
    public ResultSet queryAllFlight(Database db);
    public ResultSet queryFlightByFid(Database db, String fid);
    public boolean deleteFlightByFid(Database db, String fid);
}
