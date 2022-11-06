package ticketSystem.database.dbException;

public class ExDbUserExisted extends Exception{
    public ExDbUserExisted(){super("User already existed."); }
    public ExDbUserExisted(String msg){super(msg);}
}

