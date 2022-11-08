package ticketSystem;

import static org.mockito.ArgumentMatchers.anyString;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.junit.Before;
import org.mockito.Mockito;
import ticketSystem.database.Database;

public class BaseTest {
    protected Database db;
    protected Connection conn;
    protected Statement stmt;
    protected ResultSet rs;

    @Before
    public void init() throws SQLException {
        db = Mockito.mock(Database.class);
        conn = Mockito.mock(Connection.class);
        Mockito.when(db.connect()).thenReturn(conn);
        stmt = Mockito.mock(Statement.class);
        Mockito.when(conn.createStatement()).thenReturn(stmt);
        rs = Mockito.mock(ResultSet.class);
        Mockito.when(stmt.executeQuery(anyString())).thenReturn(rs);
    }
}
