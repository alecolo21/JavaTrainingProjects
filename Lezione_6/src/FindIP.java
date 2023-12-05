import java.net.*;
public class FindIP {
    public static void main (String[] args) {
        try {
            InetAddress address = InetAddress.getByName("www.unipi.it");
            System.out.println(address);
        } catch (UnknownHostException ex) {
            System.out.println("Could not find www.unipi.it");
        }
    }
}
