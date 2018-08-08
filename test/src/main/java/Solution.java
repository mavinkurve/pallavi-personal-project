public class Solution extends GuessGame {
    public Solution(int a) {
        super(a);
    }

    public int guessNumber(int n) {
       return guessNumber(0,n);
    }

    public int guessNumber(int start, int end) {
        if (guess(start) == 0)
            return start;
        if (guess(end) == 0)
            return end;
        int mid = start + (end-start)/2;
        int gMid = guess(mid);
        if (gMid == 0)
            return mid;
        if (gMid < 0)
            return guessNumber(start,mid);
        return guessNumber(mid+1, end);


    }
}