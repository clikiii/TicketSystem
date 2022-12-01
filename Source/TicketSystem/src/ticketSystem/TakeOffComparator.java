package ticketSystem;

import java.util.ArrayList;
import java.util.Comparator;

public class TakeOffComparator implements Comparator<ArrayList<Flight> >{

    
    /** 
     * Compare the two given flight arraylists by the take off time.
     * @param o1
     * @param o2
     * @return int
     */
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
