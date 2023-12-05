import java.net.InetAddress; import java.net.UnknownHostException;
import java.security.*;
public class Caching {
    public static final String CACHINGTIME="0";     //in base al variare di questo valore cambia il tempo //prova 0 e 1000
    public static void main(String [] args) throws InterruptedException {
        Security.setProperty("networkaddress.cache.ttl",CACHINGTIME);
        long time1 = System.currentTimeMillis();
        for (int i=0; i<1000; i++){
            try {System.out.println(InetAddress.getByName("www.cnn.com").getHostAddress());}
            catch (UnknownHostException uhe) {
                System.out.println("UHE");
            }
        }
        long time2 = System.currentTimeMillis();
        long diff=time2-time1; System.out.println("tempo trascorso e'"+diff);
    }
}