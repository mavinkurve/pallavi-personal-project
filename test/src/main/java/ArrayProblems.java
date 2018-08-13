import java.util.*;

public class ArrayProblems {
    public int removeElement(int[] nums, int val) {
        int last = nums.length - 1;
        int begin = 0;
        int size = nums.length;
        while(begin < last) {
            if (nums[begin] == val) {
                while (nums[last] == val) {
                    last--;
                    size--;
                }
                nums[begin] = nums[last];
                size--;
                last--;
            }
            begin++;
        }
        return size;
    }

    public int[] twoSum(int[] numbers, int target) {
        int start = 0;
        int end = numbers.length-1;
        while(start < end) {
            int sum = numbers[start] + numbers[end];
            if (sum == target)
                return new int[]{start+1,end+1};
            if (sum > target)
                end--;
            if (sum < target)
                start++;
        }
        return new int[]{-1,-1};
    }

    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        System.out.println("image length: " + image.length);
        System.out.println("image[0] length: " + image[0].length);
        fillHelper(image,sr,sc,image[sr][sc],newColor);
        return image;
    }

    private void fillHelper(int[][] image, int i, int j, int oldC, int newC){
        if (i < 0 || j < 0 || i >= image.length || j >= image[0].length)
            return;
        System.out.println("i: " + i + " j: " + j);
        if (image[i][j] != oldC)
            return;
        image[i][j] = newC;
        fillHelper(image,i+1,j,oldC,newC);
        fillHelper(image,i,j-1,oldC,newC);
        fillHelper(image,i,j+1,oldC,newC);
        fillHelper(image,i-1,j,oldC,newC);
    }

    public boolean lemonadeChange(int[] bills) {
        Map<Integer, Integer> cash = new HashMap<>();
        for (int i = 0; i < bills.length; i++) {
            switch (bills[i]) {
                case 5:
                    cash.put(5, cash.getOrDefault(5, 0) + 1);
                    break;
                case 10:
                    cash.put(10, cash.getOrDefault(10, 0) + 1);
                    if (cash.getOrDefault(5, 0) < 1)
                        return false;
                    cash.put(5, cash.get(5) - 1);
                    break;
                case 20:
                    cash.put(20, cash.getOrDefault(20, 0) + 1);
                    int tens = cash.getOrDefault(10, 0);
                    int fives = cash.getOrDefault(5, 0);
                    if (tens >= 1 && fives >= 1) {
                        cash.put(10, tens - 1);
                        cash.put(5, fives - 1);
                        break;
                    }
                    if (fives >= 3)
                        cash.put(5, fives - 3);
                    break;
                default:
                    return false;
            }
        }
        return true;
    }

    public int arrayNesting(int[] nums) {
        int max = 0;
        Set<Integer> visited = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (visited.contains(nums[i]))
                continue;
            int length = 0;
            int current = nums[i];
            do {
                visited.add(current);
                length++;
                current = nums[current];
            }while (current!=nums[i]);
            if (length > max)
                max = length;
        }
        return max;
    }

    public int longestMountain(int[] A) {
        List<Integer> peaks = new ArrayList<>();
        int longestMountain = 0;
        for (int i = 1; i < A.length-1; i++) {
            if (A[i] > A[i+1] && A[i] > A[i-1])
                peaks.add(i);
        }
        for (int i = 0; i < peaks.size(); i++) {
            int peak = peaks.get(i);
            int left = peak;
            int right = peak;
            int mountain = 1;
            while (left-1 >= 0 && A[left - 1] < A[left]) {
                mountain++;
                left--;
            }
            while (right+1 < A.length && A[right+1] < A[right] ) {
                mountain++;
                right++;
            }
            if (mountain > longestMountain)
                longestMountain = mountain;
        }
        return longestMountain;
    }

    public int threeSumClosest(int[] num, int target) {
        int result = num[0] + num[1] + num[num.length - 1];
        Arrays.sort(num);
        for (int i = 0; i < num.length - 2; i++) {
            int start = i + 1, end = num.length - 1;
            while (start < end) {
                int sum = num[i] + num[start] + num[end];
                if (sum > target) {
                    end--;
                } else {
                    start++;
                }
                if (Math.abs(sum - target) < Math.abs(result - target)) {
                    result = sum;
                }
            }
        }
        return result;
    }

    public int numMagicSquaresInside(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        if (rows < 3 || cols < 3)
            return 0;
        int magicSquares = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (isMagicGrid(grid,i,j))
                    magicSquares++;
            }
        }
        return magicSquares;
    }

    public boolean isMagicGrid(int[][] grid, int r, int c) {
        int rows = grid.length;
        int cols = grid[0].length;
        if (rows - r < 3 || cols - c < 3)
            return false;
        List<Integer> list = new ArrayList<>();
        for (int i = r; i < r+3; i++) {
            for (int j = c; j < c+3; j++){
                list.add(grid[i][j]);
            }
        }
        // check if all 9 numbers are present
        int sum = 0;
        for (int i = 0; i < list.size(); i++) {
            sum += i;
        }
        if (sum != 45)
            return false;

        // check row sum
        int rowSum1 = list.get(0) + list.get(1) + list.get(2);
        int rowSum2 = list.get(3) + list.get(4) + list.get(5);
        int rowSum3 = list.get(6) + list.get(7) + list.get(8);
        if (rowSum1 != rowSum2)
            return false;
        if (rowSum2 != rowSum3)
            return false;


        return true;
    }

    public int[] intersect(int[] nums1, int[] nums2) {
        List<Integer> answer = new ArrayList<>();
        Map<Integer,Integer> map = new HashMap<>();
        if (nums1.length > nums2.length) {
            for (int i = 0; i < nums1.length; i++) {
                map.put(nums1[i],map.getOrDefault(nums1[i],0) + 1);
            }

            for (int i = 0; i < nums2.length; i++) {
                if (map.getOrDefault(nums2[i],0) > 0) {
                    answer.add(nums2[i]);
                    map.put(nums2[i],map.get(nums2[i]) - 1);
                }
            }
        } else {
            for (int i = 0; i < nums2.length; i++) {
                map.put(nums2[i],map.getOrDefault(nums2[i],0) + 1);
            }
            for (int i = 0; i < nums1.length; i++) {
                if (map.getOrDefault(nums1[i],0) > 0) {
                    answer.add(nums1[i]);
                    map.put(nums1[i],map.get(nums1[i]) - 1);
                }
            }
        }
        return answer.stream().mapToInt(i->i).toArray();
    }

    public boolean containsDuplicate(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (set.contains(nums[i]))
                return true;
            set.add(nums[i]);
        }
        return false;
    }

    public void rotate(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;



    }

    private void reverse(int[][] matrix) {

    }


    public boolean wordBreak(String s, List<String> wordDict) {
        if (s.length() == 0)
            return true;
        int start = 0;
        int end = 0;
        while (start <= s.length() && end <= s.length()) {
            if (wordDict.contains(s.substring(start, end))) {
                if (wordBreak(s.substring(end, s.length()), wordDict)) {
                    return true;
                }
            }
            end++;
        }
        return (start == s.length());
    }

    public int[] getModifiedArray(int length, int[][] updates) {
        int[] array = new int[length];
        Arrays.fill(array,0);
        for (int i = 0; i < updates.length; i++) {
            int start = updates[i][0];
            int end = updates[i][1];
            int inc = updates[i][2];
            for (int j = start; j <= end; j++)
                array[j] += inc;
        }
        return array;
    }

    public int arrayPairSum(int[] nums) {
        if (nums.length == 0)
            return 0;
        if (nums.length % 2 != 0)
            return 0;
        Arrays.sort(nums);
        int sum = 0;
        for (int  i = 0; i < nums.length; i+=2) {
            sum += nums[i];
        }
        return sum;
    }

    public List<String> findMissingRanges(int[] nums, int lower, int upper) {
        int current = lower;
        List<String> missing = new ArrayList<>();
        for (int i=0; i< nums.length; i++) {
            if (nums[i] > current) {
                int first = current;
                int last = nums[i] -1;
                StringBuilder sb = new StringBuilder();
                sb.append(first);
                if (first != last) {
                    sb.append("->");
                    sb.append(last);
                }
                missing.add(sb.toString());
            }
            current = nums[i] + 1;
        }
        if (current != upper + 1){
            int first = current;
            int last = upper;
            StringBuilder sb = new StringBuilder();
            sb.append(first);
            if (first != last) {
                sb.append("->");
                sb.append(last);
            }
            missing.add(sb.toString());
        }

        return missing;
    }

    public List<Integer> topKFrequent(int[] nums, int k) {
        List<Integer> answer = new ArrayList<>();
        PriorityQueue<Integer> queue = new PriorityQueue<>(k);
        Map<Integer,Integer> map = new HashMap<>();
        Map<Integer,Integer> reverseMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (queue.size() < k) {
                queue.offer(entry.getValue());
                answer.add(entry.getKey());
                continue;
            }
        }
        return answer;
    }

    public int maxSubArray(int[] nums) {
        if (nums.length < 1)
            return 0;
        int current = nums[0];
        int max = current;

        for (int j = 1; j < nums.length;j++) {
            if (nums[j] > (current + nums[j])) {
                current = nums[j];
            }
            else
                current += nums[j];
            max = Math.max(max,current);
        }
        return max;
    }

    public int singleNumber(int[] nums) {
        int ans=0;
        for(int i=0;i<nums.length;i++)
            ans=ans^nums[i];

        return ans;
    }

    public int[] productExceptSelf(int[] nums) {

        int[] product = new int[nums.length];
        int total = 1;
        for (int i = 0; i < nums.length; i++) {
            total *= nums[i];
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0 || nums[i] == 1)
                product[i] = total;
            else
                product[i] = performDivision(total,nums[i]);
        }
        return product;
    }

    private Integer performDivision(int a, int b) {
        if (a < b)
            return 0;
        if (b == 0)
            return null;
        int div = 1;
        while (true) {
            a = a - b;
            if (a == 0 || a < b)
                return div;
            div++;
        }
    }

    public List<List<Integer>> threeSum(int[] nums) {
        HashSet<List<Integer>> set = new HashSet<>();
        Arrays.sort(nums);
        for (int i = 0; i< nums.length; i++) {
            int lo = i+1, hi = nums.length-1, sum = 0 - nums[i];
            while (lo < hi) {
                if (nums[lo] + nums[hi] == sum) {
                    set.add(Arrays.asList(nums[i], nums[lo], nums[hi]));
                    lo++;
                    hi--;
                } else {
                    if (nums[lo] + nums[hi] < sum)
                        lo++;
                    else hi--;
                }
            }
        }

        return new ArrayList<>(set);
    }

    public boolean findTarget(int[] nums, int start, int end, int target) {
        if (start >= nums.length || nums[start] > target)
            return false;
        int mid = start + (end-start)/2;
        if (nums[mid] == target)
            return true;
        if (nums[mid] < target)
            return findTarget(nums,mid+1,end,target);
        else
            return findTarget(nums,start,mid,target);
    }
    public int numIslands(char[][] grid) {
        if (grid.length == 0)
            return 0;
        int islands = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    islands++;
                    checkNeighbors(grid,i,j);
                }
            }
        }
        return islands;
    }

    // collapse neighbors
    public void checkNeighbors(char[][] grid, int i, int j) {
        if (grid[i][j] == 0)
            return;
        else {
            grid[i][j] = 0;     // set to 0 before returning
            if (i+1 < grid.length)
                checkNeighbors(grid,i+1, j);
            if (j+1 < grid[0].length)
                checkNeighbors(grid, i, j+1);
            if (i-1 >= 0)
                checkNeighbors(grid,i-1,j);
            if (j-1 >= 0)
                checkNeighbors(grid,i,j-1);
            return;
        }
    }

    public List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> answer = new ArrayList<>();
        if (nums.length == 0)
            return answer;
        int current, next;
        for (int i = 0; i < nums.length; i++) {
            current = nums[i];
            nums[i] = -1;
            do{
                next = nums[current - 1];
                nums[current - 1] = current;
                current = next;
            }while (current != -1 && current != nums[current - 1] );
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == -1)
                answer.add(i+1);
        }
        return answer;
    }


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
/*
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
*/
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
