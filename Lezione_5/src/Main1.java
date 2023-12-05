import java.util.*;
import java.util.concurrent.*;
public class Main1 {
    static Map<String, Object> theMap = new ConcurrentHashMap<>();
    public static void main(String [] args)
    { Thread t1 = new Thread (
            new Runnable() {public void run()
            {Object obj1 = new Object();
                System.out.println(theMap.putIfAbsent("5",obj1));}});
        t1.start();
        Thread t2 = new Thread (new Runnable() {public void run()
        {Object obj2 = new Object();
            System.out.println(theMap.putIfAbsent("5",obj2));}});
        t2.start();
    }
}