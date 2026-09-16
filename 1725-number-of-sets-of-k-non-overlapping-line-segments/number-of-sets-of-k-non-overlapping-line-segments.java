class Solution {
    public int numberOfSets(int n, int k) {
        int MOD = 1_000_000_007;
        int totalPoints = n + k - 1;
        int choose = 2 * k;

        if (choose > totalPoints) {
            return 0;
        }

        int[][] dp = new int[totalPoints + 1][choose + 1];

        for (int i = 0; i <= totalPoints; i++) {
            dp[i][0] = 1;
            for (int j = 1; j <= Math.min(i, choose); j++) {
                dp[i][j] = (dp[i - 1][j - 1] + dp[i - 1][j]) % MOD;
            }
        }

        return dp[totalPoints][choose];
    }
}