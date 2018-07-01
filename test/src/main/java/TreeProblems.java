
import java.util.*;

public class TreeProblems {
    public int findBottomLeftValue(TreeNode root) {
        return findBottomLeftValue(root, 1, new int[]{0, 0});
    }

    public int findBottomLeftValue(TreeNode root, int depth, int[] res) {
        if (res[1] < depth) {
            res[0] = root.val;
            res[1] = depth;
        }
        if (root.left != null) findBottomLeftValue(root.left, depth + 1, res);
        if (root.right != null) findBottomLeftValue(root.right, depth + 1, res);
        return res[0];
    }


    public TreeNode sortedArrayToBST(int[] nums) {
        return getTree(nums, 0, nums.length - 1);
    }

    private TreeNode getTree(int[] nums, int lo, int hi) {
        if (lo > hi)
            return null;
        int mid = lo + (hi - lo) / 2;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = getTree(nums, lo, mid - 1);
        root.right = getTree(nums, mid + 1, hi);
        return root;
    }

    public void connect(TreeLinkNode root) {
        Queue<TreeLinkNode> queue = new LinkedList<>();
        List<List<TreeLinkNode>> levels = new ArrayList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            List<TreeLinkNode> currentLevel = new ArrayList<>();
            TreeLinkNode current = queue.poll();
            currentLevel.add(current);
            if (current.left != null)
                queue.add(current.left);
        }

    }


    public List<Integer> largestValues(TreeNode root) {
        List<Integer> bfsNodes = new ArrayList<>();
        Queue<Map.Entry<TreeNode, Integer>> queue = new LinkedList<>();
        Map<Integer, List<Integer>> levelMap = new HashMap<>();
        queue.offer(new AbstractMap.SimpleEntry(root, 0));
        levelMap.put(0, Arrays.asList(root.val));
        while (!queue.isEmpty()) {
            Map.Entry<TreeNode, Integer> current = queue.poll();

            List<Integer> nodesAtLevel = new ArrayList<>();
            if (levelMap.containsKey(current.getValue())) {
                nodesAtLevel.addAll(levelMap.get(current.getValue()));
            }
            nodesAtLevel.add(current.getKey().val);
            levelMap.put(current.getValue(), nodesAtLevel);
            if (current.getKey().left != null)
                queue.offer(new AbstractMap.SimpleEntry(current.getKey().left, current.getValue() + 1));
            if (current.getKey().right != null)
                queue.offer(new AbstractMap.SimpleEntry(current.getKey().right, current.getValue() + 1));
        }

        for (List<Integer> list : levelMap.values()) {
            int max = Integer.MIN_VALUE;
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i) > max)
                    max = list.get(i);
            }
            bfsNodes.add(max);
        }
        System.out.println(Arrays.toString(bfsNodes.toArray()));
        return bfsNodes;
    }

    public List<List<Integer>> findLeaves(TreeNode root) {
        List<List<Integer>> allLeaves = new ArrayList();
        if (root == null)
            return allLeaves;
        while (root.left != null || root.right != null) {
            List<Integer> leaves = new ArrayList<>();
            leaves = getLeaves(root, leaves);
            allLeaves.add(leaves);
        }
        allLeaves.add(Arrays.asList(root.val));
        return allLeaves;
    }

    private List<Integer> getLeaves(TreeNode root, List<Integer> leaves) {
        if (root == null)
            return leaves;
        if (isLeaf(root.left)) {
            leaves.add(root.left.val);
            root.left = null;
        }
        if (isLeaf(root.right)) {
            leaves.add(root.right.val);
            root.right = null;
        }
        getLeaves(root.left, leaves);
        getLeaves(root.right, leaves);
        return leaves;
    }

    private Boolean isLeaf(TreeNode leaf) {
        if (leaf == null)
            return false;
        return (leaf.left == null && leaf.right == null);
    }


    public TreeNode pruneTree(TreeNode root) {
        if (numberOfOnes(root) == 0)
            return null;
        return root;
    }

    private int numberOfOnes(TreeNode root) {
        if (root == null)
            return 0;
        int leftOnes = numberOfOnes(root.left);
        if (leftOnes == 0)
            root.left = null;
        int rightOnes = numberOfOnes(root.right);
        if (rightOnes == 0)
            root.right = null;
        int ones = (root.val == 1) ? 1 : 0;
        return leftOnes + rightOnes + ones;
    }

    public TreeNode constructMaximumBinaryTree(int[] nums) {
        TreeNode root = constructTree(nums, 0, nums.length - 1);
        return root;
    }

    private TreeNode constructTree(int[] nums, int lo, int hi) {
        if (hi < lo)
            return null;
        int max = lo;
        for (int i = lo; i <= hi; i++) {
            if (nums[max] < nums[i])
                max = i;
        }
        TreeNode root = new TreeNode(nums[max]);
        root.left = constructTree(nums, lo, max - 1);
        root.right = constructTree(nums, max + 1, hi);
        return root;
    }

    public TreeNode invertTree(TreeNode root) {
        if (root == null)
            return null;
        invertTree(root.left);
        invertTree(root.right);
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
        return root;
    }

    public int maxDepth(TreeNode root) {
        return getDepth(root, 0, 0);
    }

    public int getDepth(TreeNode root, int parentDepth, int maxDepth) {
        if (root != null) {
            int currentDepth = parentDepth + 1;
            if (root.left != null)
                maxDepth = Math.max(maxDepth, getDepth(root.left, currentDepth, maxDepth));
            if (root.right != null)
                maxDepth = Math.max(maxDepth, getDepth(root.right, currentDepth, maxDepth));
            return Math.max(maxDepth, currentDepth);
        }
        return maxDepth;
    }

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

    public int subTreeSum(TreeNode root, HashMap<Integer, Integer> sums) {
        int sum = root.val;
        if (root.left != null)
            sum += subTreeSum(root.left, sums);
        if (root.right != null)
            sum += subTreeSum(root.right, sums);
        if (sums.containsKey(sum))
            sums.put(sum, sums.get(sum) + 1);
        else
            sums.put(sum, 1);
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
        values = depthFirstSearch(root, values);
        int min = root.val;
        long secondMin = Long.MAX_VALUE;

        for (Integer v : values) {
            if (min < v && v < secondMin)
                secondMin = v;
        }
        return secondMin < Long.MAX_VALUE ? (int) secondMin : -1;
    }

}
