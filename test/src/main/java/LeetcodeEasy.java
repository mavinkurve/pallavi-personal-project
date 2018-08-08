import java.util.HashMap;

public class LeetcodeEasy {

    public int climbStairs(int n) {
        HashMap<Integer,Integer> memoTable = new HashMap<>();
        memoTable.put(1,1);
        memoTable.put(2,2);
        return getStairs(n, memoTable);
    }

    private int getStairs(int n, HashMap<Integer,Integer> memoTable) {
        if (memoTable.containsKey(n))
            return memoTable.get(n);
        memoTable.put(n, getStairs(n-1, memoTable) + getStairs(n-2, memoTable));
        return memoTable.get(n);
    }

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

    public int islandPerimeter(int[][] grid) {
        int perimeter = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                perimeter += validEdgeCount(grid, i , j);
            }
        }
        return perimeter;
    }

    private int validEdgeCount(int[][] grid, int x, int y) {
        if (grid[x][y] == 0)
            return 0;
        int edges = 4;
        if (x-1 >= 0 && grid[x-1][y] == 1)
            edges--;
        if (x + 1 < grid.length && grid[x+1][y] == 1)
            edges--;
        if (y-1 >= 0 && grid[x][y-1] == 1)
            edges--;
        if (y + 1 < grid.length && grid[x][y+1] == 1)
            edges--;
        return edges;
    }
}
