import java.util.*;
import java.util.stream.IntStream;

public class Practice2 {

    public static Boolean isIntegerPalindrome(int x) {
        Integer xInt = (Integer) x;
        StringBuilder sb = new StringBuilder(xInt.toString());
        StringBuilder reverse = sb.reverse();
        return reverse.toString().equalsIgnoreCase(xInt.toString());
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
