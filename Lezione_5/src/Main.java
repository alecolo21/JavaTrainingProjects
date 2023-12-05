public class Main {
    public static void main(String [] args)
    {CHashMap ex= new CHashMap();
        Thread t1 = new Thread (new Runnable()
        {public void run()
        {System.out.println
                (ex.getOrCreate("5"));}});
        t1.start();
        Thread t2 = new Thread (new Runnable()
        {public void run()
        {System.out.println
                (ex.getOrCreate("5"));}});
        t2.start();
    }}