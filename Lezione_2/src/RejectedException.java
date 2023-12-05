import java.util.concurrent.*;
public class RejectedException {
    public static void main (String[] args )
    {ExecutorService service
            = new ThreadPoolExecutor(10, 12, 120, TimeUnit.SECONDS,
            new ArrayBlockingQueue<Runnable>(3));
        for (int i=0; i<20; i++)
            try {
                service.execute(new Task(i));
            } catch (RejectedExecutionException e)
            {System.out.println("task rejected"+e.getMessage());}
    }}