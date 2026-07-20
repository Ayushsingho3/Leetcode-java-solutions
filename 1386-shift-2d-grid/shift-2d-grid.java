import java.util.*;

class Solution {
    public List<List<Integer>> shiftGrid(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;
        int total = m * n;
        k %= total;

        int[][] result = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int originalIndex = i * n + j;
                int newIndex = (originalIndex + k) % total;
                result[newIndex / n][newIndex % n] = grid[i][j];
            }
        }

        List<List<Integer>> output = new ArrayList<>();
        for (int[] row : result) {
            List<Integer> listRow = new ArrayList<>();
            for (int val : row) {
                listRow.add(val);
            }
            output.add(listRow);
        }
        return output;
    }
}