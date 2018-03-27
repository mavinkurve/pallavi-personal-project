public class driver {

    public static void main(String[] args) {

        examples e = new examples();

        ListNode l1 = new ListNode(1);
        ListNode l2 = new ListNode(2);
        ListNode l3 = new ListNode(3);
        ListNode l4 = new ListNode(4);
        l1.next = l2;
        l2.next = l3;
        l3.next = l4;
        l4.next = l3;

        p("has Cycle? " + e.hasCycle(l1));

/*
        p("insert in sorted array: " + e.insertInSortedArray(new int[]{1,3,5,6},5));
        */
    }

    private static void p(String s) {
        System.out.println(s);
    }



}
