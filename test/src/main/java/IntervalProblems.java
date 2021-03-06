import java.util.*;

public class IntervalProblems {
/*
    public int leastInterval(char[] tasks, int n) {
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < tasks.length; i++) {
            map.put(tasks[i],map.getOrDefault(tasks[i],0) + 1);
        }
        int period = n + 1;
        int intervals = 0;
        while (!map.isEmpty()) {

        }
    }
*/
    public int minMeetingRooms(Interval[] intervals) {
        Arrays.asList(intervals).sort(new IntervalComparator());
        int meetingRooms = 0;
        for (int i = 0; i < intervals.length; i++) {
            boolean found = false;
            for (int j = 0; j < i; j++) {
                if (intervals[i].start >= intervals[j].end) {
                    found = true;
                    break;
                }
            }
            if (!found)
                meetingRooms++;
        }
        return meetingRooms;
    }

    public List<Interval> merge(List<Interval> intervals) {
        Collections.sort(intervals,new IntervalComparator());
        for (int i = 0; i < intervals.size(); i++) {
            for (int j = 0; j < i; j ++) {
                if (intervals.get(i).start <= intervals.get(j).end) {
                    Interval update = intervals.get(j);
                    update.end = Math.max(intervals.get(i).end,intervals.get(j).end);
                    intervals.set(j,update);
                    intervals.remove(i);
                    i--;
                    break;
                }
            }
        }
        return intervals;
    }
}
