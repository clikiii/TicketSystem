package ticketSystem.database.DBException;

public class ExDbOrderNotFound extends Exception{
    public ExDbOrderNotFound(){super("Order does not exist."); }
    public ExDbOrderNotFound(String msg){super(msg);}
}
