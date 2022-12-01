package ticketSystem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import org.junit.Assert;
import org.junit.Test;

public class ComparatorFactoryTest {
    @Test
    public void testGetComparator() {
        Assert.assertTrue(new ComparatorFactory().getComparator("TAKEOFFTIME") instanceof TakeOffComparator);
        Assert.assertTrue(new ComparatorFactory().getComparator("PRICE") instanceof PriceComparator);
        Assert.assertNull(new ComparatorFactory().getComparator("asdasd"));
    }

    @Test
    public void testPriceComparator() {
        ArrayList<Flight> l1 = new ArrayList<Flight>();
        ArrayList<Flight> l2 = new ArrayList<Flight>();
        l1.add(new Flight(1, "f1", "beijing", "chongqing", new Date(345), new Date(456), 100, 100, "sell", 120));
        l2.add(new Flight(1, "f1", "beijing", "Hangzhou", new Date(345), new Date(456), 100, 100, "sell", 100));
        ArrayList<ArrayList<Flight>> lists = new ArrayList();
        lists.add(l1);
        lists.add(l2);

        Collections.sort(lists, new PriceComparator());
        Assert.assertEquals(l2, lists.get(0));
    }

    @Test
    public void testTakeoffTimeComparator() {
        ArrayList<Flight> l1 = new ArrayList<Flight>();
        ArrayList<Flight> l2 = new ArrayList<Flight>();
        l1.add(new Flight(1, "f1", "beijing", "chongqing", new Date(123), new Date(456), 100, 100, "sell", 120));
        l2.add(new Flight(1, "f1", "beijing", "Hangzhou", new Date(345), new Date(456), 100, 100, "sell", 100));
        ArrayList<ArrayList<Flight>> lists = new ArrayList();
        lists.add(l1);
        lists.add(l2);

        Collections.sort(lists, new TakeOffComparator());
        Assert.assertEquals(l1, lists.get(0));
    }
}
