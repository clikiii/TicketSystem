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

        ticketSystem = new TicketSystem(db);
    }

    @Test
    public void testRegister() throws SQLException, ExDbUserExisted {
        Mockito.when(rs.getInt("count(*)")).thenReturn(0);
        Assert.assertNotNull(ticketSystem.register("abc", "123"));
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
}
