import java.io.*;
public class FileCopyNoBuffer{
    public static void main(String[] args) {
        String inFileStr = "relax.jpg"; String outFileStr = "relax_new.jpg";
        long startTime, elapsedTime; // for speed benchmarking
        File fileIn = new File(inFileStr);
        System.out.println("File size is " + fileIn.length() + " bytes");
        FileInputStream in; FileOutputStream out;
        try
        { in = new FileInputStream(inFileStr);
            out = new FileOutputStream(outFileStr);
            startTime = System.nanoTime();
            int byteRead;
            while ((byteRead = in.read()) != -1)
            { out.write(byteRead);}
            elapsedTime = System.nanoTime() - startTime;
            System.out.println("Elapsed Time is " + (elapsedTime / 1000000.0) + "msec");
        } catch (IOException ex) { ex.printStackTrace(); }}}

/*File size is 16473 bytes - Elapsed Time is 54.2873 msec*/