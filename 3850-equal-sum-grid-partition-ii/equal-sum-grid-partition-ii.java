class Solution {
    public boolean canPartitionGrid(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        long totalSum = 0;
        
        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                totalSum += grid[r][c];
            }
        }
        
        if (m > 1) {
            long topSum = 0;
            java.util.Map<Long, Integer> topMap = new java.util.HashMap<>();
            java.util.Map<Long, Integer> bottomMap = new java.util.HashMap<>();
            
            for (int r = 0; r < m; r++) {
                for (int c = 0; c < n; c++) {
                    long val = grid[r][c];
                    bottomMap.put(val, bottomMap.getOrDefault(val, 0) + 1);
                }
            }
            
            for (int i = 0; i < m - 1; i++) {
                for (int c = 0; c < n; c++) {
                    long val = grid[i][c];
                    topSum += val;
                    topMap.put(val, topMap.getOrDefault(val, 0) + 1);
                    
                    int count = bottomMap.get(val);
                    if (count == 1) {
                        bottomMap.remove(val);
                    } else {
                        bottomMap.put(val, count - 1);
                    }
                }
                
                long bottomSum = totalSum - topSum;
                long diff = Math.abs(topSum - bottomSum);
                
                if (diff == 0) return true;
                
                if (topSum > bottomSum) {
                    if (i == 0) {
                        if (diff == grid[0][0] || diff == grid[0][n - 1]) return true;
                    } else if (n == 1) {
                        if (diff == grid[0][0] || diff == grid[i][0]) return true;
                    } else {
                        if (topMap.containsKey(diff)) return true;
                    }
                } else {
                    if (i == m - 2) {
                        if (diff == grid[m - 1][0] || diff == grid[m - 1][n - 1]) return true;
                    } else if (n == 1) {
                        if (diff == grid[i + 1][0] || diff == grid[m - 1][0]) return true;
                    } else {
                        if (bottomMap.containsKey(diff)) return true;
                    }
                }
            }
        }
        
        if (n > 1) {
            long leftSum = 0;
            java.util.Map<Long, Integer> leftMap = new java.util.HashMap<>();
            java.util.Map<Long, Integer> rightMap = new java.util.HashMap<>();
            
            for (int r = 0; r < m; r++) {
                for (int c = 0; c < n; c++) {
                    long val = grid[r][c];
                    rightMap.put(val, rightMap.getOrDefault(val, 0) + 1);
                }
            }
            
            for (int j = 0; j < n - 1; j++) {
                for (int r = 0; r < m; r++) {
                    long val = grid[r][j];
                    leftSum += val;
                    leftMap.put(val, leftMap.getOrDefault(val, 0) + 1);
                    
                    int count = rightMap.get(val);
                    if (count == 1) {
                        rightMap.remove(val);
                    } else {
                        rightMap.put(val, count - 1);
                    }
                }
                
                long rightSum = totalSum - leftSum;
                long diff = Math.abs(leftSum - rightSum);
                
                if (diff == 0) return true;
                
                if (leftSum > rightSum) {
                    if (j == 0) {
                        if (diff == grid[0][0] || diff == grid[m - 1][0]) return true;
                    } else if (m == 1) {
                        if (diff == grid[0][0] || diff == grid[0][j]) return true;
                    } else {
                        if (leftMap.containsKey(diff)) return true;
                    }
                } else {
                    if (j == n - 2) {
                        if (diff == grid[0][n - 1] || diff == grid[m - 1][n - 1]) return true;
                    } else if (m == 1) {
                        if (diff == grid[0][j + 1] || diff == grid[0][n - 1]) return true;
                    } else {
                        if (rightMap.containsKey(diff)) return true;
                    }
                }
            }
        }
        
        return false;
    }
}