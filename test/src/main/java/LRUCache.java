import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class LRUCache {

    LinkedList<Integer> linkedList;
    Map<Integer,Integer> hashMap;
    int maxCapacity;

    public LRUCache(int capacity) {
        maxCapacity = capacity;
        hashMap = new HashMap<>();
        linkedList = new LinkedList<>();
    }

    public int get(int key) {
        int value = hashMap.getOrDefault(key,-1);
        if (value != -1) {
            // rearrange linked list
            linkedList.remove(Integer.valueOf(key));
            linkedList.addFirst(key);
        }
        return value;
    }

    public void put(int key, int value) {
        // check if updating existing value
        if (hashMap.getOrDefault(key,-1) == -1 && linkedList.size() == maxCapacity) {
            // evict oldest from linked list & mark deleted in hashmap
            int k = linkedList.removeLast();
            hashMap.put(k,-1);
        }

        hashMap.put(key,value);
        linkedList.remove(Integer.valueOf(key));
        linkedList.addFirst(key);
    }
}
