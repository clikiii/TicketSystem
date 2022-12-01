package ticketSystem;

public class PreStart {
    
    /** 
     * Load the data before starting.
     * @param args
     */
    public static void main(String[] args) {
        TicketSystem ticketSystem = TicketSystem.start();

        ticketSystem.load();

        ticketSystem.terminate();

    }
}
