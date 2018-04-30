public class LeetcodeEasy {

    public boolean judgeCircle(String moves) {

        int[] position = new int[]{0,0};
        moves = moves.toLowerCase();
        for(int i = 0; i < moves.length(); i++) {
            switch(moves.charAt(i)) {
                case 'l':
                    position[0] = position[0] + 1;
                    continue;
                case 'r':
                    position[0] = position[0] - 1;
                    continue;
                case 'u':
                    position[1] = position[1] + 1;
                    continue;
                case 'd':
                    position[1] = position[1] - 1;
                    continue;
                default:
                    return false;
            }
        }
        return (position[0] == 0 && position[1] == 0);
    }
}
