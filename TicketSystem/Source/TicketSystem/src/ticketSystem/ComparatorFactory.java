package ticketSystem;

import java.util.ArrayList;
import java.util.Comparator;

public class ComparatorFactory {
    public Comparator<ArrayList<Flight> > getComparator(String searchType) {
         // take off time: from early ones to late ones
        if (searchType.equalsIgnoreCase("TAKEOFFTIME")){
            return new TakeOffComparator();
        }

        // price: from cheap ones to expensive ones
        if (searchType.equalsIgnoreCase("PRICE")){
            return new PriceComparator();
        }

        // // duration: from short ones to long ones
        // if (searchType.equalsIgnoreCase("DURATION")){
        //     return null;
        // }

        return null;
    }
}
