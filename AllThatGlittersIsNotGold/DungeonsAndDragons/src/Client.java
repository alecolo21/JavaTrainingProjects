/*
 * Classe Java che rappresenta il client del gioco.
 *
 * Il client esegue un ciclo nel quale:
 * (1) legge l'input dell'utente da tastiera;
 * (2) invia il messaggio letto al server;
 * (3) riceve (e interpreta) la risposta del server.
 *
 * I comandi supportati dal client sono:
 * (1) fight: combatte contro il mostro;
 * (2) drink: beve una certa quantita' di pozione;
 * (3) potion: visualizza la quantita' di pozione rimanente;
 * (4) leave: abbandona la partita corrente.
 */
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;

public class Client {
    // Percorso del file di configurazione del client.
    public static final String configFile = "client.properties";
    // Variabile globale che rappresenta lo stato corrente.
    public static Status status = Status.PLAYING;
    // Nome host e porta del server.
    public static String hostname; // localhost
    public static int port; // 12000
    // Socket e relativi stream di input/output.
    private static final Scanner scanner = new Scanner(System.in);
    private static Socket socket;
    private static BufferedReader in;
    private static PrintWriter out;

    public static void main(String[] args) {
        try {
            readConfig();
            socket = new Socket(hostname, port);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
            while (true) {
                game();
                // Se la partita e' terminata con una sconfitta oppure
                // e' stata interrotta volontariamente dall'utente, esco dal ciclo.
                if (status == Status.LOSE || status == Status.INTERRUPTED) break;
                // Altrimenti chiedo se si vuole effettuare una nuova partita
                // e invio la risposta al server.
                System.out.print("Nuova partita [y/n]?\n> ");
                String command = scanner.nextLine();
                out.println(command);
                if (!command.equalsIgnoreCase("y")) break;
                status = Status.PLAYING;
            }
        } catch (Exception e) {
            System.err.printf("Errore: %s\n", e.getMessage());
            System.exit(1);
        }
    }

    public static void game() throws IOException {
        System.out.println("Partita iniziata. Inserisci un comando.");
        while (status == Status.PLAYING) {
            System.out.print("> ");
            String command = scanner.nextLine();
            out.println(command);
            String reply = in.readLine();
            String[] parts = reply.split(",");
            switch (parts[0]) {
                case "WIN":
                    status = Status.WIN;
                    break;
                case "LOSE":
                    status = Status.LOSE;
                    break;
                case "DRAW":
                    status = Status.DRAW;
                    break;
                case "INTERRUPTED":
                    status = Status.INTERRUPTED;
                    break;
                default:
                    break;
            }
            System.out.println(parts[1]);
        }
        System.out.println("Partita terminata.");
    }
    /**
     * Metodo che legge il file di configurazione del server.
     * @throws FileNotFoundException se il file non esiste
     * @throws IOException se si verifica un errore durante la lettura
     */
    private static void readConfig() throws FileNotFoundException, IOException {
        try(InputStream input = Server.class.getResourceAsStream(configFile)) {
            System.out.print(input.toString());
            Properties prop = new Properties();
            prop.load(input);
            port = Integer.parseInt(prop.getProperty("port"));
        }
    }
}