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

    
    /** 
     * Get available cities.
     * @return ArrayList<String>
     */
    public static ArrayList<String> getCities() {
        return cities;
    }

    private static TicketSystem instance = new TicketSystem();
    private TicketSystem(){
        this.db = new Database();
    }
    public TicketSystem(Database db) {
        this.db = db;
        instance = this;
    }

    
    /** 
     * Start the backend service.
     * @return TicketSystem
     */
    public static TicketSystem start(){
        System.out.println("Welcome to the Flight Ticket System!");
        return instance;
    }

    /** 
     * Load the data to the database.
     */
    public void load() {
        System.out.println("Loading data to database...");
        if ((Flight.queryAllFlight(this.db)).size() == 0){
            CsvToSql.dataLoader(this.db);
            System.out.println("Data loaded successful!");
            return;
        }
        System.out.println("Data already loaded!");
    }


    
    /** 
     * Check if a given username is already used.
     * @param username
     * @return boolean
     */
    public boolean checkUsernameExist(String username){
        return People.checkUsernameExist(this.db, username);
    }

    
    /** 
     * Register a new user.
     * @param username
     * @param password
     * @return User
     */
    public User register(String username, String password){
        try {
            return People.register(this.db, username, password);
        } catch (ExDbUserExisted e) {
            System.out.println("Error: Username existed!");
            return null;
        }
    }

    
    /** 
     * Admin or User login.
     * @param username
     * @param password
     * @return People
     */
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

    
    /** 
     * Search the flight routes.
     * @param departure
     * @param destination
     * @param startDate
     * @param searchType
     * @param onlySingle
     * @return ArrayList<ArrayList<Flight>>
     */
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

    
    /** 
     * Check if a given city is available in the system.
     * @param city
     * @return boolean
     */
    public boolean checkCity (String city) {
        return cities.contains(city);
    }

    /**
     * Terminate the backend.
     */
    public void terminate(){
        this.db.closeConn();
        System.out.println("System terminated.");
    }
}
