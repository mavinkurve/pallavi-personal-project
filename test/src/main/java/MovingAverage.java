import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class MovingAverage {
    Queue<Integer> window;
    int maxSize;
    double currentSum;
    /** Initialize your data structure here. */
    public MovingAverage(int size) {
        window = new LinkedList<>();
        maxSize = size;
        currentSum = 0;
    }

    public double next(int val) {
        if (window.size() == maxSize) {
            currentSum -= window.poll();
        }
        window.offer(val);
        currentSum += val;
        return (currentSum/window.size());
    }
}
