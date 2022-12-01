package ticketSystem;

import java.util.ArrayList;
import java.util.Comparator;

public class ComparatorFactory {
    
    /** 
     * Get comparator by context.
     * @param searchType
     * @return Comparator<ArrayList<Flight>>
     */
    public Comparator<ArrayList<Flight> > getComparator(String searchType) {
         // take off time: from early ones to late ones
        if (searchType.equalsIgnoreCase("TAKEOFFTIME")){
            return new TakeOffComparator();
        }

        // price: from cheap ones to expensive ones
        if (searchType.equalsIgnoreCase("PRICE")){
            return new PriceComparator();
        }

        return null;
    }
}
