public class ArrayProblems {

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
}
