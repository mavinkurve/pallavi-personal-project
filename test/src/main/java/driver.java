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
        LeetcodeEasy ep = new LeetcodeEasy();

        p("Is power of 1: " + mp.isPowerOf3(1));

        /*
        MinStack obj = new MinStack();
        obj.push(-2);
        obj.push(0);7
        obj.push(-3);
        obj.push(5);
        p("top: " + obj.top());
        p("Min: " + obj.getMin());
        obj.pop();
        p("After popping: ");
        p("top: " + obj.top());
        p("Min: " + obj.getMin());
        p("insert bigger value");
        obj.push(7);
        obj.push(10);
        p("top: " + obj.top());
        p("Min: " + obj.getMin());
        p("insert smaller value");
        obj.push(-7);
        obj.push(-10);
        p("top: " + obj.top());
        p("Min: " + obj.getMin());
        */

    }

    private static void p(String s) {
        System.out.println(s);
    }



}
