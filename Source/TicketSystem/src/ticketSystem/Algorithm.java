package ticketSystem;

import java.util.ArrayList;
import java.util.Arrays;

public class Algorithm {

    private static final ArrayList<String> cities = new ArrayList<String>(Arrays.asList("Beijing", "Chongqing", "Chengdu", "Hangzhou", "Kunming", "Nanjing", "Shanghai", "Qingdao", "Wuhan", "Amoy"));
    
    private static ArrayList<Integer> flightsToEdges(ArrayList<Flight> flightsArr, boolean isFrom) {

        ArrayList<Integer> ret = new ArrayList<>();
        
        for (Flight f: flightsArr) {
            int tempCity = cities.indexOf(
                isFrom? f.getDestination():f.getDeparture()
            );

            ret.add(Integer.valueOf(tempCity));
        }

        return ret;
    }

    public static ArrayList<ArrayList<Flight> > computeDoubleRoute(ArrayList<Flight> fromA, ArrayList<Flight> toB) {
        ArrayList<Integer> fromARoute = flightsToEdges(fromA, true);
        ArrayList<Integer> toBRoute = flightsToEdges(toB, false);

        ArrayList<ArrayList<Flight> > ret = new ArrayList<>();

        int aIdx = 0;
        for (Integer a: fromARoute) {
            int aInB = toBRoute.indexOf(a);

            if (aInB >= 0) {
                for (int bIdx = aInB; bIdx<toBRoute.lastIndexOf(a); bIdx++){
                    if( fromA.get(aIdx).getLandingTime().before( toB.get(bIdx).getTakeOffTime() ) ) {
                        ret.add(
                            new ArrayList<>(Arrays.asList(
                                fromA.get(aIdx), toB.get(bIdx)
                            ))
                        );
                    }
                }
            }

            aIdx++;
        }

        return ret;
    }
}
