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

        //TreeNode root = TreeNode.stringToTreeNode("[-3,-4,0,null,null,-2,1]");
        //p(ap.lemonadeChange(new int[]{5,5,10,10,20}));

        int[][] image = new int[2][3];
        image[0] = new int[]{0,0,0};
        image[1] = new int[]{0,0,0};

        p(ap.removeElement(new int[]{0,1,2,2,3,0,4,2},2));

     }

    private static void p(Object s) {
        System.out.println(s);
    }

}
