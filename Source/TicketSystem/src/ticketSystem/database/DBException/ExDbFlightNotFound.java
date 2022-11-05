package ticketSystem.database.DBException;

public class ExDbFlightNotFound extends Exception{
    public ExDbFlightNotFound(){super("Flight does not exist."); }
    public ExDbFlightNotFound(String msg){super(msg);}
}
