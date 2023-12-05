import java.net.InetAddress;
import java.security.Security;
import java.io.*;

public class Weblog {
    // Tempo di permanenza in cache.
    public static final int cachingTime = 600;
    public static final String stringCachingTime = String.format("%s", cachingTime);
    public static void main(String[] args) {
        // Lettura dei parametri da riga di comando.
        if (args.length < 2) {
            System.err.println("Usage: Weblog <inputFile> <outputFile>");
            System.exit(1);
        }
        String inputFile = args[0], outputFile = args[1];
        // Imposto il tempo di permamenza in cache degli indirizzi tradotti.
        Security.setProperty("networkaddress.cache.ttl", stringCachingTime);
        long start = System.currentTimeMillis();
        int count = 0;
        try (
                BufferedReader in = new BufferedReader(new FileReader(inputFile));
                PrintWriter out = new PrintWriter(outputFile);
        ) {
            String line;
            // per ogni riga estraggo l'indirizzo IP e lo traduco.
            while ((line = in.readLine()) != null) {
                String[] parts = line.split("-", 2);
                String address = parts[0].trim();
                String hostname = InetAddress.getByName(address).getHostName();
                // Scrivo la riga tradotta sul file di output.
                out.printf("%s -%s\n", hostname, parts[1]);
                count++;
            }
        }
        catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            System.exit(1);
        }
        long end = System.currentTimeMillis();
        // Stampo il numero di righe lette e il tempo impiegato (in ms).
        System.out.printf(
                "N. of lines\t: %d\nElapsed time\t: %d ms\n", count, end-start
        );
    }
}