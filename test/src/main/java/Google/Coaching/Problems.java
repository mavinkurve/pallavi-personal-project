package Google.Coaching;

public class Problems {

    public static int searchInRotatedArray(int[] array, int element) {
        return search(array,element,0,array.length-1);
    }

    public static int search(int[] array, int target, int start, int end) {
        if (start > end)
            return -1;      // target not found
        if (array[start] == target)
            return start;
        if (array[end] == target)
            return end;
        int mid = start + ((end - start) / 2);
        if (array[mid] == target)
            return mid;

        if (array[mid] > target) {
            if (array[start] < target)
                return search(array, target, start, mid);
            if (array[end] > target)
                return search(array, target, mid + 1, end);
        }
        if (array[start] > target && array[end] < target)
            return search(array, target, start, mid);
        return search(array, target, mid + 1, end);
    }


    public static void main(String[] args) {
        int[] array = new int[]{5,4,3,2,1};
        System.out.println(searchInRotatedArray(array,4));
/*

        int bitmap[][] = {
                {0,1,0,1},
                {1,1,1,0},
                {0,1,0,0},
                {0,1,1,0}
        };
        for (int i = 0; i < bitmap.length; i++) {
            for (int j = 0; j < bitmap[0].length; j++) {
                System.out.print(bitmap[i][j] + " ");
            }
            System.out.println("");
        }
        System.out.println("Floodfill");

        floodFill(bitmap,0,3);

        for (int i = 0; i < bitmap.length; i++) {
            for (int j = 0; j < bitmap[0].length; j++) {
                System.out.print(bitmap[i][j] + " ");
            }
            System.out.println("");
        }
        */
    }

    public static void floodFill(int[][] bitmap, int row, int col) {
        // check for invalid input
        if (row >= bitmap.length || col >= bitmap[0].length || row < 0 || col < 0)
            return;

        // check what should be the desired value
        int desiredValue = 1 - bitmap[row][col];
        setValue(bitmap,row,col,desiredValue);
    }

    public static void setValue(int[][] bitmap, int row, int col, int value) {
        // check for invalid input
        if (row >= bitmap.length || col >= bitmap[0].length || row < 0 || col < 0)
            return;
        if (bitmap[row][col] == value)
            return;
        bitmap[row][col] = value;
        setValue(bitmap,row+1,col,value);
        setValue(bitmap,row,col+1,value);
        setValue(bitmap,row-1,col,value);
        setValue(bitmap,row,col-1,value);
    }
}
