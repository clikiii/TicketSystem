package ticketSystem;

import java.util.ArrayList;
import java.util.Date;

import ticketSystem.database.Database;
import ticketSystem.database.DBException.ExDbUserExisted;
import ticketSystem.database.DBException.ExDbUserNotFound;

public class TicketSystem {
    private Database db;

    private static TicketSystem instance = new TicketSystem();
    private TicketSystem(){
        this.db = new Database();
    }
    public static TicketSystem start(){
        System.out.println("Welcome to the Flight Ticket System!");
        return instance;
    }


    public User register(String username, String password) throws ExDbUserExisted{
        return People.register(this.db, username, password);
    }

    public People login(String username, String password) throws ExDbUserNotFound{
        if (username == "admin") return Admin.login(this.db, username, password);

        return User.login(this.db, username, password);
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


    public void terminate(){
        System.out.println("System terminated.");
        this.db.closeConn();
    }
}
