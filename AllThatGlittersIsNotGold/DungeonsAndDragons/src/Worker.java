
import java.io.*;
import java.net.*;
import java.util.concurrent.*;
public class Worker implements Runnable {
    private final int initialHealth = 10000;
    private final int initialPotion = 2000;
    private int playerHealth = initialHealth;
    private int enemyHealth = initialHealth;
    private int potion = initialPotion;
    private Status status = Status.PLAYING;
    private final Socket socket;
    private BufferedReader in;
    private PrintWriter out;

    public Worker(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
            while (true) {
                game();
                // Se l'utente ha perso o interrotto la partita, termino.
                if (status == Status.LOSE || status == Status.INTERRUPTED) break;
                // Altrimenti, se ha vinto attendo la sua decisione
                String line = in.readLine().toLowerCase();
                if (line == null || !line.equals("y")) break;
                // Resetto le variabili per iniziare una nuova partita.
                reset();
            }
            // Chiudo gli stream e la socket.
            in.close();
            out.close();
            socket.close();
        } catch (Exception e) {
            System.err.printf("[WORKER] Errore: %s\n", e.getMessage());
        }
    }

    public void game() throws IOException {
        System.out.println("[WORKER] Partita iniziata.");
        while (status == Status.PLAYING) {
            String line = in.readLine();
            line = line.toLowerCase();
            switch (line) {
                case "fight":
                    fight();
                    break;
                case "drink":
                    drink();
                    break;
                case "potion":
                    remainingPotion();
                    break;
                case "leave":
                    status = Status.INTERRUPTED;
                    String message = "Hai perso!";
                    out.printf("%s,%s\n", status.name(), message);
                    break;
                default:
                    out.printf("%s,Errore: comando non valido.\n", status.name());
                    break;
            }
        }
    }
    public void fight() {
        int playerDamage = ThreadLocalRandom.current().nextInt(0, playerHealth + 1);
        int enemyDamage = ThreadLocalRandom.current().nextInt(0, enemyHealth + 1);
        playerHealth -= playerDamage;
        enemyHealth -= enemyDamage;
        String message;
        if (playerHealth > 0 && enemyHealth == 0) {
            status = Status.WIN;
            message = "Hai vinto! :-)";
            out.printf("%s,%s\n", status.name(), message);
            return;
        }
        if (playerHealth == 0 && enemyHealth > 0) {
            status = Status.LOSE;
            message = "Hai perso! :-(";
            out.printf("%s,%s\n", status.name(), message);
            return;
        }
        if (playerHealth == 0 && enemyHealth == 0) {
            status = Status.DRAW;
            message = "Pareggio! :-|";
            out.printf("%s,%s\n", status.name(), message);
            return;
        }
        out.printf(
                "%s,Giocatore: %d\tNemico: %d\n", status.name(), playerHealth, enemyHealth);
    }
    public void drink () {
        int quantity = ThreadLocalRandom.current().nextInt(0, potion + 1);
        potion -= quantity;
        playerHealth += quantity;
        out.printf(
                "%s,Giocatore: %d\tNemico: %d\n", status.name(), playerHealth, enemyHealth);
    }
    public void remainingPotion () {
        out.printf("%s,Pozione rimanente: %d\n", status.name(), potion);
    }
    public void reset () {
        playerHealth = enemyHealth = initialHealth;
        potion = initialPotion;
        status = Status.PLAYING;
    }
}