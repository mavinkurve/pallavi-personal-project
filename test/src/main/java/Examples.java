

import java.util.*;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

public class Examples {
    public int searchInsert(int[] nums, int target) {
        if (nums.length == 0)
            return -1;



        return findInsertionPlace(nums,0,nums.length-1,target);
    }

    private int findInsertionPlace(int[] nums, int i, int j, int target) {
        if (nums[i] >= target)
            return i;
        if (nums[j] == target)
            return j;
        if (nums[j] < target)
            return j + 1;

        int mid = i + ((j - i)/2);


        if (nums[mid] == target)
            return mid;

        if (nums[mid] > target)
            return findInsertionPlace(nums,i,mid,target);
        else
            return findInsertionPlace(nums,mid +1, j , target);
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

    public int longestValidParenthesis(String s){
        Stack<Character> stack = new Stack();
        int maxSubstringLength = 0;
        int currentSubStringLength = 0;

        for (int i = 0; i < s.length()-1; i++) {
            final char c = s.charAt(i);
            if (c == ')') {
                if (stack.isEmpty()) {
                    // found invalid character, broke the max substring so far
                    maxSubstringLength = Math.max(currentSubStringLength,maxSubstringLength);
                    currentSubStringLength = 0;
                    continue;
                } else {
                    // found a matching parenthesis on stack
                    stack.pop();
                    currentSubStringLength = currentSubStringLength + 2;
                    continue;
                }
            }
            if ((c == '(') ) {
                stack.push(c);
                continue;
            }
            return -1;      //invalid char
        }
        return Math.max(currentSubStringLength,maxSubstringLength);
    }

    public int findContentChildren(int[] g, int[] c) {
        int count = 0;
        final List<Integer> greed = Arrays.stream(g).boxed().collect(Collectors.toList());
        final List<Integer> cookies = Arrays.stream(c).boxed().collect(Collectors.toList());
        Collections.sort(greed);
        Collections.sort(cookies);
        for (int child = 0; child < greed.size();) {
            boolean cookieFound = false;
            for (int cookie = 0; cookie < cookies.size();) {
                if (cookies.get(cookie) >= greed.get(child)) {
                    cookies.remove(cookie);
                    greed.remove(child);
                    count++;
                    cookieFound = true;
                    break;
                }
                else {
                    cookie++;
                }
            }
            if (!cookieFound)
                child++;
        }
        return count;
    }




    public int monotoneIncreasingDigits(int N) {
        List<Integer> ints = new ArrayList<>();
        while (N > 0) {
            ints.add(N % 10);
            N = N / 10;
        }
        Collections.reverse(ints);
        for (int i = ints.size()-1; i > 0; i--) {
            if (ints.get(i) < ints.get(i-1)) {
                for (int j = i; j < ints.size(); j++) {
                    ints.set(j, 9);
                }
                ints.set(i-1,ints.get(i-1) == 0 ? 9 : ints.get(i-1)-1);
            }
        }

        int returnInt = 0;
        for (Integer anInt : ints) {
            returnInt = (returnInt * 10) + anInt;
        }
        return returnInt;
    }

    public List<Person> reconstructQueue(List<Person> people) {
        Stack<Person> stack = new Stack<>();
        Collections.sort(people,new PersonComparator());

        for (Person person : people) {
            if (stack.isEmpty()) {
                stack.push(person);
                continue;
            }

            List<Person> poppedPeople = new ArrayList<>();
            for (int j = 1; j <= person.k; j++) {
                poppedPeople.add(stack.pop());
            }
            stack.push(person);
            for(int p = poppedPeople.size()-1;p>=0;p--) {
                stack.push(poppedPeople.get(p));
            }

        }

        List<Person> queue = new ArrayList<>();
        while(!stack.isEmpty()){
            queue.add(stack.pop());
        }
        return queue;
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
}
