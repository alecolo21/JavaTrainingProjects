import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
public class VectorArrayList {
    public static void addElements(List<Integer> list) {
        for (int i=0; i< 1000000; i++) {
        list.add(i);
        }
    }
    public static void main (String[] args){
        final long start1 = System.nanoTime();
        addElements(new Vector<Integer>());
        final long end1=System.nanoTime();
        final long start2 =System.nanoTime();
        addElements(new ArrayList<Integer>());
        final long end2=System.nanoTime();
        System.out.println("Vector time "+ (end1-start1));
        System.out.println("ArrayList time "+ (end2-start2));
    }
}
/* Vector time 74494150 - ArrayList time 48190559*/