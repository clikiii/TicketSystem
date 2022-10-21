package ticketSystem.database.DBException;

public class ExDbUserRegisterFailed extends Exception{
    public ExDbUserRegisterFailed(){super("User register failed."); }
    public ExDbUserRegisterFailed(String msg){super(msg);}
}

