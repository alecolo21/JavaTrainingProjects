import java.net.*;
public class FindAllIP {
    public static void main (String[] args) {
        try { InetAddress [] addresses = InetAddress.getAllByName("www.repubblica.it");
            for(InetAddress address:addresses) {
                System.out.println(address);
            }
        }catch (UnknownHostException ex) {
            System.out.println("Could not find www.repubblica.it");
        }
    }
}