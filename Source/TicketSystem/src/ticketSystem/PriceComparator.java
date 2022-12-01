package ticketSystem;

import java.util.ArrayList;
import java.util.Comparator;

public class PriceComparator implements Comparator<ArrayList<Flight> >{

    
    /** 
     * Compare the two given flight arraylists by total prices.
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

        int p1 = 0;
        int p2 = 0;

        for (Flight f: o1){ p1 += f.getPrice();}
        for (Flight f: o2){ p2 += f.getPrice();}

        return p1-p2;
    }
}
