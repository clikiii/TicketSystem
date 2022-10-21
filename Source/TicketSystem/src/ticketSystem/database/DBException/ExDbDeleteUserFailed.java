package ticketSystem.database.DBException;

public class ExDbDeleteUserFailed extends Exception{
    public ExDbDeleteUserFailed(){super("User delete failed"); }
    public ExDbDeleteUserFailed(String msg){super(msg);}
}
