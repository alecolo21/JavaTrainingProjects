//GENERA ERRORI
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.*;

public class ClassMain {
    public static void main(String[] args) throws Exception {
        try (ServerSocket listener = new ServerSocket(10000)) {
            System.out.println("The capitalization server is running...");
            ExecutorService pool = Executors.newFixedThreadPool(20);
            while (true) {
                pool.execute(new Capitalizer(listener.accept()));
            }
        }
    }
    private static class Capitalizer implements Runnable {
        private final Socket socket;
        Capitalizer(Socket socket) {
            this.socket = socket; }
        public void run() {
            System.out.println("Connected: " + socket);
            try (Scanner in = new Scanner(socket.getInputStream());
                 PrintWriter out = new PrintWriter(socket.getOutputStream(),
                         true))
            { while (in.hasNextLine()) {
                out.println(in.nextLine().toUpperCase()); }
            } catch (Exception e) { System.out.println("Error:" + socket); }
        }
    }
}
