package ticketSystem.database.DBException;

public class ExDbSeatInsufficient extends Exception{
    public ExDbSeatInsufficient(){super("Flight seats insufficient."); }
    public ExDbSeatInsufficient(String msg){super(msg);}
}
