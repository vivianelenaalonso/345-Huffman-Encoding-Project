/*  File: HashMap.java
 *  Author: Kevin Li
 *  Purpose: Basic implementation of a HashMap
 */
import java.util.List;

public class HashMap<K, V> implements Map<K, V>{

    private List<Entry<K, V>>[] buckets;    // Array of buckets
    private int size;                       // Number of key-value pairs stored in the map


    // Constructor for HashMap default size is 10
    public HashMap() {
        buckets = new List[10];
        size = 0;
    }

    // Constructor for HashMap with size
    public HashMap(int size) {
        buckets = new List[size];
        size = 0;
    }

    // Checks if key is in the map, if not adds key, value pair to the map
    public void put(K key, V value) {
        int index = key.hashCode() % buckets.length;
        
        if (buckets[index] == null) {
            buckets[index] = new List<Entry<K, V>>();
        }

        buckets[index].add(new Entry<K, V>(key, value));
        size++;
    }

    // Returns the value of input key
    public V get(K key) {
        int index = key.hashCode() % buckets.length;

        if (buckets[index] == null) {
            return null;
        }

        for (Entry<K, V> entry : buckets[index]) {
            if (entry.get().equals(key)) {
                return entry.getValue();
            }
        }

        return null;
    }

    // Removes the key-value pair from the map, returns 0 if removed scuccessfully
    public int remove(K key, V value){
        int index = key.hashCode() % buckets.length;

        if (buckets[index] == null) {
            return 0;
        }

        for (Entry<K, V> entry : buckets[index]) {
            if (entry.get().equals(key)) {
                buckets[index].remove(entry);
                size--;
                return 0;
            }
        }

        return 1;
    }

    public Boolean containsKey(K key){
        int index = key.hashCode() % buckets.length;

        for(Entry<K, V> entry : buckets[index]){
            if(entry.getKey().equals(key)){
                return true;
            }
        }
    }

    // Returns the size of the map
    public int size() {
        return size;
    }

    // Checks if the map is empty
    public Boolean isEmpty() {
        return size == 0;
    }
}