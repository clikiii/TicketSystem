package ticketSystem;

import java.util.ArrayList;
import java.util.Comparator;

public class TakeOffComparator implements Comparator<ArrayList<Flight> >{

    @Override
    public int compare(ArrayList<Flight> o1, ArrayList<Flight> o2) {
        /**
         *  o1<o2: -1
         *  o1==o2: 0
         *  o1>o2: 1
         */
        return ((o1.get(0)).getTakeOffTime()).compareTo((o2.get(0)).getTakeOffTime());
    }
}
