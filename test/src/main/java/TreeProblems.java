import java.util.*;

public class TreeProblems {

    public int sumOfLeftLeaves(TreeNode root) {
        int sum = 0;
        if (root.left != null && (root.left.left == null))
            sum += root.left.val;
        if (root.left != null)
            sum += sumOfLeftLeaves(root.left);
        if (root.right != null)
            sum += sumOfLeftLeaves(root.right);
        return sum;
    }

    public int[] findFrequentTreeSum(TreeNode root) {
        if (root == null)
            return new int[]{};
        HashMap<Integer, Integer> sums = new HashMap<>();
        subTreeSum(root, sums);
        int maxFreq = 0;
        List<Integer> maxSums = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : sums.entrySet()) {
            if (entry.getValue() == maxFreq) {
                maxSums.add(entry.getKey());
                continue;
            }
            if (entry.getValue() > maxFreq) {
                maxSums.clear();
                maxFreq = entry.getValue();
                maxSums.add(entry.getKey());
            }
        }
        return maxSums.stream()
                .filter(Objects::nonNull)
                .mapToInt(Integer::intValue)
                .toArray();
    }

    public int subTreeSum(TreeNode root, HashMap<Integer,Integer> sums) {
        int sum = root.val;
        if (root.left != null)
            sum += subTreeSum(root.left,sums);
        if (root.right != null)
            sum+= subTreeSum(root.right,sums);
        if (sums.containsKey(sum))
            sums.put(sum,sums.get(sum) + 1);
        else
            sums.put(sum,1);
        return sum;
    }

    private HashSet<Integer> depthFirstSearch(TreeNode root, HashSet<Integer> values) {
        if (root != null) {
            values.add(root.val);
            depthFirstSearch(root.left, values);
            depthFirstSearch(root.right, values);
        }
        return values;
    }

    public int findSecondMinimumValue(TreeNode root) {

        HashSet<Integer> values = new HashSet<>();
        values = depthFirstSearch(root,values);
        int min = root.val;
        long secondMin = Long.MAX_VALUE;

        for (Integer v : values) {
            if (min < v && v < secondMin)
                secondMin = v;
        }
        return secondMin < Long.MAX_VALUE ? (int) secondMin : -1;
    }

}
