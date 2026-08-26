class Solution {
    public int minimumDistance(String word) {
        int[] dp = new int[27];
        java.util.Arrays.fill(dp, 20000);
        dp[26] = 0;
        
        int p = 26;
        for (int i = 0; i < word.length(); i++) {
            int c = word.charAt(i) - 'A';
            int[] nextDp = new int[27];
            java.util.Arrays.fill(nextDp, 20000);
            
            for (int j = 0; j <= 26; j++) {
                if (dp[j] != 20000) {
                    nextDp[j] = Math.min(nextDp[j], dp[j] + cost(p, c));
                    
                    nextDp[p] = Math.min(nextDp[p], dp[j] + cost(j, c));
                }
            }
            dp = nextDp;
            p = c;
        }
        
        int ans = Integer.MAX_VALUE;
        for (int val : dp) {
            ans = Math.min(ans, val);
        }
        return ans;
    }
    
    private int cost(int a, int b) {
        if (a == 26) return 0;
        return Math.abs(a / 6 - b / 6) + Math.abs(a % 6 - b % 6);
    }
}