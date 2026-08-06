class Solution {
    public int numberOfStableArrays(int num_zeros, int num_ones, int limit) {
        long[][][] dp = new long[num_zeros + 1][num_ones + 1][2];
        long MOD = 1000000007;
        
        for (int i = 1; i <= Math.min(num_zeros, limit); i++) {
            dp[i][0][0] = 1;
        }
        
        for (int j = 1; j <= Math.min(num_ones, limit); j++) {
            dp[0][j][1] = 1;
        }
        
        for (int i = 1; i <= num_zeros; i++) {
            for (int j = 1; j <= num_ones; j++) {
                dp[i][j][0] = (dp[i - 1][j][0] + dp[i - 1][j][1]) % MOD;
                if (i > limit) {
                    dp[i][j][0] = (dp[i][j][0] - dp[i - limit - 1][j][1] + MOD) % MOD;
                }
                
                dp[i][j][1] = (dp[i][j - 1][0] + dp[i][j - 1][1]) % MOD;
                if (j > limit) {
                    dp[i][j][1] = (dp[i][j][1] - dp[i][j - limit - 1][0] + MOD) % MOD;
                }
            }
        }
        
        return (int) ((dp[num_zeros][num_ones][0] + dp[num_zeros][num_ones][1]) % MOD);
    }
}