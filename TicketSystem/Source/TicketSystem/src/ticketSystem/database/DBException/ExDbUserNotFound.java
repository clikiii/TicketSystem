package ticketSystem.database.DBException;

public class ExDbUserNotFound extends Exception{
    public ExDbUserNotFound(){super("User does not exist."); }
    public ExDbUserNotFound(String msg){super(msg);}
}
