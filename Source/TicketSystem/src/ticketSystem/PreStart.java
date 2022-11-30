package ticketSystem;

public class PreStart {
    public static void main(String[] args) {
        TicketSystem ticketSystem = TicketSystem.start();

        ticketSystem.load();

        ticketSystem.terminate();

    }
}
