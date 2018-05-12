import java.util.Map;
import java.util.TreeMap;

public class ListProblems {

    public ListNode oddEvenList(ListNode head) {
        if (head == null)
            return null;
        ListNode odd = head;
        ListNode even = head.next;
        ListNode evenHead = even;
        while(even != null) {
            odd.next = even.next;
            if (odd.next != null && odd.next.next != null)
                even.next = odd.next.next;
            else
                even.next = null;

            if (odd.next != null)
                odd = odd.next;
            even = even.next;
        }

        odd.next = evenHead;
        return head;
    }
    public ListNode mergeKLists(ListNode[] lists) {
        int k = lists.length;

        ListNode dummy = new ListNode(-1);
        ListNode current = dummy;
        int processedLists = 0;

        while (processedLists < k) {
            int minIndex = 0;
            int min = Integer.MAX_VALUE;
            for (int i = 0; i < k; i++) {
                if (lists[i] == null) {
                    processedLists++;
                    continue;
                }
                if (lists[i].val < min) {
                    min = lists[i].val;
                    minIndex = i;
                }
            }
            current.next = lists[minIndex];
            lists[minIndex] = lists[minIndex].next;
        }

        return dummy.next;
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        System.out.println("Adding ");
        l1.print();
        l2.print();
        ListNode sum = new ListNode(l1.val + l2.val);
        int leftOver = 0;
        if (sum.val >= 10)
        {
            leftOver = 1;
            sum.val = sum.val % 10;
        }
        ListNode root = sum;
        while (l1.next != null && l2.next !=null) {
            l1 = l1.next;
            l2 = l2.next;
            sum.next  = new ListNode(l1.val + l2.val + leftOver);
            sum = sum.next;
            if (sum.val >= 10) {
                leftOver = 1;
                sum.val = sum.val % 10;
            }
            else {
                leftOver = 0;
            }
        }
        ListNode longerNum = null;
        if (l1.next != null) {
            longerNum = l1.next;
        }
        if (l2.next != null) {
            longerNum = l2.next;
        }
        while(longerNum != null) {
            sum.next = new ListNode(longerNum.val + leftOver);
            sum = sum.next;
            if (sum.val >= 10) {
                leftOver = 1;
                sum.val = sum.val % 10;
            }
            else {
                leftOver = 0;
            }
            longerNum = longerNum.next;
        }
        if (leftOver != 0)
            sum.next = new ListNode(leftOver);

        System.out.println("Sum: ");
        root.print();
        return root;
    }

    public ListNode deleteDuplicates(ListNode head) {
        if (head == null)
            return null;
        TreeMap<Integer,Integer> map = new TreeMap<>();
        map.put(head.val,1);
        while (head.next != null) {
            head = head.next;
            if (map.containsKey(head.val))
                map.put(head.val,map.get(head.val) + 1);
            else
                map.put(head.val,1);
        }
        ListNode dummy = new ListNode(-1);
        ListNode current = dummy;
        for (Map.Entry<Integer,Integer> entry : map.entrySet()) {
            if (entry.getValue() == 1) {
                current.next = new ListNode(entry.getKey());
                current = current.next;
            }
        }
        return dummy.next;
    }

    public boolean hasCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;

        while (slow != fast) {
            slow = slow.next;
            if (fast == null || fast.next == null)
                return false;
            else {
                slow = slow.next;
                fast = fast.next.next;
            }
        }
        return true;
    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode current = new ListNode(0);
        ListNode first = current;
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                current.next = l1;
                l1 = l1.next;
            } else {
                current.next = l2;
                l2 = l2.next;
            }
            current = current.next;
        }
        if (l1 != null)
            current.next = l1;
        if (l2 != null)
            current.next = l2;
        if (first.next != null)
            return first.next;
        else return null;
    }

}
