import java.net.*;
public class FindLocalHost {
    public static void main (String[] args) {
        try {
            InetAddress address = InetAddress.getLocalHost();
            System.out.println(address);
        } catch (UnknownHostException ex) {
            System.out.println("Could not find local host");
        }
    }
}
