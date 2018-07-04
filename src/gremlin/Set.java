package gremlin;

/**
 * Auxiliary class that builds a pair with two generic objects.
 * @param <K>
 * @param <V>
 */
public class Set<K, V> {
    private K key;
    private V value;

    Set(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }
}