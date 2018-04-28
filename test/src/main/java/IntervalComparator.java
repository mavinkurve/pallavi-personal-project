import java.util.Comparator;

class IntervalComparator implements Comparator<Interval> {
    @Override
    public int compare(Interval o1, Interval o2) {
        return Integer.compare(o1.start,o2.start);
    }
}
