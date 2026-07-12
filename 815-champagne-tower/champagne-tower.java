class Solution {
    public double champagneTower(int poured, int query_row, int query_glass) {
        double[] dp = new double[query_row + 2];
        dp[0] = (double) poured;
        
        for (int row = 0; row < query_row; row++) {
            double[] nextRow = new double[query_row + 2];
            
            for (int col = 0; col <= row; col++) {
                if (dp[col] > 1.0) {
                    double excess = (dp[col] - 1.0) / 2.0;
                    nextRow[col] += excess;
                    nextRow[col + 1] += excess;
                }
            }
            dp = nextRow;
        }
        
        return Math.min(1.0, dp[query_glass]);
    }
}