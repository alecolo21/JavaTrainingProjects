// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class ThreadRunnable {
    public static class MyRunnable implements Runnable {
        public void run() {
            System.out.println("MyRunnable running");
            System.out.println("MyRunnable finished");
        }
    }
    public static void main(String [] args) {
        Thread thread = new Thread (new MyRunnable());
        thread.start();
    }
}