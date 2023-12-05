import java.util.*;
public interface ConcurrentMap<K,V> extends Map<K,V> {
    V putIfAbsent(K key, V value);
    // Insert into map only if no value is mapped from K
    // returns the previous value associated to the key
    // or null if there is no mapping for that key
    boolean remove(Object key, Object value);
    // Remove only if K is mapped to V
    boolean replace(K key, V oldValue, V newValue);
    // Replace value only if K is mapped to oldValue
    V replace(K key, V newValue);
// Replace value only if K is mapped to some value
}