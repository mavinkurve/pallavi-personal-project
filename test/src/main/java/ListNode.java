public class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
        next = null;
    }

    void print() {
        System.out.print(val);
        if (next == null) {
            System.out.println("");
            return;
        }
        System.out.print("->");
        next.print();
    }
}
