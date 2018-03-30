import java.util.HashMap;

public class driver {

    public static void main(String[] args) {

        Examples e = new Examples();
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

/*
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
