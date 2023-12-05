import java.util.*;
public class Task implements Runnable {
    private final int name;
    public Task(int name) {this.name=name;}
    public void run() {
        try{
            long duration=(long)(Math.random()*10);
            System.out.printf("%s: Task %s: Starting a task during %d seconds\n",
                    Thread.currentThread().getName(),name,duration);
            Thread.sleep(duration);
        }
        catch (InterruptedException e) {e.printStackTrace();}
        System.out.printf("%s: Task Finished %s \n",
                Thread.currentThread().getName(),name);}}