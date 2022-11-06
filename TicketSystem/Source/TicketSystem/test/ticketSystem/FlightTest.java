package ticketSystem;

import static org.mockito.ArgumentMatchers.anyString;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import ticketSystem.Exception.ExOrderAddFailed;
import ticketSystem.Exception.ExOrderRemoveFailed;
import ticketSystem.database.DBException.ExDbDeleteUserFailed;
import ticketSystem.database.DBException.ExDbFlightrNotFound;
import ticketSystem.database.DBException.ExDbUserExisted;
import ticketSystem.database.DBException.ExDbUserNotFound;
import ticketSystem.database.Database;

public class FlightTest {
    private Database db;
    private Connection conn;
    private Statement stmt;
    private ResultSet rs;

    @Before
    public void init() throws SQLException {
        db = Mockito.mock(Database.class);
        conn = Mockito.mock(Connection.class);
        Mockito.when(db.connect()).thenReturn(conn);
        stmt = Mockito.mock(Statement.class);
        Mockito.when(conn.createStatement()).thenReturn(stmt);

        rs = Mockito.mock(ResultSet.class);
        Mockito.when(rs.getString("fid")).thenReturn("f1");
        Mockito.when(rs.getString("departure")).thenReturn("dep");
        Mockito.when(rs.getString("destination")).thenReturn("dest");
        Mockito.when(rs.getString("take_off_time")).thenReturn("take_off");
        Mockito.when(rs.getString("landing_time")).thenReturn("landing");
        Mockito.when(rs.getInt("total_seats")).thenReturn(100);
        Mockito.when(rs.getInt("available_seats")).thenReturn(90);
        Mockito.when(rs.getString("sell_status")).thenReturn("sell");
        Mockito.when(rs.getInt("price")).thenReturn(100);
    }

    @Test
    public void testQueryAllFail() throws SQLException {
        Mockito.when(stmt.executeQuery(anyString())).thenThrow(new SQLException());
        ArrayList<Flight> list = Flight.queryAllFlight(db);
        Assert.assertEquals(0, list.size());
    }

    @Test
    public void testQueryAllFail1() throws SQLException {
        ResultSet rs = Mockito.mock(ResultSet.class);
        Mockito.when(stmt.executeQuery(anyString())).thenReturn(rs);
        Mockito.when(rs.next()).thenThrow(new SQLException());
        ArrayList<Flight> list = Flight.queryAllFlight(db);
        Assert.assertNull(list);
    }

    @Test
    public void testQueryAllPass1() throws SQLException {
        ResultSet rs = Mockito.mock(ResultSet.class);
        Mockito.when(stmt.executeQuery(anyString())).thenReturn(rs);
        Mockito.when(rs.next()).thenReturn(false);
        ArrayList<Flight> list = Flight.queryAllFlight(db);
        Assert.assertEquals(0, list.size());
    }

    @Test
    public void testQueryAllPass2() throws SQLException {
        ResultSet rs = Mockito.mock(ResultSet.class);
        Mockito.when(stmt.executeQuery(anyString())).thenReturn(rs);
        Mockito.when(rs.next()).thenReturn(true, false);
        ArrayList<Flight> list = Flight.queryAllFlight(db);
        Assert.assertEquals(1, list.size());
    }

    @Test
    public void testQueryByIdFail() throws SQLException {
        Mockito.when(stmt.executeQuery(anyString())).thenThrow(new SQLException());
        ArrayList<Flight> list = Flight.queryFlightByFid(db, "1");
        Assert.assertEquals(0, list.size());
    }

    @Test
    public void testQueryByIdPass1() throws SQLException {
        ResultSet rs = Mockito.mock(ResultSet.class);
        Mockito.when(stmt.executeQuery(anyString())).thenReturn(rs);
        Mockito.when(rs.next()).thenReturn(false);
        ArrayList<Flight> list = Flight.queryFlightByFid(db, "1");
        Assert.assertEquals(0, list.size());
    }

    @Test
    public void testQueryByIdPass2() throws SQLException {
        ResultSet rs = Mockito.mock(ResultSet.class);
        Mockito.when(stmt.executeQuery(anyString())).thenReturn(rs);
        Mockito.when(rs.next()).thenReturn(true, false);
        ArrayList<Flight> list = Flight.queryFlightByFid(db, "1");
        Assert.assertEquals(1, list.size());
    }

    @Test
    public void testDeleteFail() throws SQLException, ExDbFlightrNotFound {
        Mockito.when(stmt.executeQuery(anyString())).thenThrow(new SQLException());
        Boolean res = Flight.deleteFlightByFid(db, "1");
        Assert.assertFalse(res);
    }

    @Test (expected = ExDbFlightrNotFound.class)
    public void testDeletePass1() throws SQLException, ExDbFlightrNotFound {
        ResultSet rs = Mockito.mock(ResultSet.class);
        Mockito.when(stmt.executeQuery(anyString())).thenReturn(rs);
        Mockito.when(rs.next()).thenReturn(false);
        Mockito.when(rs.getInt("count(*)")).thenReturn(0);
        Boolean res = Flight.deleteFlightByFid(db, "1");
    }

    @Test
    public void testDeletePass2() throws SQLException, ExDbFlightrNotFound {
        ResultSet rs = Mockito.mock(ResultSet.class);
        Mockito.when(stmt.executeQuery(anyString())).thenReturn(rs);
        Mockito.when(rs.next()).thenReturn(false);
        Mockito.when(rs.getInt("count(*)")).thenReturn(1);
        Boolean res = Flight.deleteFlightByFid(db, "1");
        Assert.assertTrue(res);
    }
}
