import java.util.Scanner;
import java.util.InputMismatchException;
/* un programma che esegue diversi thread, ognuno dei quali esegue la
 * stessa computazione . */
public class PrimeCounter {
    private final static int MAX = 10000000;
    /* ogni thread conta i numeri primi compresi tra 2 e MAX.
     * il numero di threads, tra 2 e 30, deve essere dato in input dal
     * programmatore */
    public static void main(String[] args) {
        int numberOfThreads=0;
        Scanner sc = new Scanner(System.in);
        System.out.print("How many threads do you want to use (from 1 to 30)? ");
        try{
            numberOfThreads = sc.nextInt();
        }catch(InputMismatchException  e){
            System.out.println("InputMismatchException");
        }

        while (numberOfThreads < 1 || numberOfThreads > 30) {
            System.out.println("Please enter a number between 1 and 30 !");
            try{
                numberOfThreads = sc.nextInt();
            }catch(InputMismatchException  e){
                System.out.println("InputMismatchException");
            }
        }
        System.out.println("\nCreating " + numberOfThreads + " prime-counting threads...");
        CountPrimesThread[] worker = new CountPrimesThread[numberOfThreads];
        for (int i = 0; i < numberOfThreads; i++) {
            worker[i] = new CountPrimesThread(i, MAX);
        }
        for (int i = 0; i < numberOfThreads; i++) {
            worker[i].start();
        }
        System.out.println("Threads have been created and started.");
        sc.close();
    }
}