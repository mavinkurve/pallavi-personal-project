import java.text.DecimalFormat;
import java.util.*;

public class MathProblems {

    public String fractionToDecimal(int numerator, int denominator) {
        if (denominator == 0) {
            return "0";
        }
        System.out.println(numerator - (numerator / denominator));
        double div = (double)numerator / (double) denominator;
        String[] str = String.valueOf(div).split(".");

        return String.valueOf(div);

    }

    public boolean isPowerOfTwo(int n) {
        if (n <= 0)
            return false;
        while (n >= 1 && n % 2 == 0)
            n = n / 2;
        return (n == 1);
    }

    public boolean isPowerOf3(int n) {
        //3,9,27
        if (n == 1)
            return true;

        if ((n % 3) != 0 || n == 0)
            return false;

        return isPowerOf3(n / 3);
    }

    public int[] plusOne(int[] digits) {
        if (digits.length == 0)
            return new int[]{};
        Stack<Integer> answer = new Stack<>();
        int temp = digits[digits.length-1] + 1;
        int carryOver = 0;
        if (temp > 9) {
            carryOver = 1;
            answer.push(0);
        }
        else
            answer.push(temp);
        for (int i = digits.length - 2; i >= 0; i--) {
                temp = digits[i] + carryOver;
                if (temp > 9) {
                    carryOver = 1;
                    answer.push(0);
                }
                else {
                    answer.push(temp);
                    carryOver = 0;
                }
        }
        if (carryOver > 0)
            answer.push(carryOver);

        int[] answerArray = new int[answer.size()];
        int index = 0;
        while(!answer.isEmpty()) {
                answerArray[index] = answer.pop();
                index++;
        }
        return answerArray;
    }


    public int kthSmallest(int[][] matrix, int k) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                ListNode node = new ListNode(5);
            }
        }

        return -1;
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

    public String largestNumber(int[] nums) {
        StringBuilder sb = new StringBuilder();

        return sb.toString();
    }


    HashMap<Integer,Integer> solutions = new HashMap<>();
    public int coinChange(int[] coins, int amount) {
        if (amount == 0)
            return 0;
        else {
            for (int i = 0; i < coins.length; i++) {
                if (coins[i] == amount)
                    return 1;
            }
            int min = Integer.MAX_VALUE;
            for (int i = 0; i < coins.length; i++) {
                if (amount - coins[i] < 0)
                    continue;
                if (!solutions.containsKey(amount - coins[i]))
                    solutions.put(amount - coins[i],coinChange(coins,amount - coins[i]));
                if (solutions.get(amount - coins[i]) < min && solutions.get(amount - coins[i]) != -1)
                    min = solutions.get(amount - coins[i]);
            }
            if (min < Integer.MAX_VALUE)
                return min + 1;
            return -1;
        }
    }

    public double myPow(double x, int n) {
        double result = x;
        if (n <= 0)
            return 1;
        else
            return x * myPow(x,n-1);
    }

    public int maxIncreaseKeepingSkyline(int[][] grid) {
        // compute skyline
        List<Integer> topSkyline = new ArrayList<>();
        List<Integer> leftSkyline = new ArrayList<>();
        int max = 0;
        // row max
        for (int i = 0; i < grid.length;i++) {
            for (int j = 0; j < grid[0].length; j++) {
                max = Math.max(grid[i][j],max);
            }
            leftSkyline.add(max);
            max = 0;
        }
        for (int i = 0; i < grid[0].length;i++) {
            for (int j = 0; j < grid.length; j++) {
                max = Math.max(grid[j][i],max);
            }
            topSkyline.add(max);
            max = 0;
        }
        int result = 0;
        for (int i = 0; i < grid.length;i++) {
            for (int j = 0; j < grid[0].length; j++) {
                int element = grid[i][j];
                int value = Math.min(topSkyline.get(j),leftSkyline.get(i));
                result += (value - element);
                grid[i][j] = value;
            }
        }
        return result;
    }

    public List<Integer> findDuplicates(int[] nums) {
        HashSet<Integer> duplicates = new HashSet<>();
        for (int i = 0; i < nums.length;) {
            while (nums[i] != i+1) {
                if(nums[nums[i]-1] == nums[i]) {
                    // found duplicate
                    duplicates.add(nums[i]);
                    break;
                }
                else { //swap
                    int temp = nums[nums[i]-1];
                    nums[nums[i] - 1] = nums[i];
                    nums[i] = temp;
                }
            }
            i++;
        }
        return new ArrayList(duplicates);
    }

    public boolean checkPerfectNumber(int num) {
        if (num <= 1)
            return false;
        int original = num;
        int sum = 1;
        for (int i = 2; i < num; i++) {
            if ((original % i) == 0) {
                sum += (original / i) + i;
                if (sum > original)
                    return false;
                num = original / i;
            }
        }
        return (sum == original);
    }

    public String solveEquation(String equation) {
        String infinite = "Inifinite solutions";
        String noSolution = "No solution";
        String solution = "x=";

        String left = equation.split("=")[0];
        String right = equation.split("=")[1];

        for (int i = 0; i < left.length(); i++) {
            if (left.charAt(i) == '+' || left.charAt(i) == '-') {

            }
        }

        return solution;
    }

    public int[] constructRectangle(int area) {
        int limit = area;
        int[] answer = new int[]{area, 1};

        for (int i = 2;i < limit; i++)
        {
            if (i > 3000)
                System.out.println(i);
            if ((area % i) == 0) {
                if (i > 2500)
                    System.out.println("Pair: " + i + "," + area/i);
                    if ((answer[0] - answer[1]) > Math.abs((area / i) - i)) {
                    answer[0] = Math.max(area/i,i);
                    answer[1] = Math.min(area/i,i);
                }
                limit = area / i;
            }
        }

        return answer;
    }

}

