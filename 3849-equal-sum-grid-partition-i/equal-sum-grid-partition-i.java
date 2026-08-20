class Solution {
    public boolean canPartition(int[][] grid) {
        return canPartitionGrid(grid);
    }

    public boolean canPartitionGrid(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        
        long[] rowSum = new long[m];
        long[] colSum = new long[n];
        long total = 0;
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                long val = grid[i][j];
                rowSum[i] += val;
                colSum[j] += val;
                total += val;
            }
        }
        
        if (total % 2 != 0) {
            return false;
        }
        
        long target = total / 2;
        
        long current = 0;
        for (int i = 0; i < m - 1; i++) {
            current += rowSum[i];
            if (current == target) {
                return true;
            }
        }
        
        current = 0;
        for (int j = 0; j < n - 1; j++) {
            current += colSum[j];
            if (current == target) {
                return true;
            }
        }
        
        return false;
    }
}