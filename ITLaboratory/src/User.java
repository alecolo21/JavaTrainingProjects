import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
/**
 * Questa classe rappresenta il generico Utente del laboratorio.
 * a seconda della Categoria, puo' trattarsi di uno Studente,
 * di un Tesista oppure di un Professore.
 * @author Matteo Loporchio
 */
public class User implements Runnable {
    public Categoria categoria; // Tipologia di utente.
    public int id; // Identificativo numerico dell'utente.
    public int numAccessi; // Numero di accessi previsti per l'utente.
    public long workDelay; // Tempo in cui l'utente utilizza il laboratorio
    public long breakDelay; // Tempo che intercorre fra un accesso e l'altro.
    public int maxAccessi = 5; // Massimo numero di accessi,
    public long maxWork = 5000; // Massimo tempo di lavoro
    public long maxBreak = 2000; // Massimo tempo di pausa
    private final Laboratorio lab; // Riferimento al laboratorio.
    /**
     * Costruttore della classe Utente.
     * @param categoria la categoria dell'utente
     * @param id identificativo numerico dell'utente
     * @param lab riferimento al laboratorio
     */
    public User(Categoria categoria, int id, Laboratorio lab) {
        this.categoria = categoria;
        this.id = id;
        this.lab = lab;
        numAccessi = ThreadLocalRandom.current().nextInt(1, maxAccessi+1);
        workDelay = ThreadLocalRandom.current().nextLong(maxWork+1);
        breakDelay = ThreadLocalRandom.current().nextLong(maxBreak+1);
    }
    /**
     * Tutti gli utenti richiedono l'accesso al laboratorio, lo utilizzano per un certo
     intervallo di tempo e poi escono per fare una pausa. Tutto viene ripetuto per
     numAccessi` volte.
     */
    public void run() {
        try {
            for (int i = 0; i < numAccessi; i++) {
                List<Integer> assegnati = lab.entrata(this);
                Thread.sleep(workDelay);
                lab.uscita(this, assegnati);
                Thread.sleep(breakDelay); }
        }
        catch (InterruptedException e) { System.out.printf("%s con id=%d interrotto.\n",categoria.name(), id); return; }
        System.out.printf("%s con id=%d ha terminato il lavoro.\n",categoria.name(), id);
    }
}