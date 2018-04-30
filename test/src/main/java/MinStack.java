import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class MinStack {

    PriorityQueue<Integer> heap;
    Map<Integer,Integer> locationMap;
    Map<Integer,Integer> valueMap;

    int size = 0;
    int top = 0;

    public MinStack() {
        heap = new PriorityQueue<>();
        locationMap = new HashMap<>();
        valueMap = new HashMap<>();
    }

    // push element x onto stack
    public void push (int x) {
        heap.offer(x);
        size++;
        locationMap.put(size, x);
        valueMap.put(x, size);
        top = x;
    }

    // remove element on top of stack
    public void pop() {
        int x = valueMap.remove(top);
        locationMap.remove(x);
        heap.remove(top);
        size--;
        top = locationMap.get(size);
    }

    // get top element
    public int top() {
        return top;
    }

    // get minimum element in stack
    public int getMin() {
        int x = heap.poll();
        int location = valueMap.remove(x);
        locationMap.remove(location);
        size--;
        return x;
    }

}
