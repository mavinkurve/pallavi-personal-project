import java.util.*;

class PhoneDirectory {
    SortedSet<Integer> pool;

    /** Initialize your data structure here
     @param maxNumbers - The maximum numbers that can be stored in the phone directory. */
    public PhoneDirectory(int maxNumbers) {
        pool = new TreeSet<>();
        for (int i = 0; i < maxNumbers; i++) {
            pool.add(i);
        }
    }

    /** Provide a number which is not assigned to anyone.
     @return - Return an available number. Return -1 if none is available. */
    public int get() {
        int number = -1;
        if (pool.size() > 0) {
            number = pool.first();
            pool.remove(number);
        }
        return number;
    }

    /** Check if a number is available or not. */
    public boolean check(int number) {
        return pool.contains(number);
    }

    /** Recycle or release a number. */
    public void release(int number) {
        if (!pool.contains(number))
            pool.add(number);

        List<Integer> list = new ArrayList<>();
        for (int i : list) {
            i += 10;
        }

    }
}

/**
 * Your PhoneDirectory object will be instantiated and called as such:
 * PhoneDirectory obj = new PhoneDirectory(maxNumbers);
 * int param_1 = obj.get();
 * boolean param_2 = obj.check(number);
 * obj.release(number);
 */