import java.nio.channels.*;
public abstract class SelectionKey
{public static final int OP_READ;
    public static final int OP_WRITE;
    public static final int OP_CONNECT;
    public static final int OP_ACCEPT;
    public abstract SelectableChannel channel( );
    public abstract Selector selector( );
    public abstract void cancel( );
    public abstract boolean isValid( );
    public abstract int interestOps( );
    public abstract void interestOps (int ops);
    public abstract int readyOps( );
    public final boolean isReadable( ) {};
    public final boolean isWritable( ) {};
    public final boolean isConnectable( ) {};
    public final boolean isAcceptable( ) {};
    public final Object attach (Object ob) {};
    public final Object attachment( ) {};}