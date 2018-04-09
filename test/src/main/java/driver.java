import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class driver {

    public static void main(String[] args) {

        StringProblems sp = new StringProblems();
        Practice2 p = new Practice2();
        MathProblems mp = new MathProblems();

        ArrayProblems ap = new ArrayProblems();

        ListNode l1 = new ListNode(1);
        ListNode l2 = new ListNode(2);
        ListNode l3 = new ListNode(4);
        ListNode l4 = new ListNode(8);

        ListNode m1 = new ListNode(2);
        ListNode m2 = new ListNode(3);
        ListNode m3 = new ListNode(5);

        ListNode n1 = new ListNode(3);
        ListNode n2 = new ListNode(6);
        ListNode n3 = new ListNode(7);
        ListNode n4 = new ListNode(9);
        ListNode n5 = new ListNode(10);

        l1.next = l2;
        l2.next = l3;
        l3.next = l4;

        m1.next = m2;
        m2.next = m3;

        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;

        l1.print();
        n1.print();
        m1.print();

        p("sorted merge");
        final ListNode listNode = ap.mergeKLists(new ListNode[] { l1, m1, n1 });
        listNode.print();


        /*
        int[][] grid =  new int[4][4];
        grid[0] = new int []{3, 0, 8, 4};
        grid[1] = new int []{2,4,5,7};
        grid[2] = new int []{9,2,6,3};
        grid[3] = new int []{0,3,1,0};

        mp.maxIncreaseKeepingSkyline(grid);

        List<Interval> intervals = new ArrayList<>();
        intervals.add(new Interval(1,4));
        intervals.add(new Interval(2,3));


        mp.merge(intervals).forEach(i -> p(i.toString()));


        p("Pow " + mp.myPow(1.00001,123456));
        int[] nums = new int[]{1,2,3,6,4};
        System.out.print(Arrays.toString(nums) + " -> ");
        p.nextPermutation(nums);
        p(Arrays.toString(nums));

        List<String> strings = sp.generateParenthesis(0);
        strings.forEach(s -> p(s));

        p("abcabcbb : " + sp.lengthOfLongestSubstring("abcabcbb"));
        p("bbbbb : " + sp.lengthOfLongestSubstring("bbbbb"));
        p ("pwwkew : " + sp.lengthOfLongestSubstring("pwwkew"));
        p ("c : " + sp.lengthOfLongestSubstring("c"));
        p ("dvdf : " + sp.lengthOfLongestSubstring("dvdf"));


        List<String> logs = new ArrayList<>();
        logs.add("0:start:0");
        logs.add("1:start:2");
                logs.add(        "1:end:5");
                        logs.add("0:end:6");
        try {
            p.exclusiveTime(2,logs);
        } catch (Exception e) {
            e.printStackTrace();
        }


        BitOps b = new BitOps();
        p(b.getBits(5));
        p("has alternating bits " + b.hasAlternatingBits(3));

        /*
        Examples e = new Examples();

        ListNode l1 = new ListNode(1);
        ListNode l2 = new ListNode(2);
        ListNode l3 = new ListNode(3);
        ListNode l4 = new ListNode(3);
        ListNode l5 = new ListNode(4);
        ListNode l6 = new ListNode(4);
        ListNode l7 = new ListNode(5);

        l1.next = l2;
        l2.next = l3;
        l3.next = l4;
        l4.next = l5;
        l5.next = l6;
        l6.next = l7;
        e.deleteDuplicates(l1).print();
        /*
        List<Person> people = new ArrayList<>();
        people.add(new Person(7,0));
        people.add(new Person(4,4));
        people.add(new Person(7,1));
        people.add(new Person(5,0));
        people.add(new Person(6,1));
        people.add(new Person(5,2));
        List<Person> personList = e.reconstructQueue(people);
        p("Recounstr queye ");
        personList.forEach(person -> p(person.h + "," + person.k));


        p("Monotone digits: " + e.monotoneIncreasingDigits(100));

        p("content children: " +
                e.findContentChildren(new int[] {1,2},
                        new int[]{1,2,3}));


        TreeNode n1 = new TreeNode(2);

        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(5);
        n1.left = n2;
        n1.right = n3;

        TreeNode n4 = new TreeNode(5);
        TreeNode n5 = new TreeNode(7);
        n3.left = n4;
        n3.right = n5;

        p("second minimum node " + e.findSecondMinimumValue(n1));

  //      p("longest valid parenthesis: " + e.longestValidParenthesis(")(()(())("));


        TreeNode n8 = new TreeNode(8);

        TreeNode n4 = new TreeNode(4);
        TreeNode n10 = new TreeNode(10);
        n8.left = n4;
        n8.right = n10;

        TreeNode n2 = new TreeNode(2);
        TreeNode n6 = new TreeNode(6);
        n4.left = n2;
        n4.right = n6;

        TreeNode n1 = new TreeNode(1);
        TreeNode n3 = new TreeNode(3);
        n2.left = n1;
        n2.right = n3;

        TreeNode n5 = new TreeNode(5);
        TreeNode n7 = new TreeNode(7);
        n6.left = n5;
        n6.right = n7;

        TreeNode n9 = new TreeNode(9);
        TreeNode n11 = new TreeNode(11);
        n10.left = n9;
        n10.right = n11;

        BSTIterator i = new BSTIterator(null);
        System.out.println("BSTIterator: ");
        while (i.hasNext()) {
            System.out.print(i.next() + "->");
        }
        p("END");


        Examples e = new Examples();

        int badVersion = 10;
        int totalVersions = 10;
        HashMap<Integer,Boolean> versions = new HashMap<Integer, Boolean>();

        for (int i = 1; i <= totalVersions; i++) {
            Boolean bad = (i >= badVersion);
            versions.put(i,bad);
        }

        Solution s = new Solution();
        s.setVersions(versions);
        p("out of " + totalVersions + " first bad version is " +  s.firstBadVersion(totalVersions));


        ListNode l1 = new ListNode(1);
        ListNode l2 = new ListNode(2);
        ListNode l3 = new ListNode(3);
        ListNode l4 = new ListNode(4);
        l1.next = l2;
        l2.next = l3;
        l3.next = l4;
        l4.next = l3;

        p("has Cycle? " + e.hasCycle(l1));


        p("insert in sorted array: " + e.searchInsert(new int[]{1,3,5},3));
        */

    }

    private static void p(String s) {
        System.out.println(s);
    }



}
