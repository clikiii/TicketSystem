package ticketSystem;

import java.util.ArrayList;
import java.util.List;

import ticketSystem.database.Database;
import ticketSystem.database.DBException.ExDbUserExisted;


public class Main {
    public static void main(String[] args) {
        // System.out.println("Hello world!");
        // Database db = new Database();
        // try {
        //     People currentUser = new User().register(db, "EEE", "789");
        // } catch (ExDbUserExisted e) {
        //     // TODO Auto-generated catch block
        //     e.printStackTrace();
        // }
        // db.closeConn();

        List<Integer> a = new ArrayList<Integer>();
        a.add(1);
        // a.add(2);
        a.add(3);
        a.add(4);

        List<Integer> b = new ArrayList<Integer>();
        b.add(1);
        b.add(2);
        b.add(3);
        System.out.println(a.retainAll(b));
        System.out.println(a.toString());
    }
}
