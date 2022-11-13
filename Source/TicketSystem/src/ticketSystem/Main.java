package ticketSystem;

import ticketSystem.database.dbException.ExDbUserExisted;

public class Main {
    public static void main(String[] args) {
        TicketSystem ticketSystem = TicketSystem.start();

        // simple example
        // try {
            
        // } catch ( ExDbUserExisted e) {
        //     e.printStackTrace();
        // }

        System.out.println(ticketSystem.checkCity("BeijingS"));

        ticketSystem.terminate();
    }
}
