package ticketSystem;

import java.util.ArrayList;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        TicketSystem ticketSystem = TicketSystem.start();

        // simple example
        // System.out.println(ticketSystem.login("AAA", "123").toString());

        // ticketSystem.load();

        // String str = "1669999500000";
        // Date date = new Date(Long.parseLong(str));
        Date date = new Date();
        System.out.println(date);

        ArrayList<ArrayList<Flight>> ffs = ticketSystem.searchRoute(
            "Beijing", "Shanghai", date, "PRICE", false
        );

        System.out.println(ffs.size());

        // for (ArrayList<Flight> ff: ffs ) {
        //     // for (Flight f: ff){
        //     //     System.out.println(f.getDeparture());
        //     // }

        //     System.out.println(ff.size());
        // }

        ticketSystem.terminate();


        // String str = "1669999500";
        // System.out.println(Long.parseLong(str)*1000);
        // Date date = new Date(Long.parseLong(str)*1000);
        // long t = date.getTime();
        // String ts = String.valueOf((int)(t/1000));
        // System.out.println(ts);
    }
}
