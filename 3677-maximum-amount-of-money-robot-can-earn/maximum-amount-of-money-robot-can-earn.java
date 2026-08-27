class Solution {
    public int maximumAmount(int[][] coins) {
        int m = coins.length;
        int n = coins[0].length;
        int[][][] dp = new int[m][n][3];
        int MIN = Integer.MIN_VALUE / 2;
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dp[i][j][0] = MIN;
                dp[i][j][1] = MIN;
                dp[i][j][2] = MIN;
            }
        }
        
        dp[0][0][0] = coins[0][0];
        dp[0][0][1] = coins[0][0] < 0 ? 0 : coins[0][0];
        dp[0][0][2] = coins[0][0] < 0 ? 0 : coins[0][0];
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) continue;
                
                int val = coins[i][j];
                
                for (int k = 0; k <= 2; k++) {
                    int best = MIN;
                    
                    if (i > 0) best = Math.max(best, dp[i - 1][j][k]);
                    if (j > 0) best = Math.max(best, dp[i][j - 1][k]);
                    
                    if (best != MIN) {
                        dp[i][j][k] = best + val;
                    }
                    
                    if (val < 0 && k > 0) {
                        int bestPrev = MIN;
                        if (i > 0) bestPrev = Math.max(bestPrev, dp[i - 1][j][k - 1]);
                        if (j > 0) bestPrev = Math.max(bestPrev, dp[i][j - 1][k - 1]);
                        
                        if (bestPrev != MIN) {
                            dp[i][j][k] = Math.max(dp[i][j][k], bestPrev);
                        }
                    }
                }
            }
        }
        
        return Math.max(dp[m - 1][n - 1][0], Math.max(dp[m - 1][n - 1][1], dp[m - 1][n - 1][2]));
    }
}