package ticketSystem;

import java.util.ArrayList;
import org.junit.Assert;
import org.junit.Test;

public class AlgorithmTest {
    @Test
    public void testCompute() {
        ArrayList<Flight> from = new ArrayList<Flight>();
        from.add(new Flight(1, "f1", "beijing", "chongqing", "123", "234", 100, 100, "sell", 100));
        ArrayList<Flight> to = new ArrayList<Flight>();
        to.add(new Flight(1, "f1", "beijing", "chongqing", "345", "456", 100, 100, "sell", 100));
        to.add(new Flight(1, "f1", "beijing", "Hangzhou", "345", "456", 100, 100, "sell", 100));
        ArrayList<ArrayList<Flight>> res = Algorithm.computeDoubleRoute(from, to);

        Assert.assertEquals(1, res.size());
        Assert.assertEquals(2, res.get(0).size());
    }
}
