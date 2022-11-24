package ticketSystem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import ticketSystem.database.CsvToSql;
import ticketSystem.database.Database;
import ticketSystem.database.dbException.ExDbPwdIsWrong;
import ticketSystem.database.dbException.ExDbUserExisted;
import ticketSystem.database.dbException.ExDbUserNotFound;

public class TicketSystem {
    private static final ArrayList<String> cities = new ArrayList<String>(Arrays.asList("Beijing", "Chongqing", "Chengdu", "Hangzhou", "Kunming", "Nanjing", "Shanghai", "Qingdao", "Wuhan", "Amoy", "Taipei", "Hong Kong"));
    private Database db;

    private static TicketSystem instance = new TicketSystem();
    private TicketSystem(){
        this.db = new Database();
    }
    public static TicketSystem start(){
        System.out.println("Welcome to the Flight Ticket System!");
        return instance;
    }

    public void load() {
        CsvToSql.dataLoader(this.db);
    }


    public boolean checkUsernameExist(String username){
        return People.checkUsernameExist(this.db, username);
    }

    public User register(String username, String password){
        try {
            return People.register(this.db, username, password);
        } catch (ExDbUserExisted e) {
            System.out.println("Error: Username existed!");
            return null;
        }
    }

    public People login(String username, String password){
        try {
            if (username.equals("admin")) return Admin.login(this.db, username, password);

            return User.login(this.db, username, password);
        } catch (ExDbUserNotFound e) {
            System.out.println("Error: User not found!");
            return null;
        } catch (ExDbPwdIsWrong e) {
            System.out.println("Error: User password is wrong!");
            return new User(db, "password wrong", "password wrong");
        }
    }

    public ArrayList<ArrayList<Flight>> searchRoute(
        String departure, 
        String destination, 
        Date startDate,
        String searchType, 
        boolean onlySingle
    ){
        Search search = new Search(departure, destination, startDate, this.db);
        return search.searchRoute(searchType, onlySingle);
    }

    public boolean checkCity (String city) {
        return cities.contains(city);
    }

    public void terminate(){
        this.db.closeConn();
        System.out.println("System terminated.");
    }
}
