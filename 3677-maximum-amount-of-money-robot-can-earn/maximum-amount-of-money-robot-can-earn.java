class Solution {
    public int maximumAmount(int[][] coins) {
        int m = coins.length;
        int n = coins[0].length;
        
        long[][][] dp = new long[m][n][3];
        long INF = Long.MIN_VALUE / 2;
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < 3; k++) {
                    dp[i][j][k] = INF;
                }
            }
        }
        
        int v0 = coins[0][0];
        dp[0][0][0] = v0;
        if (v0 < 0) {
            dp[0][0][1] = 0;
        }
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) continue;
                
                long val = coins[i][j];
                
                for (int k = 0; k < 3; k++) {
                    long maxPrev = INF;
                    if (i > 0) maxPrev = Math.max(maxPrev, dp[i - 1][j][k]);
                    if (j > 0) maxPrev = Math.max(maxPrev, dp[i][j - 1][k]);
                    
                    if (maxPrev != INF) {
                        dp[i][j][k] = maxPrev + val;
                    }
                    
                    if (val < 0 && k > 0) {
                        long maxPrevK = INF;
                        if (i > 0) maxPrevK = Math.max(maxPrevK, dp[i - 1][j][k - 1]);
                        if (j > 0) maxPrevK = Math.max(maxPrevK, dp[i][j - 1][k - 1]);
                        
                        if (maxPrevK != INF) {
                            dp[i][j][k] = Math.max(dp[i][j][k], maxPrevK);
                        }
                    }
                }
            }
        }
        
        long ans = INF;
        for (int k = 0; k < 3; k++) {
            ans = Math.max(ans, dp[m - 1][n - 1][k]);
        }
        
        return (int) ans;
    }
}