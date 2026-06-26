import java.util.*;

class Solution {
    public int minCost(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;
        long INF = Long.MAX_VALUE / 2; 
        
        long[][][] dp = new long[k + 1][m][n];
        for (int t = 0; t <= k; t++) {
            for (int i = 0; i < m; i++) {
                Arrays.fill(dp[t][i], INF);
            }
        }
        
        dp[0][0][0] = 0;
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i > 0) dp[0][i][j] = Math.min(dp[0][i][j], dp[0][i - 1][j] + grid[i][j]);
                if (j > 0) dp[0][i][j] = Math.min(dp[0][i][j], dp[0][i][j - 1] + grid[i][j]);
            }
        }
        
        TreeMap<Integer, List<int[]>> valueGroups = new TreeMap<>(Collections.reverseOrder());
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                valueGroups.computeIfAbsent(grid[i][j], x -> new ArrayList<>()).add(new int[]{i, j});
            }
        }
        
        for (int t = 1; t <= k; t++) {
            long minCostFromLargerCells = INF;
            
            for (int val : valueGroups.keySet()) {
                List<int[]> cells = valueGroups.get(val);
                
                for (int[] cell : cells) {
                    minCostFromLargerCells = Math.min(minCostFromLargerCells, dp[t - 1][cell[0]][cell[1]]);
                }
                
                for (int[] cell : cells) {
                    dp[t][cell[0]][cell[1]] = minCostFromLargerCells;
                }
            }
            
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (i > 0) dp[t][i][j] = Math.min(dp[t][i][j], dp[t][i - 1][j] + grid[i][j]);
                    if (j > 0) dp[t][i][j] = Math.min(dp[t][i][j], dp[t][i][j - 1] + grid[i][j]);
                }
            }
        }
        
        long minTotalCost = INF;
        for (int t = 0; t <= k; t++) {
            minTotalCost = Math.min(minTotalCost, dp[t][m - 1][n - 1]);
        }
        
        return (int) minTotalCost;
    }
}