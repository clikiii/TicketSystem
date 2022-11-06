package ticketSystem.Exception;

public class ExOrderAddFailed extends Exception{
    public ExOrderAddFailed(){super("Order created failed."); }
    public ExOrderAddFailed(String msg){super(msg);}
}

