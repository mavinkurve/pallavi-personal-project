import java.util.*;

class Solution {

    int[] original;
    int[] current;
    Random random;

    public Solution(int[] nums) {
        original = nums;
        current = new int[original.length];
        for (int i = 0; i < original.length; i++) {
            current[i] = original[i];
        }
        random = new Random();
    }

    /** Resets the array to its original configuration and return it. */
    public int[] reset() {
        for (int i = 0; i < original.length; i++) {
            current[i] = original[i];
        }
        return original;
    }

    /** Returns a random shuffling of the array. */
    public int[] shuffle() {
        HashSet<Integer> visited = new HashSet<>();
        for(int i = 0; i < current.length; i++) {
            if (!visited.contains(i)) {
                int rand = random.nextInt(i+1);
                int temp = current[rand];
                current[rand] = current[i];
                current[i] = temp;
                visited.add(i);
                visited.add(rand);
            }
        }
        return current;
    }
}
