class Solution {
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
        
        if (m > 1) {
            java.util.Set<Long> topSet = new java.util.HashSet<>();
            long topSum = 0;
            for (int i = 0; i < m - 1; i++) {
                for (int j = 0; j < n; j++) {
                    topSet.add((long) grid[i][j]);
                }
                topSum += rowSum[i];
                long bottomSum = total - topSum;
                long d = topSum - bottomSum;
                
                if (d == 0) return true;
                
                if (d > 0) {
                    if (n == 1) {
                        if (d == grid[0][0] || d == grid[i][0]) return true;
                    } else {
                        if (i == 0) {
                            if (d == grid[0][0] || d == grid[0][n - 1]) return true;
                        } else {
                            if (topSet.contains(d)) return true;
                        }
                    }
                }
            }
            
            java.util.Set<Long> bottomSet = new java.util.HashSet<>();
            long bottomSumRev = 0;
            for (int i = m - 1; i >= 1; i--) {
                for (int j = 0; j < n; j++) {
                    bottomSet.add((long) grid[i][j]);
                }
                bottomSumRev += rowSum[i];
                long topSumRev = total - bottomSumRev;
                long d = bottomSumRev - topSumRev;
                
                if (d > 0) {
                    if (n == 1) {
                        if (d == grid[i][0] || d == grid[m - 1][0]) return true;
                    } else {
                        if (i == m - 1) {
                            if (d == grid[m - 1][0] || d == grid[m - 1][n - 1]) return true;
                        } else {
                            if (bottomSet.contains(d)) return true;
                        }
                    }
                }
            }
        }
        
        if (n > 1) {
            java.util.Set<Long> leftSet = new java.util.HashSet<>();
            long leftSum = 0;
            for (int j = 0; j < n - 1; j++) {
                for (int i = 0; i < m; i++) {
                    leftSet.add((long) grid[i][j]);
                }
                leftSum += colSum[j];
                long rightSum = total - leftSum;
                long d = leftSum - rightSum;
                
                if (d == 0) return true;
                
                if (d > 0) {
                    if (m == 1) {
                        if (d == grid[0][0] || d == grid[0][j]) return true;
                    } else {
                        if (j == 0) {
                            if (d == grid[0][0] || d == grid[m - 1][0]) return true;
                        } else {
                            if (leftSet.contains(d)) return true;
                        }
                    }
                }
            }
            
            java.util.Set<Long> rightSet = new java.util.HashSet<>();
            long rightSumRev = 0;
            for (int j = n - 1; j >= 1; j--) {
                for (int i = 0; i < m; i++) {
                    rightSet.add((long) grid[i][j]);
                }
                rightSumRev += colSum[j];
                long leftSumRev = total - rightSumRev;
                long d = rightSumRev - leftSumRev;
                
                if (d > 0) {
                    if (m == 1) {
                        if (d == grid[0][j] || d == grid[0][n - 1]) return true;
                    } else {
                        if (j == n - 1) {
                            if (d == grid[0][n - 1] || d == grid[m - 1][n - 1]) return true;
                        } else {
                            if (rightSet.contains(d)) return true;
                        }
                    }
                }
            }
        }
        
        return false;
    }
}