package ticketSystem.database;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileReader;
import java.io.IOException;

public class csvToSql extends SQLException {
    public void dataLoader(Database db) {
        try {
            readCSV(db);
        } catch (CsvValidationException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private static void readCSV(Database db) throws ParseException, SQLException, CsvValidationException, NumberFormatException, IOException {
        Connection conn = db.connect();
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        long start = sdf.parse("2022-11-01").getTime();
        Long now = Date.from(LocalDateTime.now().toInstant(ZoneOffset.UTC)).getTime() - 8 * 60 * 60 * 1000;
        long count = now - start;
        long day = count/1000/60/60/24;
        long daycount = day * 24 * 60 * 60;

        CSVReader reader = new CSVReaderBuilder(new FileReader("Source/TicketSystem/src/ticketSystem/database/flight.csv")).withSkipLines(1).build();
        String sqlInsert = "INSERT INTO flight" + 
            "(fid, departure, destination, take_off_time, landing_time, total_seats, available_seats, sell_status, price)" + 
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";
        pstmt = conn.prepareStatement(sqlInsert);
        
        String[] rowData = null;
        String t = "";
        int i = 0;
        while ((rowData = reader.readNext()) != null) {
            for (String data : rowData) {
                if (i == 5 || i == 6) {
                    long time = Long.parseLong(data);
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
        db.closeRs(rs);
        db.closeStmt(pstmt);
    
        return;
    }
}