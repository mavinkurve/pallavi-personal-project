import java.util.ArrayList;
import java.util.List;

class MedianFinder {

    List<Integer> numbers;
    int currentMedian;

    /** initialize your data structure here. */
    public MedianFinder() {
        numbers = new ArrayList<>();
        currentMedian = 0;
    }

    public void addNum(int num) {

    }

    public double findMedian() {
        return currentMedian;
    }
}