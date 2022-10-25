package ticketSystem.database.dao.flight;

import java.sql.*;

import ticketSystem.database.Database;

public class FlightDAO implements IFlightDAO {
    private static FlightDAO instance = new FlightDAO();
    private FlightDAO(){};
    public static FlightDAO getInstance() {
        return instance;
    }

    @Override
    public ResultSet queryAllFlight(Database db) {
        // Connection conn = db.connect();
        // Statement stmt = conn.createStatement();
        // String sqlSelect = "select * from ticketdb.flight;";
        // ResultSet rs = stmt.executeQuery(sqlSelect);
        return null;
    }

    @Override
    public ResultSet queryFlightByFid(Database db, String fid) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean deleteFlightByFid(Database db, String fid) {
        // TODO Auto-generated method stub
        return false;
    }
    
}
