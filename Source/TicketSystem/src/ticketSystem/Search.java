package ticketSystem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

import ticketSystem.database.Database;

public class Search {

    private String departure;
    private String destination;
    private Date startDate;
    private Database db;

    public Search(String departure, String destination, Date startDate, Database db){
        this.departure = departure;
        this.destination = destination;
        this.startDate = startDate;
        this.db = db;
    }

    private ArrayList<ArrayList<Flight>> singleTo2DArray(ArrayList<Flight> singleRoute) {
        ArrayList<ArrayList<Flight>> ret = new ArrayList<>();

        for (Flight f: singleRoute) {
            ret.add(
                new ArrayList<>(Arrays.asList(f))
            );
        }

        return ret;
    }

    // TODO: discuss with the frontend
    public ArrayList<ArrayList<Flight>> searchRoute(String searchType, boolean onlySingle) {
        ComparatorFactory comparatorFactory = new ComparatorFactory();
        Comparator<ArrayList<Flight>> comparator = comparatorFactory.getComparator(searchType);

        ArrayList<Flight> tempSingle  = Flight.queryByDepartAndDest(this.db, this.departure, this.destination, this.startDate);
        ArrayList<ArrayList<Flight>> singleRoute = singleTo2DArray(tempSingle);

        if (onlySingle) {
            Collections.sort(singleRoute, comparator);
            return singleRoute;
        }

        ArrayList<Flight> fromA = Flight.queryByDepart(this.db, this.departure, this.startDate);
        ArrayList<Flight> toB = Flight.queryByDest(this.db, this.destination, this.startDate);

        ArrayList<ArrayList<Flight>> doubleRoute = Algorithm.computeDoubleRoute(fromA, toB);

        ArrayList<ArrayList<Flight>> ret = new ArrayList<>();
        ret.addAll(singleRoute.subList(0, 5>singleRoute.size()? singleRoute.size():5));
        ret.addAll(doubleRoute.subList(0, 5>doubleRoute.size()? doubleRoute.size():5));
        Collections.sort(ret, comparator);

        return ret;
    }
}
