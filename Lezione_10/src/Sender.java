import java.net.*;
import java.nio.charset.StandardCharsets;

public class Sender{
    public static void main (String[] args) {
        try {
            DatagramSocket clientSocket = new DatagramSocket();
            byte[] buffer="1234567890abcdefghijklmnopqrstuvwxyz".getBytes(StandardCharsets.US_ASCII);
            InetAddress address = InetAddress.getByName("Localhost");
            for (int i = buffer.length; i >0; i--) {
                DatagramPacket mypacket = new DatagramPacket(buffer,i,address, 40000);
                clientSocket.send(mypacket);
                Thread.sleep(200); }
            System.exit(0);}
        catch (Exception e) {e.printStackTrace();}
    }
}