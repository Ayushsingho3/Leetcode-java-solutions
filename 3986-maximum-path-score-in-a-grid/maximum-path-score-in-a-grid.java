import java.util.Arrays;

class Solution {
    public int maxPathScore(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;

        int[][][] dp = new int[m][n][k + 1];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                Arrays.fill(dp[i][j], -1);
            }
        }

        int startVal = grid[0][0];
        int startCost = (startVal == 0) ? 0 : 1;
        int startScore = startVal;

        if (startCost <= k) {
            dp[0][0][startCost] = startScore;
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) continue;

                int cellVal = grid[i][j];
                int cellCost = (cellVal == 0) ? 0 : 1;
                int cellScore = cellVal;

                for (int c = cellCost; c <= k; c++) {
                    int prevCost = c - cellCost;
                    int maxPrev = -1;

                    if (i > 0 && dp[i - 1][j][prevCost] != -1) {
                        maxPrev = Math.max(maxPrev, dp[i - 1][j][prevCost]);
                    }
                    if (j > 0 && dp[i][j - 1][prevCost] != -1) {
                        maxPrev = Math.max(maxPrev, dp[i][j - 1][prevCost]);
                    }

                    if (maxPrev != -1) {
                        dp[i][j][c] = maxPrev + cellScore;
                    }
                }
            }
        }

        int maxScore = -1;
        for (int c = 0; c <= k; c++) {
            maxScore = Math.max(maxScore, dp[m - 1][n - 1][c]);
        }

        return maxScore;
    }
}