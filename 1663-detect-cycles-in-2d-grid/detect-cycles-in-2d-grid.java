class Solution {
    private static final int[][] DIRS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public boolean containsCycle(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        boolean[][] visited = new boolean[m][n];

        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                if (!visited[r][c]) {
                    if (dfs(grid, visited, r, c, -1, -1, grid[r][c])) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean dfs(char[][] grid, boolean[][] visited, int r, int c, int pr, int pc, char target) {
        visited[r][c] = true;
        int m = grid.length;
        int n = grid[0].length;

        for (int[] d : DIRS) {
            int nr = r + d[0];
            int nc = c + d[1];

            if (nr >= 0 && nr < m && nc >= 0 && nc < n && grid[nr][nc] == target) {
                if (nr == pr && nc == pc) {
                    continue;
                }
                if (visited[nr][nc]) {
                    return true;
                }
                if (dfs(grid, visited, nr, nc, r, c, target)) {
                    return true;
                }
            }
        }
        return false;
    }
}