package ticketSystem.database.DBException;

public class ExDbFlightrNotFound extends Exception{
    public ExDbFlightrNotFound(){super("Flight does not exist."); }
    public ExDbFlightrNotFound(String msg){super(msg);}
}
