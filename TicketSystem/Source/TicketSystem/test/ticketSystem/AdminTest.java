package ticketSystem;

import static org.mockito.ArgumentMatchers.anyString;

import java.sql.SQLException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import ticketSystem.database.dbException.ExDbUserNotFound;

public class AdminTest extends BaseTest {
    private Admin admin;

    @Before
    public void setUp() throws Exception {
        super.init();

        admin = Admin.login(db, "admin", "admin");
        Mockito.when(rs.getInt("order_index")).thenReturn(1);
        Mockito.when(rs.getString("flight_set")).thenReturn("1");
        Mockito.when(rs.getInt("number")).thenReturn(1);
    }

    @Test (expected = ExDbUserNotFound.class)
    public void testLoginFailed() throws ExDbUserNotFound {
        Admin.login(db, "abc", "123");
    }

    @Test
    public void testGetUserOrderFail() throws SQLException {
        Mockito.when(stmt.executeQuery(anyString())).thenThrow(SQLException.class);
        Assert.assertNull(admin.getUserOrder("a"));
    }

    @Test
    public void testGetUserOrderPass() throws SQLException {
        Mockito.when(rs.next()).thenReturn(true, false);
        Assert.assertNotNull(admin.getUserOrder("a"));
    }

    @Test
    public void testGetAllOrderFail() throws SQLException {
        Mockito.when(stmt.executeQuery(anyString())).thenThrow(SQLException.class);
        Assert.assertEquals(0, admin.getAllOrder().size());
    }

    @Test
    public void testGetAllOrderPass() throws SQLException {
        Mockito.when(rs.next()).thenReturn(true, false);
        Assert.assertNotNull(admin.getAllOrder());
    }
}
