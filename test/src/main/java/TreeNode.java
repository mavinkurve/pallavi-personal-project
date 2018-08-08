public class TreeNode {

    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
        left = null;
        right = null;
    }

    TreeNode(int x, int l, int r){
        val = x;
        left = new TreeNode(l);
        right = new TreeNode(r);

    }
}
