package ticketSystem.database.dbException;

public class ExDbSeatInsufficient extends Exception{
    public ExDbSeatInsufficient(){super("Flight seats insufficient."); }
}
