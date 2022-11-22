package ticketSystem;


public class Main {
    public static void main(String[] args) {
        TicketSystem ticketSystem = TicketSystem.start();

        // simple example
        System.out.println(ticketSystem.login("AAA", "123").toString());

        // ticketSystem.load();

        ticketSystem.terminate();
    }
}
