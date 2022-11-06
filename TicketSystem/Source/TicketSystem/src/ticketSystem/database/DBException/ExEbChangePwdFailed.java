package ticketSystem.database.DBException;

public class ExEbChangePwdFailed extends Exception{
    public ExEbChangePwdFailed(){super("User changes password failed."); }
    public ExEbChangePwdFailed(String msg){super(msg);}
}

