import java.util.*;
import java.util.stream.IntStream;

public class Practice2 {

    public static Boolean isIntegerPalindrome(int x) {
        Integer xInt = (Integer) x;
        StringBuilder sb = new StringBuilder(xInt.toString());
        StringBuilder reverse = sb.reverse();
        return reverse.toString().equalsIgnoreCase(xInt.toString());
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

    public int removeDuplicates(int[] nums) {
        System.out.println("Input Array:");
        for (int i = 0; i < nums.length; i++) {
            System.out.print(nums[i] + ", ");
        }

        if (nums.length == 0) return 0;
        int i = 0;
        for (int j = 1; j < nums.length; j++) {
            if (nums[j] != nums[i]) {
                i++;
                nums[i] = nums[j];
            }
        }

        System.out.println("Output Array:");
        for (int k = 0; k < i + 1; k++) {
            System.out.print(nums[k] + ", ");
        }

        return i + 1;

    }

    public String countAndSay(int n) {
        String output = "1";
        System.out.println("1 : 1");
        if (n == 1) {
            return output;
        }
        for (int i = 2; i <= n; i++) {
            output = printInts(output);
            System.out.println(i + " : " + output);
        }
        return output;
    }

    private String printInts(String i) {
        String[] split = i.split("");
        if (split.length == 1)
            return ("1" + i);
        Integer count = 1;
        StringBuilder output = new StringBuilder();
        for (int c = 0; c < split.length - 1; c++) {
            if (split[c].equalsIgnoreCase(split[c + 1])) {
                count++;
            } else {
                output.append(count);
                output.append(split[c]);
                count = 1;
            }
        }
        output.append(count);
        output.append(split[split.length - 1]);
        return output.toString();
    }

    public int maxSubArray(int[] nums) {
        if (nums.length == 1)
            return nums[0];
        int max = nums[0];

        for (int i = 0; i < nums.length; i++) {
            int sum = 0;
            for (int j = i; j < nums.length; j++) {
                sum += nums[j]; //sum is that of A[i..j]
                if (sum > max) {
                    max = sum;
                }
            }
        }
        return max;
    }

    public int[] exclusiveTime(int n, List<String> logs) throws Exception {
        Stack<Integer> stack = new Stack<>();
        int[] times = new int[n];
        int prev = 0;
        for (int i = 0; i < logs.size(); i++) {
            String[] split = logs.get(i).split(":");
            int pId = Integer.parseInt(split[0]);
            String op = split[1];
            prev = Integer.parseInt(split[2]);
            if (op.equalsIgnoreCase("start")) {
                if(!stack.isEmpty()) {
                    times[stack.peek()] = prev - times[stack.peek()];
                }
                stack.push(pId);
                continue;
            }
            if (op.equalsIgnoreCase("end")){
             // TODO
                continue;
            }
        }

        return times;
    }

    public void nextPermutation(int[] nums) {
        int j = 0;
       for (int i = nums.length -1; i > 0; i-- ) {
           if (nums[i] > nums[i -1]) {
               // found non-decreasing
               j = i-1;
               for (int k = i+1; k < nums.length; k++) {
                   if (nums[k] > nums[j]) {
                       int temp = nums[k];
                       nums[k] = nums[j];
                       nums[j] = temp;
                       break;
                   }
               }
               break;
           }
       }

       // reverse the remaining length
        int arrLength = (nums.length - j)/2 + j;
        for (int k = j; k < arrLength; k++) {
           int temp = nums[k];
            nums[k] = nums[nums.length-k-1];
            nums[nums.length-k-1] = temp;
        }
    }
}
