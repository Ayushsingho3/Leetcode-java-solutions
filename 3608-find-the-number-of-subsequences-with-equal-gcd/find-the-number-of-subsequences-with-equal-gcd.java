class Solution {
    public int subsequencePairCount(int[] nums) {
        int maxVal = 0;
        for (int num : nums) {
            maxVal = Math.max(maxVal, num);
        }
        
        int[][] dp = new int[maxVal + 1][maxVal + 1];
        dp[0][0] = 1;
        
        int mod = 1000000007;
        
        for (int x : nums) {
            int[][] nextDp = new int[maxVal + 1][maxVal + 1];
            
            for (int i = 0; i <= maxVal; i++) {
                for (int j = 0; j <= maxVal; j++) {
                    if (dp[i][j] > 0) {
                        int ways = dp[i][j];
                        
                        nextDp[i][j] = (nextDp[i][j] + ways) % mod;
                        
                        int gcd1 = (i == 0) ? x : gcd(i, x);
                        nextDp[gcd1][j] = (nextDp[gcd1][j] + ways) % mod;
                        
                        int gcd2 = (j == 0) ? x : gcd(j, x);
                        nextDp[i][gcd2] = (nextDp[i][gcd2] + ways) % mod;
                    }
                }
            }
            dp = nextDp;
        }
        
        int ans = 0;
        for (int i = 1; i <= maxVal; i++) {
            ans = (ans + dp[i][i]) % mod;
        }
        
        return ans;
    }
    
    private int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
}
