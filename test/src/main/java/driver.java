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

        int matrix[][] = {
                {1,2,3,4},
                {5,6,7,8},
                {9,10,11,12}
        };


        ap.productExceptSelf(new int[]{1,2,3,4});
    }

    private static void p(String s) {
        System.out.println(s);
    }



}
