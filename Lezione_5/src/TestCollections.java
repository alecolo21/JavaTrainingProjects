import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
public class TestCollections {
    public static void main(String[] args) throws Exception {
        evaluatingThePerformance(new Hashtable<String,Integer>(),5);
        evaluatingThePerformance(Collections.synchronizedMap(new HashMap<String,Integer>()),5);
        evaluatingThePerformance (new ConcurrentHashMap<String,Integer>(),5);}
    public static void evaluatingThePerformance (Map<String,Integer> maptoEvalPerf, int size) throws Exception {
        long averageTime = 0;
        for(int j=0; j<5;j++) {
            ExecutorService executorService = Executors.newFixedThreadPool(size);
            long startTime = System.nanoTime();
            for(int i=0; i<5;i++) {
                executorService.execute(new Runnable ()
                {public void run()
                {performTest(maptoEvalPerf);}});
            }
            executorService.shutdown();
            executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.DAYS);
            long entTime = System.nanoTime();
            long totalTime = (entTime - startTime) / 1000000L;
            averageTime += totalTime;
            System.out.println("500K entried added/retrieved by each thread in "+ totalTime+ "  ms");}
        System.out.println("For " + maptoEvalPerf.getClass() + " the average time is " + averageTime / 5 + "ms\n");
    }
    public static void performTest(Map<String,Integer> maptoEvaluateThePerformance) {
        for(int i=0; i<500000; i++) {
            Integer randomNumber = (int) Math.ceil(Math.random() * 550000);
            Integer value = maptoEvaluateThePerformance.get(String.valueOf(randomNumber));
            // Put value
            maptoEvaluateThePerformance.put(String.valueOf(randomNumber),randomNumber);
        }
    }
}

