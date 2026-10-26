class Solution {
    public int countIslands(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;
        int count = 0;
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] > 0) {
                    long islandSum = dfs(grid, i, j);
                    if (islandSum % k == 0) {
                        count++;
                    }
                }
            }
        }
        
        return count;
    }
    
    private long dfs(int[][] grid, int r, int c) {
        if (r < 0 || c < 0 || r >= grid.length || c >= grid[0].length || grid[r][c] == 0) {
            return 0;
        }
        
        long val = grid[r][c];
        grid[r][c] = 0;
        
        val += dfs(grid, r + 1, c);
        val += dfs(grid, r - 1, c);
        val += dfs(grid, r, c + 1);
        val += dfs(grid, r, c - 1);
        
        return val;
    }
}