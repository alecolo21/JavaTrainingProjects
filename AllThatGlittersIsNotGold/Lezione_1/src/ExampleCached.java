import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
public class ExampleCached{
    public static void main(String[] args) {
        ExecutorService service = Executors.newCachedThreadPool();
        for (int i =0; i<100; i++) {
            service.execute(new Task(i)); //sleep(1000); //attivando sleep lavora sempre il thread1
            }
        System.out.println("ThreadName:"+Thread.currentThread().getName());
    }
    private static void sleep(long timeMillis) {
        try {
            Thread.sleep(timeMillis);
        } catch(InterruptedException e) {}
    }
}