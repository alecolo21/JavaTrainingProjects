import java.util.concurrent.*;
/**
 * Versione che prevede 1) la chiusura degli sportelli in caso di inattivita',
 * attesa attiva degli utenti che devono entrare nella seconda stanza
 */
public class UfficioChiusura {
    // Numero degli sportelli dell'ufficio.
    public static final int numSportelli = 4;
    // Dimensione della coda davanti agli sportelli.
    public static final int dimCoda = 10;
    // Numero di clienti da fare entrare nell'ufficio.
    public static final int numClienti = 500;
    // Tempo di attesa per ritentare di entrare nella seconda stanza se la coda sportelli e' piena.
    public static final long queueDelay = 500;
    // Tempo di attesa per la terminazione del pool.
    public static final long terminationDelay = 5000;
    // Tempo di inattivita' prima della chiusura di uno sportello.
    public static final long closingDelay = 60000;
    public static void main(String[] args) {
        // Contatore delle persone servite.
        int count = 0;
        System.out.println("Ufficio aperto!");
        // Creo la coda (senza limiti di dimensione) per la prima sala.
        BlockingQueue<Runnable> coda = new LinkedBlockingQueue<Runnable>();
        // creo il pool di thread personalizzato usando AbortPolicy come politica di rifiuto
        // (ovvero viene sollevata una RejectedExecutionException quando la coda del pool e' piena).
        ThreadPoolExecutor pool = new ThreadPoolExecutor(
                numSportelli,
                numSportelli,
                closingDelay,
                TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<Runnable>(dimCoda),
                new ThreadPoolExecutor.AbortPolicy()
        );
        // Imposto la possibilita' di chiusura degli sportelli.
        pool.allowCoreThreadTimeOut(true);

        // Metto in coda i clienti nella prima sala.
        for (int i = 0; i < numClienti; i++) coda.add(new Persona(i));

        while (!coda.isEmpty()) {
            Persona p = (Persona) coda.peek(); //legge da coda
            try { pool.execute(p);
                coda.poll();  //rimuove la persona dalla coda e la aggiunge al numero delle persone servite
                count++; }
            catch (RejectedExecutionException e) {
                // se sono qui, significa che la coda davanti agli sportelli
                // (ovvero la coda del pool) è piena.
                // aspetto un certo intervallo di tempo affinche' si svuoti e poi
                // ritento
                System.out.printf("Coda sportelli piena. " +
                        "Il cliente con id=%d resta in attesa.\n", p.id);
                try {
                    Thread.sleep(queueDelay);}
                catch (InterruptedException x)
                { System.err.println("Interruzione durante sleep."); } } }
        // a questo punto si chiude l’ufficio
        // 1) si attende un certo intervallo di
        // tempo affinche' tutti i thread possano terminare.
        // 2) passato l'intervallo, l'esecuzione del pool
        // viene interrotta immediatamente.
        pool.shutdown();
        try {
            if (!pool.awaitTermination(terminationDelay, TimeUnit.MILLISECONDS))
                pool.shutdownNow();
        }
        catch (InterruptedException e) {pool.shutdownNow();}
        // Stampa di un messaggio di chiusura.
        System.out.printf("Ufficio chiuso. Persone servite: %d\n", count);
    }
}
