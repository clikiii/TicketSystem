package ticketSystem;

import static org.mockito.ArgumentMatchers.any;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.junit.Test;
import org.mockito.Mockito;
import ticketSystem.database.CsvToSql;

public class CsvToSqlTest extends BaseTest {
    @Test
    public void testDataLoader() throws SQLException {
        PreparedStatement pstmt = Mockito.mock(PreparedStatement.class);
        Mockito.when(conn.prepareStatement(any())).thenReturn(pstmt);
    }

    @Test
    public void testDataLoaderCont() throws SQLException {
        TicketSystem ticketSystem = null;
        ticketSystem = TicketSystem.start();
        try {
            CsvToSql.dataLoader(ticketSystem.getDb());
        } finally {
            ticketSystem.terminate();
        }
    }
}
