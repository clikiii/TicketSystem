package ticketSystem;

public class Main {
    public static void main(String[] args) {
        TicketSystem ticketSystem = TicketSystem.start();
        ticketSystem.terminate();
    }
}
