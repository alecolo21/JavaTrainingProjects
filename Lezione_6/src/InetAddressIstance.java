import java.net.*; import java.util.Arrays; import java.io.*;
public class InetAddressIstance {
    public static void main (String[] args) throws IOException {
        InetAddress ia1 = InetAddress.getByName("www.google.com");
        byte [] address = ia1.getAddress();
        System.out.println(Arrays.toString(address));
        System.out.println(ia1.getHostAddress());
        System.out.println(ia1.getHostName());
        System.out.println(ia1.isReachable(1000));
        System.out.println(ia1.isLoopbackAddress());
        System.out.println(ia1.isMulticastAddress());
        System.out.println(InetAddress.getByAddress(new byte[]{127,0,0,1}).isLoopbackAddress());
        System.out.println(InetAddress.getByAddress(new byte[] {(byte)225,(byte)255,(byte)255,
                (byte)255}).isMulticastAddress());
    }
}
