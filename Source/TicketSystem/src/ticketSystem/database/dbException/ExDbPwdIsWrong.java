package ticketSystem.database.dbException;

public class ExDbPwdIsWrong extends Exception{
    public ExDbPwdIsWrong(){super("User password is wrong."); }
    public ExDbPwdIsWrong(String msg){super(msg);}
}