import java.net.*;
import java.io.*;
public class SocketInfo {
    public static void main(String [] args)
    { for (String host: args) {
        try {
            Socket theSocket = new Socket (host, 80);
            System.out.println("Connected to "+theSocket.getInetAddress()
                    +" on port"+ theSocket.getPort()+ " from port "
                    + theSocket.getLocalPort() + " of"
                    + theSocket.getLocalAddress());
        } catch(UnknownHostException ex) {
            System.out.println("I cannot find"+host);}
        catch(SocketException ex) {
            System.out.println("Could not connect to"+host);}
        catch(IOException ex) { System.out.println(ex);
        }
    }
    }
}
//parametri: www.repubblica.it www.google.com
