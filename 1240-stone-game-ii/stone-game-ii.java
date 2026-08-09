class Solution {
    public int stoneGameII(int[] piles) {
        int n = piles.length;
        int[][] memo = new int[n][n + 1];
        int[] suffixSum = new int[n];
        
        suffixSum[n - 1] = piles[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            suffixSum[i] = suffixSum[i + 1] + piles[i];
        }
        
        return dfs(0, 1, suffixSum, memo);
    }
    
    private int dfs(int i, int M, int[] suffixSum, int[][] memo) {
        if (i + 2 * M >= suffixSum.length) {
            return suffixSum[i];
        }
        if (memo[i][M] != 0) {
            return memo[i][M];
        }
        
        int maxStones = 0;
        for (int x = 1; x <= 2 * M; x++) {
            maxStones = Math.max(maxStones, suffixSum[i] - dfs(i + x, Math.max(M, x), suffixSum, memo));
        }
        
        memo[i][M] = maxStones;
        return maxStones;
    }
}