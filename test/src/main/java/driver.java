public class driver {

    public static void main(String[] args) {
        p("Hello World!");

        examples e = new examples();

        p("insert in sorted array: " + e.insertInSortedArray(new int[]{1,3,5,6},5));
    }

    private static void p(String s) {
        System.out.println(s);
    }



}
