import java.util.Arrays;

class Solution {
    class Cell implements Comparable<Cell> {
        int r, c, val;
        
        public Cell(int r, int c, int val) {
            this.r = r;
            this.c = c;
            this.val = val;
        }
        
        @Override
        public int compareTo(Cell other) {
            return Integer.compare(other.val, this.val);
        }
    }

    public int minCost(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;
        
        long[][] dp = new long[m][n];
        for (int i = 0; i < m; i++) {
            Arrays.fill(dp[i], Long.MAX_VALUE);
        }
        
        dp[0][0] = 0;
        
        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                if (r == 0 && c == 0) continue;
                
                long minPrev = Long.MAX_VALUE;
                if (r > 0) minPrev = Math.min(minPrev, dp[r-1][c]);
                if (c > 0) minPrev = Math.min(minPrev, dp[r][c-1]);
                
                if (minPrev != Long.MAX_VALUE) {
                    dp[r][c] = minPrev + grid[r][c];
                }
            }
        }
        
        Cell[] cells = new Cell[m * n];
        int idx = 0;
        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                cells[idx++] = new Cell(r, c, grid[r][c]);
            }
        }
        Arrays.sort(cells);
        
        int maxTeleports = Math.min(k, m * n);
        
        for (int t = 1; t <= maxTeleports; t++) {
            long[][] next_dp = new long[m][n];
            long min_dp = Long.MAX_VALUE;
            
            int i = 0;
            while (i < cells.length) {
                int j = i;
                long min_in_group = Long.MAX_VALUE;
                
                while (j < cells.length && cells[j].val == cells[i].val) {
                    min_in_group = Math.min(min_in_group, dp[cells[j].r][cells[j].c]);
                    j++;
                }
                
                min_dp = Math.min(min_dp, min_in_group);
                
                for (int p = i; p < j; p++) {
                    next_dp[cells[p].r][cells[p].c] = min_dp;
                }
                
                i = j;
            }
            
            boolean changed = false;
            
            for (int r = 0; r < m; r++) {
                for (int c = 0; c < n; c++) {
                    long minPrev = Long.MAX_VALUE;
                    if (r > 0) minPrev = Math.min(minPrev, next_dp[r-1][c]);
                    if (c > 0) minPrev = Math.min(minPrev, next_dp[r][c-1]);
                    
                    if (minPrev != Long.MAX_VALUE) {
                        next_dp[r][c] = Math.min(next_dp[r][c], minPrev + grid[r][c]);
                    }
                    
                    if (next_dp[r][c] != dp[r][c]) {
                        changed = true;
                    }
                }
            }
            
            if (!changed) {
                break;
            }
            
            dp = next_dp;
        }
        
        return dp[m-1][n-1] == Long.MAX_VALUE ? -1 : (int) dp[m-1][n-1];
    }
}