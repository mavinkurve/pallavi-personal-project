import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class ExamRoom {
    List<Integer> seats;
    int size;

    public ExamRoom(int N) {
        seats = new ArrayList<>();
        size = N;
    }

    public int seat() {
        if (seats.size() == 0) {
            seats.add(0);
            return 0;
        }
        int max = 0;
        int start = 0, end = 0;
        for (int i = 1; i < seats.size(); i++) {
            if (seats.get(i) - seats.get(i-1) > max) {
                max = seats.get(i) - seats.get(i - 1);
                start = i-1;
                end = i;
            }
        }
        int mid = start + (end - start)/2;
        seats.add(mid);
        Collections.sort(seats);
        return mid;
    }

    public void leave(int p) {
        seats.remove(p);
    }
}