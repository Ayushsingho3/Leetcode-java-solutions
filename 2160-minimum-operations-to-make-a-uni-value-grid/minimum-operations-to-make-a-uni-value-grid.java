import java.util.Arrays;

class Solution {
    public int minOperations(int[][] grid, int x) {
        int m = grid.length;
        int n = grid[0].length;
        int[] arr = new int[m * n];
        int base = grid[0][0];

        int idx = 0;
        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                if (Math.abs(grid[r][c] - base) % x != 0) {
                    return -1;
                }
                arr[idx++] = grid[r][c];
            }
        }

        Arrays.sort(arr);
        int median = arr[arr.length / 2];
        int operations = 0;

        for (int val : arr) {
            operations += Math.abs(val - median) / x;
        }

        return operations;
    }
}