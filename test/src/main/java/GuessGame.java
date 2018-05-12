public class GuessGame {
    int answer;

    public GuessGame(int a) {
        answer = a;
    }

    int guess(int num) {
        if (num > answer)
            return -1;
        if (num < answer)
            return 1;
        return 0;
    }
}
