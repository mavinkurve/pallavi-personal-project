import java.util.*;

public class driver {

    public static void main(String[] args) {

        StringProblems sp = new StringProblems();
        Practice2 p = new Practice2();
        MathProblems mp = new MathProblems();
        GraphProblems gp = new GraphProblems();
        ArrayProblems ap = new ArrayProblems();
        TreeProblems tp = new TreeProblems();
        BitOps bo = new BitOps();
        ListProblems lp = new ListProblems();
        LeetcodeEasy ep = new LeetcodeEasy();
        IntervalProblems ip = new IntervalProblems();

        TreeNode root1 = new TreeNode(1,3,2);
        root1.left.left = new TreeNode(5);

        TreeNode root2 = new TreeNode(2,1,3);
        root2.left.right = new TreeNode(4);
        root2.right.right = new TreeNode(7);

        p(sp.longestPalindrome("babad"));

    }

    private static void p(Object s) {
        System.out.println(s);
    }

}
