package ticketSystem;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import ticketSystem.database.dbException.ExDbUserExisted;
import ticketSystem.database.dbException.ExDbUserNotFound;

public class TicketSystemTest extends BaseTest {
    private TicketSystem ticketSystem;

    @Before
    public void setUp() throws Exception {
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

        ticketSystem = new TicketSystem(db);
    }

    @Test
    public void testStart() {
        Assert.assertTrue(ticketSystem == TicketSystem.start());
    }

    @Test
    public void testCheckUsernameExistPass() throws SQLException {
        Mockito.when(rs.getInt("count(*)")).thenReturn(0);
        Assert.assertTrue(ticketSystem.checkUsernameExist("abc"));
    }

    @Test
    public void testRegister() throws SQLException, ExDbUserExisted {
        Mockito.when(rs.getInt("count(*)")).thenReturn(0);
        Assert.assertNotNull(ticketSystem.register("abc", "123"));
    }

    @Test
    public void testRegisterFailed() throws SQLException, ExDbUserExisted {
        Mockito.when(rs.getInt("count(*)")).thenReturn(1);
        Assert.assertNull(ticketSystem.register("abc", "123"));
    }

    @Test
    public void testLogin1() throws ExDbUserNotFound {
        People people = ticketSystem.login("admin", "admin");
        Assert.assertTrue(people instanceof Admin);
    }

    @Test
    public void testLogin2() throws SQLException, ExDbUserNotFound {
        Mockito.when(rs.getInt("count(*)")).thenReturn(1);
        People people = ticketSystem.login("abc", "123");
        Assert.assertTrue(people instanceof User);
    }

    @Test
    public void testLogin3() throws SQLException, ExDbUserNotFound {
        Mockito.when(rs.getInt("count(*)")).thenReturn(0);
        People people = ticketSystem.login("abc", "123");
        Assert.assertTrue((((User) people).getPassword().equals("password wrong")));
    }

    @Test
    public void testSearch() throws SQLException {
        Mockito.when(rs.next()).thenReturn(true, false);
        Search search = new Search("dept", "dest", new Date(), db);
        ArrayList<ArrayList<Flight>> res = ticketSystem.searchRoute("dept", "dest", new Date(),"tp1", true);
        Assert.assertNotNull(res);
    }

    @Test
    public void testTerminate() {
        ticketSystem.terminate();
    }

    @Test
    public void testCheckCity() {
        Assert.assertTrue(ticketSystem.checkCity("Beijing"));
    }
}
