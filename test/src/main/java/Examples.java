
import java.util.Stack;

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
}
