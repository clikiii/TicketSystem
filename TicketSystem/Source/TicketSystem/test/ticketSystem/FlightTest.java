package ticketSystem;

import static org.mockito.ArgumentMatchers.anyString;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import ticketSystem.database.Database;
import ticketSystem.database.dbException.ExDbFlightNotFound;
import ticketSystem.database.dbException.ExDbSeatInsufficient;

public class FlightTest extends BaseTest {
    @Before
    public void init() throws SQLException {
        super.init();

        Mockito.when(rs.getInt("flight_index")).thenReturn(1);
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
        Mockito.when(rs.next()).thenReturn(true, false);
        ArrayList<Flight> list = Flight.queryAllFlight(db);
        Assert.assertEquals(1, list.size());

        Flight flight = list.get(0);
        Assert.assertEquals(1, flight.getFlightIndex());
        Assert.assertEquals("f1", flight.getFid());
        Assert.assertEquals("dep", flight.getDeparture());
        Assert.assertEquals("dest", flight.getDestination());
        Assert.assertEquals("take_off", flight.getTakeOffTime());
        Assert.assertEquals("landing", flight.getLandingTime());
        Assert.assertEquals(100, flight.getTotalSeats());
        Assert.assertEquals(90, flight.getAvailableSeats());
        Assert.assertEquals("sell", flight.getSellStatus());
        Assert.assertEquals(100, flight.getPrice());
    }

    @Test
    public void testQueryByIdFail() throws SQLException {
        Mockito.when(stmt.executeQuery(anyString())).thenThrow(new SQLException());
        ArrayList<Flight> list = Flight.queryFlightByIndex(db, 1);
        Assert.assertEquals(0, list.size());
    }

    @Test
    public void testQueryByDeptPass1() throws SQLException {
        Mockito.when(rs.next()).thenReturn(false);
        ArrayList<Flight> list = Flight.queryByDepart(db, "dept",new Date());
        Assert.assertEquals(0, list.size());
    }

    @Test
    public void testQueryByDeptPass2() throws SQLException {
        ResultSet rs = Mockito.mock(ResultSet.class);
        Mockito.when(stmt.executeQuery(anyString())).thenReturn(rs);
        Mockito.when(rs.next()).thenReturn(true, false);
        ArrayList<Flight> list = Flight.queryByDepart(db, "dept",new Date());
        Assert.assertEquals(1, list.size());
    }

    @Test
    public void testQueryByDeptFail() throws SQLException {
        Mockito.when(stmt.executeQuery(anyString())).thenThrow(new SQLException());
        ArrayList<Flight> list = Flight.queryByDepart(db, "dept",new Date());
        Assert.assertEquals(0, list.size());
    }

    @Test
    public void testQueryByDestPass1() throws SQLException {
        Mockito.when(rs.next()).thenReturn(false);
        ArrayList<Flight> list = Flight.queryByDest(db, "dest",new Date());
        Assert.assertEquals(0, list.size());
    }

    @Test
    public void testQueryByDestPass2() throws SQLException {
        Mockito.when(rs.next()).thenReturn(true, false);
        ArrayList<Flight> list = Flight.queryByDest(db, "dest",new Date());
        Assert.assertEquals(1, list.size());
    }

    @Test
    public void testQueryByDestFail() throws SQLException {
        Mockito.when(stmt.executeQuery(anyString())).thenThrow(new SQLException());
        ArrayList<Flight> list = Flight.queryByDest(db, "dest",new Date());
        Assert.assertEquals(0, list.size());
    }

    @Test
    public void testQueryByDeptAndDestPass1() throws SQLException {
        Mockito.when(rs.next()).thenReturn(false);
        ArrayList<Flight> list = Flight.queryByDepartAndDest(db, "dept", "dest",new Date());
        Assert.assertEquals(0, list.size());
    }

    @Test
    public void testQueryByDeptAndDestPass2() throws SQLException {
        Mockito.when(rs.next()).thenReturn(true, false);
        ArrayList<Flight> list = Flight.queryByDepartAndDest(db, "dept", "dest",new Date());
        Assert.assertEquals(1, list.size());
    }

    @Test
    public void testQueryByDeptAndDestFail() throws SQLException {
        Mockito.when(stmt.executeQuery(anyString())).thenThrow(new SQLException());
        ArrayList<Flight> list = Flight.queryByDepartAndDest(db, "dept", "dest",new Date());
        Assert.assertEquals(0, list.size());
    }

    @Test
    public void testQueryByIdPass1() throws SQLException {
        Mockito.when(rs.next()).thenReturn(false);
        ArrayList<Flight> list = Flight.queryFlightByIndex(db, 1);
        Assert.assertEquals(0, list.size());
    }

    @Test
    public void testQueryByIdPass2() throws SQLException {
        Mockito.when(rs.next()).thenReturn(true, false);
        ArrayList<Flight> list = Flight.queryFlightByIndex(db, 1);
        Assert.assertEquals(1, list.size());
    }

    @Test
    public void testDeleteFail() throws SQLException, ExDbFlightNotFound {
        Mockito.when(stmt.executeQuery(anyString())).thenThrow(new SQLException());
        Boolean res = Flight.deleteFlightByIndex(db, 1);
        Assert.assertFalse(res);
    }

    @Test (expected = ExDbFlightNotFound.class)
    public void testDeletePass1() throws SQLException, ExDbFlightNotFound {
        Mockito.when(rs.next()).thenReturn(false);
        Mockito.when(rs.getInt("count(*)")).thenReturn(0);
        Boolean res = Flight.deleteFlightByIndex(db, 1);
    }

    @Test
    public void testDeletePass2() throws SQLException, ExDbFlightNotFound {
        Mockito.when(rs.next()).thenReturn(false);
        Mockito.when(rs.getInt("count(*)")).thenReturn(1);
        Boolean res = Flight.deleteFlightByIndex(db, 1);
        Assert.assertTrue(res);
    }

    @Test (expected = ExDbFlightNotFound.class)
    public void testUpdateFail1() throws SQLException, ExDbFlightNotFound, ExDbSeatInsufficient {
        Mockito.when(rs.getInt("count(*)")).thenReturn(0);
        Boolean res = Flight.updateSeatByIndex(db, 1, 1);
        Assert.assertFalse(res);
    }

    @Test (expected = ExDbSeatInsufficient.class)
    public void testUpdateFail2() throws SQLException, ExDbFlightNotFound, ExDbSeatInsufficient {
        Mockito.when(rs.getInt("count(*)")).thenReturn(1);
        Mockito.when(rs.getInt("available_seats")).thenReturn(-1);
        Boolean res = Flight.updateSeatByIndex(db, 1, 1);
        Assert.assertFalse(res);
    }

    @Test
    public void testUpdateFail3() throws SQLException, ExDbFlightNotFound, ExDbSeatInsufficient {
        Mockito.when(stmt.executeQuery(anyString())).thenThrow(SQLException.class);
        Boolean res = Flight.updateSeatByIndex(db, 1, 1);
        Assert.assertFalse(res);
    }

    @Test
    public void testUpdatePass() throws SQLException, ExDbFlightNotFound, ExDbSeatInsufficient {
        Mockito.when(rs.getInt("count(*)")).thenReturn(1);
        Mockito.when(rs.getInt("available_seats")).thenReturn(1);
        Boolean res = Flight.updateSeatByIndex(db, 1, 1);
        Assert.assertTrue(res);
    }
}
