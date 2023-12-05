import java.io.File;
import java.io.*;
import java.io.IOException;

public class FileCopyBuffered {
    public static void main(String[] args) {
        String inFileStr = "relax.jpg"; String outFileStr = "relax_new.jpg";
        long startTime, elapsedTime; // for speed benchmarking
        File fileIn = new File(inFileStr);
        System.out.println("File size is " + fileIn.length() + " bytes");
        BufferedInputStream in; BufferedOutputStream out;
        try
        { in = new BufferedInputStream(new FileInputStream(inFileStr));
            out = new BufferedOutputStream(new FileOutputStream(outFileStr));
            startTime = System.nanoTime();
            int byteRead;
            while ((byteRead = in.read()) != -1)
            { out.write(byteRead);}
            elapsedTime = System.nanoTime() - startTime;
            System.out.println("Elapsed Time is " + (elapsedTime / 1000000.0) + "msec");
        } catch (IOException ex) { ex.printStackTrace(); }}}

/*File size is 16473 bytes - Elapsed Time is 1.2581 msec*/
/*molto più veloce di FileCopyNoBuffer*/