public class MyCountPrimesThread  extends Thread {
    public final int id;
    public final int MAX;

    public MyCountPrimesThread(int id, int MAX) {
        this.id = id;
        this.MAX = MAX;
    }

    public int CountPrime(int min, int max) {
        int count = 0;
        for(int i=min; i<=max;i++){
            if(isPrime(i)){
                count++;
            }
        }
        return count;
    }
    public boolean isPrime(int x) {
        assert x > 1;
        int top = (int) Math.sqrt(x);
        for (int i = 2; i <= top; i++) {
            // condition for nonprime number
            if (x % i == 0){return false;}
        }
        return true;
    }
    public void run() {
        long startTime = System.currentTimeMillis();
        int count = CountPrime(2, MAX);
        long elapsedTime = System.currentTimeMillis() - startTime;
        System.out.println("Thread " + id + " counted " + count + " primes in " + (elapsedTime / 1000.0) + " seconds.");
    }
}