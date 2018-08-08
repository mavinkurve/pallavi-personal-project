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

        TreeNode root = new TreeNode(1,2,3);
        root.left.left = new TreeNode(4);
        root.right.left = new TreeNode(5);
        root.right.left.left = new TreeNode(7);
        root.right.right = new TreeNode(6);

        p(sp.findMaxForm(new String[]{"10", "0001", "111001", "1", "0"}, 5, 3));
    }

    private static void p(Object s) {
        System.out.println(s);
    }

}
