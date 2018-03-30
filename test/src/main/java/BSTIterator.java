import java.util.Stack;

public class BSTIterator {

    Stack<TreeNode> stack;

    public BSTIterator(TreeNode root) {
        stack = new Stack<TreeNode>();
        if (root != null) {
            stack.push(root);
            TreeNode current = root;
            while (current != null) {
                if (current.left != null) stack.push(current.left);
                current = current.left;
            }
        }
    }

    /* return if we have next smallest number */
    public boolean hasNext() {
        return !(stack.isEmpty());
    }

    /* return next smallest number */
    public int next() {
        while (stack.peek() != null && stack.peek().left != null) {
            stack.push(stack.peek().left);
        }
        TreeNode next = stack.pop();

        if (!stack.isEmpty() && stack.peek().left == next)
            stack.peek().left = null;

        if (next.right != null) {
            stack.push(next.right);
        }
        return next.val;
    }
}
