class Solution {
    public int minimumDistance(String word) {
        if (word == null || word.length() <= 1) {
            return 0;
        }
        
        int[] dp = new int[27];
        for (int i = 0; i < 27; i++) {
            dp[i] = 1000000;
        }
        dp[26] = 0; 
        
        int prev = word.charAt(0) - 'A';
        
        for (int i = 1; i < word.length(); i++) {
            int curr = word.charAt(i) - 'A';
            int[] new_dp = new int[27];
            for (int j = 0; j < 27; j++) {
                new_dp[j] = 1000000;
            }
            
            for (int c = 0; c <= 26; c++) {
                if (dp[c] != 1000000) {
                    new_dp[c] = Math.min(new_dp[c], dp[c] + dist(prev, curr));
                    
                    new_dp[prev] = Math.min(new_dp[prev], dp[c] + dist(c, curr));
                }
            }
            dp = new_dp;
            prev = curr;
        }
        
        int minDistance = Integer.MAX_VALUE;
        for (int c = 0; c <= 26; c++) {
            minDistance = Math.min(minDistance, dp[c]);
        }
        
        return minDistance;
    }
    
    private int dist(int a, int b) {
        if (a == 26) {
            return 0;
        }
        return Math.abs(a / 6 - b / 6) + Math.abs(a % 6 - b % 6);
    }
}