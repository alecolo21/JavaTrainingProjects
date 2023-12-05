import java.util.*;
import java.util.concurrent.*;
public class CHashMap {
    private Map<String, Object> theMap =
            new ConcurrentHashMap<>();
    public Object getOrCreate(String key) {
        Object value = theMap.get(key);
        try { Thread.sleep(5000);
        } catch(Exception e) {};
        if (value == null) {
            value = new Object();
            theMap.put(key, value); }
        return value.hashCode();}}
