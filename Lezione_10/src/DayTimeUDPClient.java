import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;

public class DayTimeUDPClient {
    // RFC-867
    private final static int PORT = 13;
    private static final String HOSTNAME = "test.rebex.net";
    public static void main(String[] args) {
        try (DatagramSocket socket = new DatagramSocket(0)) {
            socket.setSoTimeout(15000);
            InetAddress host = InetAddress.getByName(HOSTNAME);
            DatagramPacket request = new DatagramPacket(new byte[1], 1, host , PORT);
            DatagramPacket response = new DatagramPacket(new byte[1024], 1024);
            socket.send(request);
            socket.receive(response);
            String daytime = new String(response.getData(),0,response.getLength(), StandardCharsets.US_ASCII);  System.out.println(daytime);
        }
        catch (IOException ex) { ex.printStackTrace(); }
    }
}