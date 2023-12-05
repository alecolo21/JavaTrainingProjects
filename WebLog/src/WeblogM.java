import java.io.*;
import java.io.PrintWriter;
import java.security.Security;
import java.util.*;
import java.util.concurrent.*;

public class WeblogM {
    // Tempo di permanenza in cache.
    public static final int cachingTime = 600;
    public static final String stringCachingTime = String.format("%s", cachingTime);
    // Coda con priorita' in cui vengono memorizzate le righe tradotte.
    public static BlockingQueue<Element> outputQueue = new PriorityBlockingQueue<>();
    // Pool di thread.
    public static ExecutorService pool = Executors.newCachedThreadPool();
    // Tempo massimo di attesa per la terminazione del pool.
    public static final int maxDelay = 60000;

    public static void main(String[] args) {
        // Controllo dei parametri da riga di comando.
        if (args.length < 3) {
            System.err.println("Usage: <inputFile> <outputFile> <maxBatchSize>");
            System.exit(1);
        }
        int maxBatchSize = Integer.parseInt(args[2]), numLines = 0;
        // Imposto il tempo di permanenza in cache degli indirizzi tradotti.
        Security.setProperty("networkaddress.cache.ttl", stringCachingTime);
        // Apro i file di input e di output.
        long start = System.currentTimeMillis();
        try (
                BufferedReader in = new BufferedReader(new FileReader(args[0]));
                PrintWriter out = new PrintWriter(args[1]);
        ) {
            // Leggo il file di input riga per riga.
            // Creo gruppi di `batchSize` righe e per ciascun gruppo
            // attivo un Consumer che effettua la traduzione.
            String line;
            List<Element> batch = new ArrayList<>();
            while ((line = in.readLine()) != null) {
                // Se la lista ha raggiunto la capacita' massima,
                // la passo al Consumer e la (re-)inizializzo.
                if (batch.size() == maxBatchSize) {
                    pool.execute(new Consumer(batch, outputQueue));
                    batch = new ArrayList<>();
                }
                // Aggiungo la nuova riga alla lista.
                batch.add(new Element(numLines, line));
                numLines++;
            }
            // A questo punto, attendo la terminazione del pool.
            awaitPoolTermination();
            // Scrivo sul file di output le righe
            // Per ottenere le righe nell'ordine corretto, chiamo ripetutamente
            // il metodo take() che aspetta finché non c’è un elemento in testa
            // da prendere, a differenza del poll() che ha un timeout
            while (!outputQueue.isEmpty()) {
                Element element = outputQueue.take();
                out.print(element.line);
            }
        } catch (Exception e) {
            System.err.printf("Errore: %s\n", e.getMessage());
        }
        long end = System.currentTimeMillis();
        // Stampo il numero di righe lette e il tempo impiegato (in ms).
        System.out.printf(
                "N. of lines\t: %d\nElapsed time\t: %d ms\n", numLines, end - start
        );
    }

    /**
     * Avvia la procedura di terminazione del pool.
     */
    public static void awaitPoolTermination() {
        pool.shutdown();
        try {
            if (!pool.awaitTermination(maxDelay, TimeUnit.MILLISECONDS))
                pool.shutdownNow();
        } catch (InterruptedException e) {
            pool.shutdownNow();
        }
    }
}
