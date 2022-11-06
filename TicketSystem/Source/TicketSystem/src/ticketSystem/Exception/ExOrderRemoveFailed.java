package ticketSystem.Exception;

public class ExOrderRemoveFailed extends Exception{
    public ExOrderRemoveFailed(){super("Order removed failed."); }
    public ExOrderRemoveFailed(String msg){super(msg);}
}

