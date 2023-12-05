import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
public class CPUIntensiveTask implements Runnable {
    public void run() {
        // eseguo la PoW
    }
    public static class ThreadDimensioning {
        public static void main(String[] args) {
            // get count of available cores
            int coreCount = Runtime.getRuntime().availableProcessors();
            System.out.println(coreCount);
            ExecutorService service = Executors.newFixedThreadPool(coreCount);
            // submit the tasks for execution
            for (int i = 0; i < 100; i++) {
                service.execute(new CPUIntensiveTask());
            }
        }
    }
}