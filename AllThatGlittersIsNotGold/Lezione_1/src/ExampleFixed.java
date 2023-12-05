import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
public class ExampleFixed{
    public static void main(String[] args) {
        // create the pool
        ExecutorService service = Executors.newFixedThreadPool(10);
        //submit the task for execution
        for (int i =0; i<100; i++) {
            service.execute(new Task(i));
        }
        System.out.println("Thread Name:"+
                Thread.currentThread().getName());
    }
}
