import java.io.*;
public class TestDataIOStream {
    public static void main(String[] args) {
        String filename = "data-out.dat";
        // Write primitives to an output file
        try (DataInputStream in =
                     new DataInputStream(
                             new BufferedInputStream(
                                     new FileInputStream(filename)))) {
            System.out.println("byte: " + in.readByte());
            System.out.println("short: " + in.readShort());
            System.out.println("int: " + in.readInt());
            System.out.println("long: " + in.readLong());
            System.out.println("float: " + in.readFloat());
            System.out.println("double: " + in.readDouble());
            System.out.println("boolean: " + in.readBoolean());} catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
