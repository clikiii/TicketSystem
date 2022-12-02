package ticketSystem;

import static org.mockito.ArgumentMatchers.anyString;

import java.sql.SQLException;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import ticketSystem.database.dbException.ExDbUserExisted;

public class PeopleTest extends BaseTest {

    @Test(expected = ExDbUserExisted.class)
    public void testRegisterFailed1() throws SQLException, ExDbUserExisted {
        Mockito.when(rs.getInt("count(*)")).thenReturn(1);
        Assert.assertNull(People.register(db, "abc", "123"));
    }

    @Test
    public void testRegisterFailed2() throws ExDbUserExisted, SQLException {
        Mockito.when(stmt.executeQuery(anyString())).thenThrow(SQLException.class);
        Assert.assertNotNull(People.register(db, "abc", "123"));
    }

    @Test
    public void testRegisterUserPass() throws SQLException, ExDbUserExisted {
        Mockito.when(rs.getInt("count(*)")).thenReturn(0);
        Assert.assertNotNull(People.register(db, "abc", "123"));
    }

    @Test
    public void testCheckUsernameExistFailed() throws ExDbUserExisted, SQLException {
        Mockito.when(stmt.executeQuery(anyString())).thenThrow(SQLException.class);
        Assert.assertFalse(People.checkUsernameExist(db, "abc"));
    }

    @Test
    public void testCheckUsernameExistPass() throws SQLException, ExDbUserExisted {
        Mockito.when(rs.getInt("count(*)")).thenReturn(0);
        Assert.assertTrue(People.checkUsernameExist(db, "abc"));
    }
}
