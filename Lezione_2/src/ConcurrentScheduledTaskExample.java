import java.util.concurrent.*;
public class ConcurrentScheduledTaskExample {
    public static void main(String[] args) {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(3);
        CountDownClock clock1 = new CountDownClock("A");
        CountDownClock clock2 = new CountDownClock("B");
        CountDownClock clock3 = new CountDownClock("C");
        scheduler.scheduleWithFixedDelay(clock1, 3, 10, TimeUnit.SECONDS);
        scheduler.scheduleWithFixedDelay(clock2, 3, 15, TimeUnit.SECONDS);
        scheduler.scheduleWithFixedDelay(clock3, 3, 20, TimeUnit.SECONDS);
    }
}