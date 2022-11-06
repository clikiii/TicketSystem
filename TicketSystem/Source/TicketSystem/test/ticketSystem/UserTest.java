package ticketSystem;

import static org.mockito.ArgumentMatchers.anyString;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import ticketSystem.Exception.ExOrderAddFailed;
import ticketSystem.Exception.ExOrderRemoveFailed;
import ticketSystem.database.DBException.ExDbDeleteUserFailed;
import ticketSystem.database.DBException.ExDbUserExisted;
import ticketSystem.database.DBException.ExDbUserNotFound;
import ticketSystem.database.Database;

public class UserTest {
    @Test (expected = NullPointerException.class)
    public void testAddOrderFailed() throws ExOrderAddFailed {
        User user = new User();
        user.addOrder(new Order("test"));
    }

    @Test
    public void testAddOrderPassed() throws ExOrderAddFailed {
        User user = new User("abc", "123");
        List<Order> list = user.addOrder(new Order("test"));
        Assert.assertEquals(1, list.size());
    }

    @Test (expected = ExOrderRemoveFailed.class)
    public void testCancelOrderFailed() throws ExOrderAddFailed, ExOrderRemoveFailed {
        User user = new User("abc", "123");
        Order o1 = new Order("test");
        List<Order> list = user.addOrder(o1);
        Assert.assertEquals(1, list.size());

        list = user.cancelOrder(new Order("123"));
        Assert.assertEquals(1, list.size());
    }

    @Test
    public void testCancelOrderPassed() throws ExOrderAddFailed, ExOrderRemoveFailed {
        User user = new User("abc", "123");
        Order o1 = new Order("test");
        List<Order> list = user.addOrder(o1);
        Assert.assertEquals(1, list.size());

        list = user.cancelOrder(o1);
        Assert.assertEquals(0, list.size());
    }

    @Test (expected = ExDbUserNotFound.class)
    public void testDeleteUserFailed1() throws SQLException, ExDbUserNotFound, ExDbDeleteUserFailed {
        User user = new User();
        Database db = Mockito.mock(Database.class);
        Connection conn = Mockito.mock(Connection.class);
        Mockito.when(db.connect()).thenReturn(conn);
        Statement stmt = Mockito.mock(Statement.class);
        Mockito.when(conn.createStatement()).thenReturn(stmt);
        ResultSet rs = Mockito.mock(ResultSet.class);
        Mockito.when(stmt.executeQuery(anyString())).thenReturn(rs);
        Mockito.when(rs.getInt(anyString())).thenReturn(0);

        Assert.assertTrue(user.deleteMe(db, "abc", "123"));
    }

    @Test
    public void testDeleteUserFailed2() throws SQLException, ExDbUserNotFound, ExDbDeleteUserFailed {
        User user = new User();
        Database db = Mockito.mock(Database.class);
        Connection conn = Mockito.mock(Connection.class);
        Mockito.when(db.connect()).thenReturn(conn);
        Statement stmt = Mockito.mock(Statement.class);
        Mockito.when(conn.createStatement()).thenReturn(stmt);
        ResultSet rs = Mockito.mock(ResultSet.class);
        Mockito.when(stmt.executeQuery(anyString())).thenThrow(new SQLException());

        Assert.assertTrue(user.deleteMe(db, "abc", "123"));
    }

    @Test
    public void testDeleteUserPassed() throws SQLException, ExDbUserNotFound, ExDbDeleteUserFailed {
        User user = new User();
        Database db = Mockito.mock(Database.class);
        Connection conn = Mockito.mock(Connection.class);
        Mockito.when(db.connect()).thenReturn(conn);
        Statement stmt = Mockito.mock(Statement.class);
        Mockito.when(conn.createStatement()).thenReturn(stmt);
        ResultSet rs = Mockito.mock(ResultSet.class);
        Mockito.when(stmt.executeQuery(anyString())).thenReturn(rs);
        Mockito.when(rs.getInt(anyString())).thenReturn(1);

        Assert.assertTrue(user.deleteMe(db, "abc", "123"));
    }

    @Test (expected = ExDbUserExisted.class)
    public void testRegisterFailed1() throws SQLException, ExDbUserExisted {
        User user = new User();
        Database db = Mockito.mock(Database.class);
        Connection conn = Mockito.mock(Connection.class);
        Mockito.when(db.connect()).thenReturn(conn);
        Statement stmt = Mockito.mock(Statement.class);
        Mockito.when(conn.createStatement()).thenReturn(stmt);
        ResultSet rs = Mockito.mock(ResultSet.class);
        Mockito.when(stmt.executeQuery(anyString())).thenReturn(rs);
        Mockito.when(rs.getInt(anyString())).thenReturn(1);

        Assert.assertNotNull(user.register(db, "abc", "123"));
    }

    @Test
    public void testRegisterFailed2() throws SQLException, ExDbUserExisted {
        User user = new User();
        Database db = Mockito.mock(Database.class);
        Connection conn = Mockito.mock(Connection.class);
        Mockito.when(db.connect()).thenReturn(conn);
        Statement stmt = Mockito.mock(Statement.class);
        Mockito.when(conn.createStatement()).thenReturn(stmt);
        ResultSet rs = Mockito.mock(ResultSet.class);
        Mockito.when(stmt.executeQuery(anyString())).thenThrow(new SQLException());

        Assert.assertNotNull(user.register(db, "abc", "123"));
    }

    @Test
    public void testRegisterPassed() throws SQLException, ExDbUserExisted {
        User user = new User();
        Database db = Mockito.mock(Database.class);
        Connection conn = Mockito.mock(Connection.class);
        Mockito.when(db.connect()).thenReturn(conn);
        Statement stmt = Mockito.mock(Statement.class);
        Mockito.when(conn.createStatement()).thenReturn(stmt);
        ResultSet rs = Mockito.mock(ResultSet.class);
        Mockito.when(stmt.executeQuery(anyString())).thenReturn(rs);
        Mockito.when(rs.getInt(anyString())).thenReturn(0);

        Assert.assertNotNull(user.register(db, "abc", "123"));
    }

    @Test (expected = ExDbUserNotFound.class)
    public void testLoginFailed1() throws SQLException, ExDbUserNotFound {
        User user = new User();
        Database db = Mockito.mock(Database.class);
        Connection conn = Mockito.mock(Connection.class);
        Mockito.when(db.connect()).thenReturn(conn);
        Statement stmt = Mockito.mock(Statement.class);
        Mockito.when(conn.createStatement()).thenReturn(stmt);
        ResultSet rs = Mockito.mock(ResultSet.class);
        Mockito.when(stmt.executeQuery(anyString())).thenReturn(rs);
        Mockito.when(rs.getInt(anyString())).thenReturn(0);

        Assert.assertNotNull(user.login(db, "abc", "123"));
    }

    @Test
    public void testLoginFailed2() throws SQLException, ExDbUserNotFound {
        User user = new User();
        Database db = Mockito.mock(Database.class);
        Connection conn = Mockito.mock(Connection.class);
        Mockito.when(db.connect()).thenReturn(conn);
        Statement stmt = Mockito.mock(Statement.class);
        Mockito.when(conn.createStatement()).thenReturn(stmt);
        ResultSet rs = Mockito.mock(ResultSet.class);
        Mockito.when(stmt.executeQuery(anyString())).thenThrow(new SQLException());

        Assert.assertNotNull(user.login(db, "abc", "123"));
    }

    @Test
    public void testLoginPassed() throws SQLException, ExDbUserNotFound {
        User user = new User();
        Database db = Mockito.mock(Database.class);
        Connection conn = Mockito.mock(Connection.class);
        Mockito.when(db.connect()).thenReturn(conn);
        Statement stmt = Mockito.mock(Statement.class);
        Mockito.when(conn.createStatement()).thenReturn(stmt);
        ResultSet rs = Mockito.mock(ResultSet.class);
        Mockito.when(stmt.executeQuery(anyString())).thenReturn(rs);
        Mockito.when(rs.getInt(anyString())).thenReturn(1);

        Assert.assertNotNull(user.login(db, "abc", "123"));
    }

    @Test (expected = ExDbUserNotFound.class)
    public void testChangePwdFailed1() throws SQLException, ExDbUserNotFound {
        User user = new User();
        Database db = Mockito.mock(Database.class);
        Connection conn = Mockito.mock(Connection.class);
        Mockito.when(db.connect()).thenReturn(conn);
        Statement stmt = Mockito.mock(Statement.class);
        Mockito.when(conn.createStatement()).thenReturn(stmt);
        ResultSet rs = Mockito.mock(ResultSet.class);
        Mockito.when(stmt.executeQuery(anyString())).thenReturn(rs);
        Mockito.when(rs.getInt(anyString())).thenReturn(0);

        Assert.assertNotNull(user.changePwd(db, "abc", "123"));
    }

    @Test
    public void testChangePwdFailed2() throws SQLException, ExDbUserNotFound {
        User user = new User();
        Database db = Mockito.mock(Database.class);
        Connection conn = Mockito.mock(Connection.class);
        Mockito.when(db.connect()).thenReturn(conn);
        Statement stmt = Mockito.mock(Statement.class);
        Mockito.when(conn.createStatement()).thenReturn(stmt);
        ResultSet rs = Mockito.mock(ResultSet.class);
        Mockito.when(stmt.executeQuery(anyString())).thenThrow(new SQLException());

        Assert.assertNotNull(user.changePwd(db, "abc", "123"));
    }

    @Test
    public void testChangePwdPassed() throws SQLException, ExDbUserNotFound {
        User user = new User();
        Database db = Mockito.mock(Database.class);
        Connection conn = Mockito.mock(Connection.class);
        Mockito.when(db.connect()).thenReturn(conn);
        Statement stmt = Mockito.mock(Statement.class);
        Mockito.when(conn.createStatement()).thenReturn(stmt);
        ResultSet rs = Mockito.mock(ResultSet.class);
        Mockito.when(stmt.executeQuery(anyString())).thenReturn(rs);
        Mockito.when(rs.getInt(anyString())).thenReturn(1);

        Assert.assertNotNull(user.changePwd(db, "abc", "123"));
    }
}
