import java.nio.ByteBuffer;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;
import java.nio.channels.Channels;
import java.io.*;
public class ChannelCopy {
    public static void main(String[] argv) throws IOException {
        ReadableByteChannel source =
                Channels.newChannel(new FileInputStream("in.txt"));
        WritableByteChannel dest =
                Channels.newChannel(new FileOutputStream("out.txt"));
        channelCopy1(source, dest);
        source.close();
        dest.close();
    }

    private static void channelCopy1 (ReadableByteChannel src,WritableByteChannel dest) throws IOException {
        ByteBuffer buffer = ByteBuffer.allocateDirect (16 * 1024);
        while (src.read (buffer) != -1) {
            // prepararsi a leggere i byte che sono stati inseriti nel buffer
            buffer.flip();
            // scrittura nel file destinazione; può essere bloccante
            dest.write (buffer);
            // non è detto che tutti i byte siano trasferiti, dipende da quanti
            // bytes la write ha scaricato sul file di output
            // compatta i bytes rimanenti all'inizio del buffer
            // se il buffer è stato completamente scaricato, si comporta come clear()
            buffer.compact();
        }
        // quando si raggiunge l'EOF, è possibile che alcuni byte debbano essere ancor
        // scritti nel file di output
        buffer.flip();
        while (buffer.hasRemaining()) { dest.write (buffer); }
    }
    private static void channelCopy2 (ReadableByteChannel src,WritableByteChannel dest) throws IOException {
        ByteBuffer buffer = ByteBuffer.allocateDirect (16 * 1024);
        while (src.read (buffer) != -1) {
            // prepararsi a leggere i byte inseriti nel buffer dalla lettura
            // del file
            buffer.flip();
            // riflettere sul perchè del while
            // una singola lettura potrebbe non aver scaricato tutti i dati
            while (buffer.hasRemaining()) {
                dest.write (buffer); }
            // a questo punto tutti i dati sono stati letti e scaricati sul file
            // preparare il buffer all'inserimento dei dati provenienti
            // dal file
            buffer.clear();
        }
    }
}