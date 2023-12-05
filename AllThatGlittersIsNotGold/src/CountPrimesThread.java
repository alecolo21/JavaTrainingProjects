public class CountPrimesThread extends Thread {
    /* Ogni thread è identificato da un identificatore
    MAX è la quantità di numeri da controllare */
    public int id;
    public int MAX;
    public CountPrimesThread(int id, int MAX){
        this.id = id;
        this.MAX = MAX;
    }

    public static int countPrimes(int min, int max) {
        int count = 0;
        for (int i = min; i <= max; i++)
            if (isPrime(i))
                count++;
        return count;
    }
    /* test di primalità:
 * dato un numero di input n, si deve verificare se esiste un intero m
 * compreso tra 2 e n − 1 tale da dividere n.
 * se n è divisibile per almeno un m allora n è composto, altrimenti è
 * primo. Il limite n-1 può essere abbassato alla radice quadrata di n
 * in quanto se tutti i fattori fossero maggiori di questo valore,
 * il loro prodotto sarebbe necessariamente maggiore di n, che è
 assurdo */
    private static boolean isPrime(int x) {
        assert x > 1;
        int top = (int) Math.sqrt(x);
        for (int i = 2; i <= top; i++)
            if (x % i == 0)
                return false;
        return true;
    }
    public void run() {
        long startTime = System.currentTimeMillis();
        int count = countPrimes (2,MAX);
        long elapsedTime = System.currentTimeMillis() - startTime;
        System.out.println("Thread " + id + " counted " + count + " primes in " + (elapsedTime/1000.0) + " seconds.");
    }
}