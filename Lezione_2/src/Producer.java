import java.util.concurrent.BlockingQueue;
public class Producer implements Runnable {
    BlockingQueue <String> blockingQueue = null;
    public Producer (BlockingQueue<String> queue) {
        this.blockingQueue = queue; }
    public void run() {
        while (true) {
            long timeMillis = System.currentTimeMillis();
            try {
                this.blockingQueue.put("" + timeMillis);
            } catch (InterruptedException e) {
                System.out.println("Producer was interrupted"); }
            sleep(1000); }}
    private static void sleep(long timeMillis) {
        try { Thread.sleep(timeMillis);
        } catch(InterruptedException e) {e.printStackTrace();}
    }
}