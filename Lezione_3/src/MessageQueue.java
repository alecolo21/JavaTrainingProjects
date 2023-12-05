//implementazione di produttore consumatore con monitor
public class MessageQueue {
    int putptr, takeptr, count;
    final Object[] items;

    public MessageQueue(int size) {
        items = new Object[size];
        count = 0;
        putptr = 0;
        takeptr = 0;
    }

    public synchronized void produce(Object x) {
        while (count == items.length){
            try {
                wait();
            } catch (Exception e) {
                System.out.print("Exception:" + e.getMessage());
            }
            // gestione puntatoricoda
            items[putptr] = x;
            putptr++;
            ++count;
            if (putptr == items.length) putptr = 0;
            System.out.println("Message Produced" + x);
            notifyAll();
        }
    }
    public synchronized Object consume() {
        while (count == 0)
            try {
                wait();}
            catch(InterruptedException e) {}
        // gestione puntatori coda
        Object data = items[takeptr]; takeptr=takeptr+1; --count;
        if (takeptr == items.length) {takeptr = 0;};
        notifyAll();
        System.out.println("Message Consumed"+data);
        return data;
    }
}