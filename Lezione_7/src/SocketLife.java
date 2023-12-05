import java.io.*;
import java.net.*;
public class SocketLife {
    public static void main(String[] args) {
        // instantiate the ServerSocket
        ServerSocket servSock;
        int port = 0;               //controlli messi da me
        boolean done = false;       //controlli

        try{
            servSock = new ServerSocket(port);
            while (!done) {  // oppure while(!"done")
                // accept the incoming connection
                Socket sock = servSock.accept();
                // ServerSocket is connected ... talk via sock
                InputStream in = sock.getInputStream();
                OutputStream out = sock.getOutputStream();
                //client and server communicate via in and out and do their work
                sock.close();
            }
            servSock.close();
        }catch(IOException e){                  //catch error messo da me
            throw new RuntimeException(e);
        }

    }
}
