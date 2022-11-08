package ticketSystem;

import ticketSystem.database.dbException.ExDbUserExisted;

public class Main {
    public static void main(String[] args) {
        TicketSystem ticketSystem = TicketSystem.start();

        // simple example
        try {
            System.out.println(ticketSystem.register("AAA", "123").toString());
        } catch ( ExDbUserExisted e) {
            e.printStackTrace();
        }

        ticketSystem.terminate();
    }
}
