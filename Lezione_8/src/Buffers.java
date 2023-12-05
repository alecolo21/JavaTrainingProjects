import java.nio.*;
public class Buffers {
    public static void main(String[] args) {
        ByteBuffer byteBuffer1 = ByteBuffer.allocate(10);
        System.out.println(byteBuffer1);
        // java.nio.HeapByteBuffer[pos=0 lim=10 cap=10]
        byteBuffer1.putChar('a');
        System.out.println(byteBuffer1);
        // java.nio.HeapByteBuffer[pos=2 lim=10 cap=10]
        byteBuffer1.putInt(1);
        System.out.println(byteBuffer1);
        // java.nio.HeapByteBuffer[pos=6 lim=10 cap=10]
        byteBuffer1.flip();
        System.out.println(byteBuffer1);
        // java.nio.HeapByteBuffer[pos=0 lim=6 cap=10]
        System.out.println(byteBuffer1.getChar());
        System.out.println(byteBuffer1);
        // a
        // java.nio.HeapByteBuffer[pos=2 lim=6 cap=10]
        byteBuffer1.compact();
        System.out.println(byteBuffer1);
        // java.nio.HeapByteBuffer[pos=4 lim=10 cap=10]
        byteBuffer1.putInt(2);
        System.out.println(byteBuffer1);
        // java.nio.HeapByteBuffer[pos=8 lim=10 cap=10]
        byteBuffer1.flip();
        // java.nio.HeapByteBuffer[pos=0 lim=8 cap=10]
        System.out.println(byteBuffer1.getInt());
        System.out.println(byteBuffer1.getInt());
        System.out.println(byteBuffer1);
        // 1
        // 2
        // java.nio.HeapByteBuffer[pos=8 lim=8 cap=10]
        byteBuffer1.rewind();
        // rewind prepara a rileggere i dati che sono nel buffer, ovvero resetta
        //position a 0 e non modifica limit
        // java.nio.HeapByteBuffer[pos=0 lim=8 cap=10]
        System.out.println(byteBuffer1.getInt());
        // 1
        byteBuffer1.mark();
        System.out.println(byteBuffer1.getInt());
        // 2
        System.out.println(byteBuffer1);
        //position:8;limit:8;capacity:10
        byteBuffer1.reset();
        System.out.println(byteBuffer1);
        //position:4;limit:8;capacity:10
        byteBuffer1.clear();
        System.out.println(byteBuffer1);
        //position:0;limit:10;capacity:10]]>
    }
}