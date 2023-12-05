import java.util.concurrent.*;
import java.awt.*;
public class BeepClockS implements Runnable {
    public void run() {
        Toolkit.getDefaultToolkit().beep();
    }
    public static void main(String[] args) {
        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
        Runnable task = new BeepClockS();
        int initialDelay = 4;
        int periodicDelay = 1;
        scheduler.scheduleAtFixedRate(task, initialDelay, periodicDelay,TimeUnit.SECONDS);}}