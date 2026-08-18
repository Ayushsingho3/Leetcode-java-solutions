class Solution {
    public int maxProductPath(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        
        long[][] maxDp = new long[m][n];
        long[][] minDp = new long[m][n];
        
        maxDp[0][0] = grid[0][0];
        minDp[0][0] = grid[0][0];
        
        for (int i = 1; i < m; i++) {
            maxDp[i][0] = maxDp[i - 1][0] * grid[i][0];
            minDp[i][0] = minDp[i - 1][0] * grid[i][0];
        }
        
        for (int j = 1; j < n; j++) {
            maxDp[0][j] = maxDp[0][j - 1] * grid[0][j];
            minDp[0][j] = minDp[0][j - 1] * grid[0][j];
        }
        
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                long val = grid[i][j];
                if (val >= 0) {
                    maxDp[i][j] = val * Math.max(maxDp[i - 1][j], maxDp[i][j - 1]);
                    minDp[i][j] = val * Math.min(minDp[i - 1][j], minDp[i][j - 1]);
                } else {
                    maxDp[i][j] = val * Math.min(minDp[i - 1][j], minDp[i][j - 1]);
                    minDp[i][j] = val * Math.max(maxDp[i - 1][j], maxDp[i][j - 1]);
                }
            }
        }
        
        if (maxDp[m - 1][n - 1] < 0) {
            return -1;
        }
        
        return (int) (maxDp[m - 1][n - 1] % 1000000007);
    }
}