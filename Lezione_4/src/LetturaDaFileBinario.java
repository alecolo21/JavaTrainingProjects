import java.io.*;
public class LetturaDaFileBinario {
    public static void main(String args[]){
        FileInputStream is = null;
        try { is = new FileInputStream(args[0]); }
        catch(FileNotFoundException e){
            System.out.println("File non trovato");
            System.exit(1);
        }
        try { int x; int n = 0;
            while ((x = is.read())>=0) {
                System.out.println(" " + x); n++; }
            System.out.println("\nTotale byte: " + n);
        }
        catch(IOException ex){
            System.out.println("Errore di input");
            System.exit(2);}
    }
}