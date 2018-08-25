import com.sun.tools.javac.util.Assert;

import java.util.*;

public class driver {
    enum Color {
        red,
        green,
        blue
    }

    static int countDuplicates(int[] numbers) {
        Set<Integer> dupes = new HashSet<>();
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < numbers.length; i++) {
            if (set.contains(numbers[i]))
                dupes.add(numbers[i]);
            else set.add(numbers[i]);
        }
        return dupes.size();


    }

    public static void main(String[] args) {

        Color c = Color.green;

        System.out.println(c);

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

        //p(ap.findDisappearedNumbers(new int[]{1,2,4,5,7}));
    }

    private static void p(Object s) {
        System.out.println(s);
    }

}
