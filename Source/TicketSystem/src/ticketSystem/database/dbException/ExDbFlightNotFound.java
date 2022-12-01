package ticketSystem.database.dbException;

public class ExDbFlightNotFound extends Exception{
    public ExDbFlightNotFound(){super("Flight does not exist."); }
}
