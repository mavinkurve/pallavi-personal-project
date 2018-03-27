public class examples {
    public int insertInSortedArray(int[] array, int n) {
        if (array.length == 0)
            return -1;

        return findInsertionPlace(array,0,array.length-1,n);
    }

    private int findInsertionPlace(int[] array, int i, int j, int n) {

        if (j <= i)
            return i;

        int mid = i + ((j - i)/2);

        if (array[mid] > n)
            return findInsertionPlace(array,i,mid,n);
        if (array[mid] < n && array[mid + 1] > n) {
            return mid + 1;
        }
        if (array[mid + 1] < n)
            return findInsertionPlace(array,mid +1, j , n);
        if (array[mid] == n || array[mid+1] == n)
            return mid + 1;
        return -1;
    }
}
