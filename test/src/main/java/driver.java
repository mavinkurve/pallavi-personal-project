import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

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

        p("find target range " + Arrays.toString(ap.searchRange(new int[]{5,7,7,8,8,10},6)));

    }

    private static void p(String s) {
        System.out.println(s);
    }



}
