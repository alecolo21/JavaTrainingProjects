import java.util.*;
public class Laboratorio {
    public final int numComputer = 20; // Numero di computer nel laboratorio (costante).
    public final int idComputerTesisti = 19; // Identificativo del computer richiesto dai tesisti.
    private List<Thread> thread; // Lista dove memorizzo i riferimenti ai thread.
    // Array per tenere traccia dei computer liberi e occupati.
    // L'elemento i-esimo e' false se e solo se il computer e' libero.
    private boolean[] computer;
    // Utilizzo questi contatori per tenere traccia di professori
    // e tesisti in attesa di entrare nel laboratorio.
    private int profWaiting = 0;
    private int tesiWaiting = 0;
    /**
     * Costruttore della classe Laboratorio
     * che inizializza tutte le strutture dati.
     */
    public Laboratorio() {
        thread = new ArrayList<>();
        computer = new boolean[numComputer];
    }
    public void start(int numProf, int numTesisti, int numStudenti) {
        System.out.println("Laboratorio aperto.");
        for (int i = 0; i < numProf; i++) // Creo gli utenti, un thread per ognuno di essi.
        {thread.add(new Thread(new User(Categoria.PROFESSORE, i, this))); }
        for (int i = 0; i < numTesisti; i++)
        {thread.add(new Thread(new User(Categoria.TESISTA, i, this))); }
        for (int i = 0; i < numStudenti; i++)
        {thread.add(new Thread( new User(Categoria.STUDENTE, i, this))); }
        // Per simulare l'arrivo degli utenti in ordine casuale, eseguo uno shuffle della lista di therad prima di avviarli.
        Collections.shuffle(thread, new Random(System.currentTimeMillis()));
        for (Thread t : thread) t.start();
        // Attendo la terminazione di tutti i thread usando la join().
        for (Thread t : thread)
        { try {t.join();} catch (InterruptedException e)
        {System.err.println("Interruzione durante l'attesa dei thread!");}
        } System.out.println("Laboratorio chiuso.");
    }
    public synchronized List<Integer> entrata(User u) throws InterruptedException {
        List<Integer> assegnati = new ArrayList<>();
        System.out.printf("%s con id=%d in attesa di entrare.\n", u.categoria.name(),u.id);
        // Quindi procedo in maniera diversa a seconda del tipo di utente.
        switch (u.categoria) {
            // I professori attendono finche' tutti i computer non
            // sono disponibili e quindi occupano tutto il laboratorio.
            case PROFESSORE:
                profWaiting++;
                while (!libero()) wait();
                profWaiting--;
                for (int i = 0; i < computer.length; i++) {
                    computer[i] = true;
                    assegnati.add(i);
                }
                break;
            // I tesisti occupano sempre uno specifico computer.
            case TESISTA:
                tesiWaiting++;
                while (profWaiting > 0 || computer[idComputerTesisti])
                    wait();
                tesiWaiting--;
                computer[idComputerTesisti] = true;
                assegnati.add(idComputerTesisti);
                break;
            // Gli studenti occupano il primo computer libero.
            case STUDENTE:
                int id = primoComputerLibero();
                // Lo studente attende finche' ci sono professori che stanno aspettando, oppure se non  ci sono computer disponibili o se il computer assegnato e' quello dei tesisti e ci
                // sono gia' tesisti prenotati per l'entrata.
                while (profWaiting > 0 || id == -1 || (tesiWaiting > 0 && id == idComputerTesisti)) {
                    wait();
                    id = primoComputerLibero();
                }
                computer[id] = true;
                assegnati.add(id);
                break;
            default: break; }
        System.out.printf("%s con id=%d entrato.\n", u.categoria.name(), u.id);
        return assegnati;
    }
    /**
     * Metodo invocato dall’utente all’uscita del laboratorio.
     * @param u l'utente che desidera uscire dal laboratorio
     * @param occupati lista con gli id dei computer da liberare
     */
    public synchronized void uscita(User u, List<Integer> occupati) {
        // Libero tutti i computer occupati.
        for (Integer id : occupati) computer[id] = false;
        // Risveglio tutti gli utenti in attesa.
        // NOTA: al proprio risveglio, ciascun utente controllera'
        // la validita' della propria condizione di attesa.
        notifyAll();
        System.out.printf("%s con id=%d uscito.\n", u.categoria.name(), u.id);
    }
    /**
     * Restituisce true se e solo se tutto il laboratorio e' libero.
     * @return true se tutti i computer non sono occupati
     */
    private boolean libero() {
        for (int i = 0; i < computer.length; i++)
            if (computer[i]) return false;
        return true;}
    /**
     *Restituisce l'identificativo del primo computer libero.
     *@return l'id del primo computer libero
     */
    private int primoComputerLibero() {
        for (int i = 0; i < computer.length; i++)
            if (!computer[i]) return i;
        return -1;
    }
    public static void main(String[] args) {
        // Verifica e parsing dei parametri da riga di comando.
        if (args.length < 3) {
            System.err.println("Esegui come: Laboratorio " + "<numProf> <numTesisti> <numStudenti>");
                    System.exit(1);
        }
        int numProf = Integer.parseInt(args[0]),
                numTesisti = Integer.parseInt(args[1]),
                numStudenti = Integer.parseInt(args[2]);
        // Creo il laboratorio e faccio entrare gli utenti.
        Laboratorio lab = new Laboratorio();
        lab.start(numProf, numTesisti, numStudenti);
    }
}