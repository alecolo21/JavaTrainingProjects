/**
 * Reti e Laboratorio III - A.A. 2022/2023
 * Dungeon Adventures
 *
 * Classe Java che rappresenta il server del gioco.
 *
 * Il server gestisce un pool di thread ed esegue un ciclo nel quale:
 * (1) accetta richieste di connessione da parte dei vari client;
 * (2) per ogni richiesta, attiva un thread Worker per interagire con il client;
 *
 */

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;
public class Server {
    public static final String configFile = "server.properties";
    public static int port;
    public static int maxDelay;
    public static final ExecutorService pool = Executors.newCachedThreadPool();
    public static ServerSocket serverSocket;

    public static void main(String[] args) throws Exception {
        try {
            try{readConfig();}catch(Exception e){System.out.print("errore readconfig");}
            serverSocket = new ServerSocket(port);
            Runtime.getRuntime().addShutdownHook(
                    new TerminationHandler(maxDelay, pool, serverSocket)
            );
            System.out.printf("[SERVER] In ascolto sulla porta: %d\n", port);
            while (true) {
                Socket socket;
                // Accetto le richieste provenienti dai client.
                try {socket = serverSocket.accept();}
                catch (SocketException e) {break;}
                pool.execute(new Worker(socket));
            }
        } catch (Exception e) {
            System.err.printf("[SERVER]: %s\n",e.getMessage());
            System.exit(1);
        }
    }

    /**
     * Metodo che legge il file di configurazione del server.
     * @throws FileNotFoundException se il file non esiste
     * @throws IOException se si verifica un errore durante la lettura
     */
    private static void readConfig() throws FileNotFoundException, IOException {
        try(InputStream input = Server.class.getResourceAsStream(configFile)) {
            Properties prop = new Properties();
            prop.load(input);
            port = Integer.parseInt(prop.getProperty("port"));
            maxDelay = Integer.parseInt(prop.getProperty("maxDelay"));
        }

    }
}