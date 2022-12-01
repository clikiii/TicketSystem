package ticketSystem;

import static org.mockito.ArgumentMatchers.anyString;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import ticketSystem.database.Database;
import ticketSystem.database.dbException.ExDbFlightNotFound;
import ticketSystem.database.dbException.ExDbOrderNotFound;
import ticketSystem.database.dbException.ExDbPwdIsWrong;
import ticketSystem.database.dbException.ExDbSeatInsufficient;
import ticketSystem.database.dbException.ExDbUserNotFound;

public class UserTest extends BaseTest {
    private User user;

    @Before
    public void init() throws SQLException {
        super.init();

        Mockito.when(rs.getInt("order_index")).thenReturn(1);
        Mockito.when(rs.getString("flight_set")).thenReturn("1");
        Mockito.when(rs.getInt("number")).thenReturn(1);
        Mockito.when(rs.getString("username")).thenReturn("abc");
        user = new User(db, "abc", "123");
    }

    @Test (expected = NumberFormatException.class)
    public void testAddOrderFailed1() throws ExDbFlightNotFound, ExDbSeatInsufficient {
        List<Order> list = user.addOrder("flight1", 1, "abc");
        Assert.assertEquals(1, list.size());
    }

    @Test
    public void testAddOrderFailed2() throws ExDbFlightNotFound, ExDbSeatInsufficient, SQLException {
        Mockito.when(stmt.executeQuery(anyString())).thenThrow(SQLException.class);
        Mockito.when(rs.next()).thenReturn(false, false, false);
        List<Order> list = user.addOrder("101", 1, "abc");
        Assert.assertNull(list);
    }

    @Test (expected = ExDbFlightNotFound.class)
    public void testAddOrderFailed3() throws ExDbFlightNotFound, ExDbSeatInsufficient, SQLException {
        Mockito.when(stmt.executeQuery(anyString())).thenReturn(rs);
        Mockito.when(rs.getInt("count(*)")).thenReturn(0);

        List<Order> list = user.addOrder("101", 1, "abc");
    }

    @Test (expected = ExDbSeatInsufficient.class)
    public void testAddOrderFailed4() throws ExDbFlightNotFound, ExDbSeatInsufficient, SQLException {
        Mockito.when(stmt.executeQuery(anyString())).thenReturn(rs);
        Mockito.when(rs.getInt("count(*)")).thenReturn(1);
        Mockito.when(rs.getInt("available_seats")).thenReturn(-1);

        List<Order> list = user.addOrder("101", 1, "abc");
    }

    @Test
    public void testAddOrderPass() throws ExDbFlightNotFound, ExDbSeatInsufficient, SQLException {
        Mockito.when(stmt.executeQuery(anyString())).thenReturn(rs);
        Mockito.when(rs.getInt("count(*)")).thenReturn(1);
        Mockito.when(rs.getInt("available_seats")).thenReturn(1);
        Mockito.when(rs.next()).thenReturn(true, true, true, false);

        List<Order> list = user.addOrder("101", 1, "abc");
        Assert.assertEquals(1, list.size());

        Order order = list.get(0);
        Assert.assertEquals(1, order.getOrderIndex());
        Assert.assertEquals("1", order.getFlightSet());
        Assert.assertEquals(1, order.getNumber());
        Assert.assertEquals("abc", order.getUsername());
    }

    @Test (expected = ExDbOrderNotFound.class)
    public void testCancelOrderFailed1() throws SQLException, ExDbOrderNotFound {
        Mockito.when(stmt.executeQuery(anyString())).thenReturn(rs);
        Mockito.when(rs.getInt("count(*)")).thenReturn(0);

        boolean res = user.cancelOrder(1, "101", 1);
        Assert.assertFalse(res);
    }

    @Test
    public void testCancelOrderFailed2() throws SQLException, ExDbOrderNotFound {
        Mockito.when(stmt.executeQuery(anyString())).thenReturn(rs);
        Mockito.when(rs.getInt("count(*)")).thenReturn(1);
        Mockito.when(rs.getInt("available_seats")).thenReturn(-2);

        boolean res = user.cancelOrder(1, "101", 1);
        Assert.assertFalse(res);
    }

    @Test
    public void testCancelOrderPassed() throws SQLException, ExDbOrderNotFound {
        Mockito.when(stmt.executeQuery(anyString())).thenReturn(rs);
        Mockito.when(rs.getInt("count(*)")).thenReturn(1);
        Mockito.when(rs.getInt("available_seats")).thenReturn(1);

        boolean res = user.cancelOrder(1, "101", 1);
        Assert.assertTrue(res);
    }

    @Test (expected = ExDbUserNotFound.class)
    public void testDeleteFailed1() throws ExDbUserNotFound, SQLException {
        Mockito.when(rs.getInt("count(*)")).thenReturn(0);
        Assert.assertTrue(user.deleteMe());
    }

    @Test
    public void testDeleteFailed2() throws ExDbUserNotFound, SQLException {
        Mockito.when(rs.getInt("count(*)")).thenThrow(SQLException.class);
        Assert.assertTrue(user.deleteMe());
    }

    @Test
    public void testDeleteUserPass() throws SQLException, ExDbUserNotFound {
        Mockito.when(rs.getInt("count(*)")).thenReturn(1);
        Assert.assertTrue(user.deleteMe());
    }

    @Test (expected = ExDbPwdIsWrong.class)
    public void testLoginFailed1() throws SQLException, ExDbUserNotFound, ExDbPwdIsWrong {
        Mockito.when(rs.getInt("count(*)")).thenReturn(0);
        Assert.assertNotNull(user.login(db, "abc", "123"));
    }

    @Test
    public void testLoginFailed2() throws SQLException, ExDbUserNotFound, ExDbPwdIsWrong {
        Mockito.when(rs.getInt("count(*)")).thenThrow(SQLException.class);
        Assert.assertNotNull(user.login(db, "abc", "123"));
    }

    @Test
    public void testLoginPass() throws SQLException, ExDbUserNotFound, ExDbPwdIsWrong {
        Mockito.when(rs.getInt("count(*)")).thenReturn(1);
        Assert.assertNotNull(user.login(db, "abc", "123"));
    }

    @Test (expected = ExDbUserNotFound.class)
    public void testChangePwdFailed1() throws SQLException, ExDbUserNotFound {
        Mockito.when(rs.getInt("count(*)")).thenReturn(0);
        Assert.assertNotNull(user.changePwd("123"));
    }

    @Test
    public void testChangePwdFailed2() throws SQLException, ExDbUserNotFound {
        Mockito.when(rs.getInt("count(*)")).thenThrow(SQLException.class);
        Assert.assertNotNull(user.changePwd("123"));
    }

    @Test
    public void testChangePwdPass() throws SQLException, ExDbUserNotFound {
        Mockito.when(rs.getInt("count(*)")).thenReturn(1);
        Assert.assertNotNull(user.changePwd("123"));
    }

    @Test
    public void testGetOrderFailed() throws SQLException {
        Mockito.when(stmt.executeQuery(anyString())).thenThrow(SQLException.class);
        Assert.assertNull(user.getMyOrder());
    }

    @Test
    public void testGetOrderPass() throws SQLException, ExDbUserNotFound {
        Mockito.when(rs.getInt("count(*)")).thenReturn(1);
        Mockito.when(rs.next()).thenReturn(true, false);
        Assert.assertNotNull(user.getMyOrder());
    }
}
