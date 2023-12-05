import java.util.Scanner;
import java.util.InputMismatchException;

public class MyPrimeCounter {
    private static final int MAX = 10000000;

    public static void main(String[] args){
        int NumberOfThreads = 0;
        Scanner sc = new Scanner(System.in);
        while(NumberOfThreads>30 || NumberOfThreads<1){
            System.out.println("Inserire il numero di thread nell'intervallo 1-30");
            try{
                NumberOfThreads = sc.nextInt();
            }catch(InputMismatchException e){
                System.out.println("InputMismatchException");
            }
        }
        System.out.println("\nCreating " + NumberOfThreads + " prime-counting threads...");
        MyCountPrimesThread[] threadpool = new MyCountPrimesThread[NumberOfThreads];
        for(int i=0;i<NumberOfThreads;i++){
            threadpool[i] = new MyCountPrimesThread(i,MAX);
        }
        for(int i=0;i<NumberOfThreads;i++){
            threadpool[i].start();
        }
        System.out.println("Threads have been created and started.");
        sc.close();
    }
}
