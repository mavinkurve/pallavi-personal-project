import java.util.*;
import java.util.stream.Collectors;

public class ListProblems {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        Stack<Integer> s1 = new Stack<>();
        Stack<Integer> s2 = new Stack<>();

        while(l1 != null){
            s1.push(l1.val);
            l1 = l1.next;
        }

        while(l2 != null){
            s2.push(l2.val);
            l2 = l2.next;
        }

        int carry = 0;
        ListNode sum = new ListNode(-1);
        while (!s1.isEmpty() && !s2.isEmpty()) {
            int digit = carry + s1.pop() + s2.pop();
            if (digit > 9) {
                carry = 1;
                digit = digit % 10;
            }
            else
                carry = 0;
            ListNode digitNode = new ListNode(digit);
            digitNode.next = sum.next;
            sum.next = digitNode;
        }
        while (s1.size() < s2.size()) {
            s1.push(0);
        }
        while(s2.size() < s1.size()) {
            s2.push(0);
        }
        while (!s1.isEmpty() && !s2.isEmpty()) {
            int digit = carry + s1.pop() + s2.pop();
            if (digit > 9) {
                carry = 1;
                digit = digit % 10;
            }
            else
                carry = 0;
            ListNode digitNode = new ListNode(digit);
            digitNode.next = sum.next;
            sum.next = digitNode;
        }
        return sum.next;
    }

    public ListNode partition(ListNode head, int x) {
        List<Integer> original = new ArrayList<>();
        ListNode dummy = head;
        while (dummy != null) {
            original.add(dummy.val);
            dummy = dummy.next;
        }
        List<Integer> partition = new ArrayList<>();
        partition.addAll(original.stream().filter(i -> i < x).collect(Collectors.toList()));
        partition.addAll(original.stream().filter(i -> i >= x).collect(Collectors.toList()));
        dummy = head;
        for (int i = 0; i < partition.size(); i++) {
            dummy.val = partition.get(i);
            dummy = dummy.next;
        }
        return head;
    }

    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null)
            return head;

        ListNode prev = new ListNode(-1);
        prev.next = head;
        ListNode dummy = prev;

        while (head != null) {
            while (head.next != null && head.next.val == head.val){
                prev.next = head.next.next;
            }
            head = head.next;
        }
        return dummy.next;
    }

    public ListNode rotateRight(ListNode head, int k) {
        ListNode dummy = head;
        List<Integer> list = new ArrayList<>();
        while (dummy != null) {
            list.add(dummy.val);
            dummy = dummy.next;
        }
        dummy = head;
        if (k > list.size())
            k = k % list.size();
        Collections.rotate(list,k);
        for (int i = 0; i < list.size(); i++) {
            dummy.val = list.get(i);
            dummy = dummy.next;
        }
        return head;
        }

        public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null)
            return head;

        ListNode restOfListHead = head.next.next;
        ListNode next = head.next;
        next.next = head;
        head.next = swapPairs(restOfListHead);

        return next;
    }

    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null)
            return head;
        ListNode dummy = head;
        ListNode prev = head;
        ListNode slow = head;
        ListNode fast = head;
        for (int i = 0; i < n; i++) {
            fast = fast.next;
        }
        System.out.println("Slow is at: " + slow.val);
        System.out.println("Prev is at: " + prev.val);

        while (fast != null) {
            ListNode temp = slow;
            slow = slow.next;
            fast = fast.next;
            prev = temp;
        }
        System.out.println("Slow is at: " + slow.val);
        System.out.println("Prev is at: " + prev.val);

        if (prev == slow)   // removing head node
            return slow.next;
        prev.next = slow.next;
        return dummy;
    }

    public boolean isPalindrome(ListNode head) {
        if (head == null)
            return true;

        List<Integer> list = new ArrayList<>();

        while (head != null) {
            list.add(head.val);
            head = head.next;
        }

        if (list.size()  <= 1)
            return true;

        int start = 0;
        int end = list.size() - 1;
        while (end > start) {
            if (!list.get(start).equals(list.get(end)))
                return false;
            start++;
            end--;
        }
        return true;
    }

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

    public ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
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

    public ListNode deleteDuplicates2(ListNode head) {
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
