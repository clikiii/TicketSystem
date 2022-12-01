package ticketSystem.database.dbException;

public class ExDbUserNotFound extends Exception{
    public ExDbUserNotFound(){super("User does not exist."); }
}
