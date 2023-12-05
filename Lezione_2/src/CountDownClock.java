public class CountDownClock implements Runnable {
    private final String clockName;
    public CountDownClock(String clockName) {
        this.clockName = clockName;
    }
    public void run() {
        String threadName = Thread.currentThread().getName();
        for (int i = 5; i >= 0; i--) {
            System.out.printf("%s -> %s: %d\n", threadName, clockName, i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            } } } }