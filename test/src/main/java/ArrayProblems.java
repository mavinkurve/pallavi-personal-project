import org.omg.PortableInterceptor.INACTIVE;

import java.util.*;

public class ArrayProblems {

    public void rotateArray(int[] nums, int k) {
        if (k > nums.length)
            return;
        System.out.println("Original array: " + Arrays.toString(nums));

        reverseArray(nums,0,nums.length -1);
        reverseArray(nums,0,k-1);
        reverseArray(nums,k, nums.length-1);

        System.out.println("Rotated array: " + Arrays.toString(nums));
        return;
    }

    private void reverseArray(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }

    public int findTarget(int[] nums, int start, int end, int target) {
        int mid = start + ((end - start) / 2);
        if (nums[mid] == target)
            return mid;
        if ((start == end) && target != nums[start])
            return -1;
        if (nums[mid] > target)
            return findTarget(nums,start,mid,target);
        return findTarget(nums,mid+1, end, target);
    }

    public int[] searchRange(int[] nums, int target) {
        int[] range = new int[] {-1,-1};
        if (nums.length < 1)
            return range;
        if (nums.length == 1 & target == nums[0]) {
            Arrays.fill(range,0);
            return range;
        }

        int t = findTarget(nums,0,nums.length-1, target);
        if (t == -1)
            return range;
        int start = t;
        int end = t;
        while (start >=0 && nums[start] == target) {
            range[0] = start;
            start--;
        }
        while (end < nums.length && nums[end] == target) {
            range[1] = end;
            end++;
        }
        return range;
    }

    public List<Integer> countSmaller(int[] nums) {
        List<Integer> counts = new ArrayList<>();

        return counts;
    }

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        HashSet<List<Integer>> subsets = new HashSet<>();
        double pow_set_size = Math.pow(2, nums.length);
        int counter, j;

        for(counter = 0; counter < pow_set_size; counter++)
        {
            List<Integer> subset = new ArrayList<>();
            for(j = 0; j < nums.length; j++)
            {
          /* Check if jth bit in the counter is set
             If set then add jth element from set */
                if((counter & (1<<j)) != 0)
                    subset.add(nums[j]);
            }
            subsets.add(subset);
        }

        return new ArrayList(subsets);
    }

    public boolean isPossible(int[] nums) {
        List<HashSet<Integer>> options = new ArrayList<>();
        HashSet<Integer> list = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            list.add(nums[i]);
            if (list.size() >= 3 && nums[i+1] == nums[i])
            {
                options.add(list);
                list = new HashSet<>();
            }
        }
        return (!options.isEmpty() && options.get(options.size() - 1).contains(nums[nums.length-1]));
    }

    public int[] asteroidCollision(int[] asteroids) {
        Stack<Integer> stack = new Stack<Integer>();
        for (int i = 0; i < asteroids.length; i++) {
            if (stack.isEmpty()) {
                stack.push(asteroids[i]);
                continue;
            }
            if (stack.peek() > 0) {         // stack positive
                if (asteroids[i] >= 0) {       // asteroid positive
                    stack.push(asteroids[i]);
                    continue;
                }
                int a = Math.abs(asteroids[i]);     // asteroid negative
                while (true) {
                    if (stack.isEmpty() || stack.peek() < 0) {
                        stack.push(asteroids[i]);
                        break;
                    }
                    // collision
                    int top = stack.pop();
                    if (top == a)
                        break;   // both explode, nothing pushed to stack
                    if (top > a) {
                        stack.push(top);   // a explodes, top pushed back on stack
                        break;
                    }
                    if (top < a) {
                        continue;   // top explodes, keep popping stack
                    }
                }
            } else {      // stack negative
                stack.push(asteroids[i]);
            }
        }
        int[] answer = new int[stack.size()];
        for (int i = stack.size()-1; i >=0; i--) {
            answer[i] = stack.pop();
        }
        return answer;
    }

    public int findMin(int[] nums) {
        return findMinInRotatedArray(nums,0,nums.length-1);
    }

    public int findMinInRotatedArray(int[] nums, int start, int end) {
        if (nums.length == 1)
            return nums[0];
        int mid = start + ((end - start) / 2);
        if (nums[mid] > nums[mid + 1])
            return nums[mid+1];
        if (nums[start] > nums[mid])
            return findMinInRotatedArray(nums,start,mid);
        if (nums[mid+1] > nums[end])
            return findMinInRotatedArray(nums,mid+1,end);
        else // array not rotated
            return nums[start];
    }

    public int removeDuplicates(int[] nums) {
        if (nums.length <= 1)
            return nums.length;

        int length = nums.length;
        for (int i =0; i < length-1; i++) {
            if (nums[i] == nums[i+1]) {
                for (int j = i; j < length -1; j++) {
                    nums[j] = nums[j + 1];
                }
                length--;
                i--;
            }
        }

        return length;

    }


}
