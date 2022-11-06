import ticketSystem.database.Database;

import java.sql.*;
import com.opencsv.CSVReader;
import java.io.FileReader;

public class csvToSql {
    public void dataLoader(Database db)
    {
        readCSV(db);
    }
    
    private static void readCSV(Database db) {
        Connection conn = db.connect();
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        long start = sdf.parse("2022-11-01").getTime();
        long now = new Date().getCreateTime();
        long count = now - start;
        long day = count/1000/60/60/24;
        long daycount = day * 24 * 60 * 60 * 1000;

        try (CSVReader reader = new CSVReader(new FileReader("flight.csv"), ',')) {
            String sqlInsert = "INSERT INTO flight" + 
                "(fid, departure, destination, take_off_time, landing_time, total_seats, available_seats, sell_status, price)" + 
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";
            pstmt = conn.prepareStatement(sqlInsert);
            String[] rowData = null;
            int i = 0;
            while ((rowData = reader.readNext()) != null) {
                for (String data : rowData) {
                    if (i == 5 || i == 6) {
                        long time = Long.parseInt(data) * 1000;
                        long new_time = time + daycount;
                        data = Long.toString(new_time);
                    }

                    pstmt.setString(i++, data);
                    pstmt.addBatch();
                    pstmt.executeBatch();
                }
                i = 0;
            }
            reader.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            db.closeRs(rs);
            db.closeStmt(pstmt);
        }
    
        return;
    }
    
    private static void loadCSV(Database db) {
        Connection conn = db.connect();
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.createStatement();
            String sqlLoad = "LOAD DATA LOCAL INFILE '" + "flight.csv" + "'" + 
                "INTO TABLE flight" + 
                "FIELDS TERMINATED BY ','" + 
                "LINES TERMINATED BY '\n'" + 
                "IGNORE 1 ROWS" + 
                "(fid, departure, destination, take_off_time, landing_time, total_seats, available_seats, sell_status, price);";
            rs = stmt.executeQuery(String.format(sqlLoad));
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            db.closeRs(rs);
            db.closeStmt(stmt);
        }
    
        return;
    }
}