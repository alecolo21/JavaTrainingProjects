import java.net.*;
import java.nio.charset.StandardCharsets;

public class Receiver{
    public static void main(String[] args) throws Exception {
    DatagramSocket serverSock= new DatagramSocket(40000);
    byte[] buffer = new byte[100];
    DatagramPacket receivedPacket = new DatagramPacket(buffer, buffer.length);
    while (true) {
        serverSock.receive(receivedPacket);
        //String byteToString = new String(receivedPacket.getData(), StandardCharsets.US_ASCII);
        String byteToString = new String(receivedPacket.getData(),0,receivedPacket.getLength(), StandardCharsets.US_ASCII);
        int l=byteToString.length();
        System.out.println(l);
        System.out.println("Length " + receivedPacket.getLength() + " data " + byteToString);
        //if(receivedPacket.getLength()==1)break;
    }
    //System.out.print("Terminato");
}
}