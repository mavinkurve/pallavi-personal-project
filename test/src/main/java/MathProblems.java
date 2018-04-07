import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MathProblems {

    public double myPow(double x, int n) {
        double result = x;
        if (n <= 0)
            return 1;
        else
            return x * myPow(x,n-1);
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

    public int maxIncreaseKeepingSkyline(int[][] grid) {
        // compute skyline
        List<Integer> topSkyline = new ArrayList<>();
        List<Integer> leftSkyline = new ArrayList<>();
        int max = 0;
        // row max
        for (int i = 0; i < grid.length;i++) {
            for (int j = 0; j < grid[0].length; j++) {
                max = Math.max(grid[i][j],max);
            }
            leftSkyline.add(max);
            max = 0;
        }
        for (int i = 0; i < grid[0].length;i++) {
            for (int j = 0; j < grid.length; j++) {
                max = Math.max(grid[j][i],max);
            }
            topSkyline.add(max);
            max = 0;
        }
        int result = 0;
        for (int i = 0; i < grid.length;i++) {
            for (int j = 0; j < grid[0].length; j++) {
                int element = grid[i][j];
                int value = Math.min(topSkyline.get(j),leftSkyline.get(i));
                result += (value - element);
                grid[i][j] = value;
            }
        }
        return result;
    }
}

class IntervalComparator implements Comparator<Interval> {
    @Override
    public int compare(Interval o1, Interval o2) {
        return Integer.compare(o1.start,o2.start);
    }
}
