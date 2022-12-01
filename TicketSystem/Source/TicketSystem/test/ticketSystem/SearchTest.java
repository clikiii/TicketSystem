package ticketSystem;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class SearchTest extends BaseTest {
    @Before
    public void init() throws SQLException {
        super.init();
        Mockito.when(rs.getInt("flight_index")).thenReturn(1);
        Mockito.when(rs.getString("fid")).thenReturn("f1");
        Mockito.when(rs.getString("departure")).thenReturn("dep");
        Mockito.when(rs.getString("destination")).thenReturn("dest");
        Mockito.when(rs.getString("take_off_time")).thenReturn("1000");
        Mockito.when(rs.getString("landing_time")).thenReturn("2000");
        Mockito.when(rs.getInt("total_seats")).thenReturn(100);
        Mockito.when(rs.getInt("available_seats")).thenReturn(90);
        Mockito.when(rs.getString("sell_status")).thenReturn("sell");
        Mockito.when(rs.getInt("price")).thenReturn(100);
    }

    @Test
    public void testSearchRoute1() throws SQLException {
        Mockito.when(rs.next()).thenReturn(true, false);
        Search search = new Search("dept", "dest", new Date(), db);
        ArrayList<ArrayList<Flight>> res = search.searchRoute("tp1", true);
        Assert.assertNotNull(res);
    }

    @Test
    public void testSearchRoute2() throws SQLException {
        Mockito.when(rs.next()).thenReturn(true, false, true, false, true, false);
        Search search = new Search("dept", "dest", new Date(), db);
        ArrayList<ArrayList<Flight>> res = search.searchRoute("tp1", false);
        Assert.assertNotNull(res);
    }
}
