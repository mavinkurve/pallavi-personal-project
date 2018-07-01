import java.util.ArrayList;
import java.util.List;

class HitCounter {
    List<Integer> hits;

    /** Initialize your data structure here. */
    public HitCounter() {
        hits = new ArrayList<>();
    }

    /** Record a hit.
     @param timestamp - The current timestamp (in seconds granularity). */
    public void hit(int timestamp) {
        hits.add(timestamp);
    }

    /** Return the number of hits in the past 5 minutes.
     @param timestamp - The current timestamp (in seconds granularity). */
    public int getHits(int timestamp) {
        int h = 0;
        timestamp = timestamp - 300;
        for (int i = hits.size() - 1; i >= 0; i--) {
            if (hits.get(i) > timestamp)
                h++;
        }
        return h;
    }
}