import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ArrayBlockingQueue;
public class ProducerConsumerExample {
    public static void main(String[] args) {
        BlockingQueue<String> blockingQueue =
                new ArrayBlockingQueue<String>(3);
        Producer producer = new Producer(blockingQueue);
        Consumer consumer = new Consumer(blockingQueue);
        Thread producerThread = new Thread (producer);
        Thread consumerThread = new Thread(consumer);
        producerThread.start();
        consumerThread.start(); } }