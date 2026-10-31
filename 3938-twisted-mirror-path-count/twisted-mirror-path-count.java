class Solution {
    public int uniquePaths(int[][] grid) {
        int MOD = 1_000_000_007;
        int m = grid.length;
        int n = grid[0].length;
        int[][] dp = new int[n + 1][2];
        dp[1][0] = 1;
        dp[1][1] = 1;
        
        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                if (grid[r][c] == 1) {
                    int next0 = dp[c + 1][1];
                    int next1 = dp[c][0];
                    dp[c + 1][0] = next0;
                    dp[c + 1][1] = next1;
                } else {
                    int val = (dp[c + 1][1] + dp[c][0]) % MOD;
                    dp[c + 1][0] = val;
                    dp[c + 1][1] = val;
                }
            }
        }
        return dp[n][0];
    }
}