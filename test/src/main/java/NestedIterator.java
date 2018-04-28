import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class NestedIterator implements Iterator<Integer> {
    List<Integer> list = new ArrayList<>();
    Integer current = 0;

    public NestedIterator(List<NestedInteger> nestedList) {
        flattenList(nestedList);
    }

    private void flattenList(List<NestedInteger> nestedList) {
        nestedList.forEach(l -> {
            if (l.isInteger())
                list.add(l.getInteger());
            else {
                flattenList(l.getList());
            }
        });
    }

    @Override
    public Integer next() {
        int i = list.get(current);
        current++;
        return i;
    }

    @Override
    public boolean hasNext() {
        return (current <= list.size() - 1);
    }
}
