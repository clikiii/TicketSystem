package ticketSystem.database;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class CsvToSql {
    public static void dataLoader(Database db) {
        try {
            readCSV(db);
        } catch (CsvValidationException | NumberFormatException | ParseException | SQLException | IOException e) {
            e.printStackTrace();
        }
    }
    
    private static void readCSV(Database db) throws CsvValidationException, NumberFormatException, FileNotFoundException, ParseException, SQLException, IOException {
        Connection conn = db.connect();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        CSVReader reader = null;

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            long start = sdf.parse("2022-11-01").getTime();
            Long now = Date.from(LocalDateTime.now().toInstant(ZoneOffset.UTC)).getTime() - 8 * 60 * 60 * 1000;
            long count = now - start;
            long day = count/1000/60/60/24;
            long daycount = day * 24 * 60 * 60;

            reader = new CSVReaderBuilder(new FileReader(System.getProperty("user.dir") + File.separator + "flight.csv")).withSkipLines(1).build();
            String sqlInsert = "INSERT INTO flight" + 
                "(fid, departure, destination, take_off_time, landing_time, total_seats, available_seats, sell_status, price)" + 
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";
            pstmt = conn.prepareStatement(sqlInsert);
            
            String[] rowData = null;
            int position = 1;
            int index = 1;
            while ((rowData = reader.readNext()) != null) {
                for (String data : rowData) {
                    if (!(position == 2 || position == 4 || position == 8)){
                        if (position == 6 || position == 7) {
                            long time = Long.parseLong(data);
                            long new_time = time + daycount;
                            data = Long.toString(new_time);
                        }

                        pstmt.setString(index++, data);
                    }

                    position++;
                }

                position = 1;
                index = 1;
                pstmt.addBatch();
                pstmt.executeBatch();
            }
            
        } finally {
            reader.close();
            Database.closeRs(rs);
            Database.closeStmt(pstmt);
        }
    
        return;
    }
}