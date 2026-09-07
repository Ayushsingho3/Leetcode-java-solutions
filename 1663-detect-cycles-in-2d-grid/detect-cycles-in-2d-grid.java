class Solution {
    public boolean containsCycle(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[] parent = new int[m * n];
        for (int i = 0; i < m * n; i++) {
            parent[i] = i;
        }

        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                int curr = r * n + c;

                if (c + 1 < n && grid[r][c] == grid[r][c + 1]) {
                    int right = r * n + c + 1;
                    if (!union(parent, curr, right)) {
                        return true;
                    }
                }

                if (r + 1 < m && grid[r][c] == grid[r + 1][c]) {
                    int down = (r + 1) * n + c;
                    if (!union(parent, curr, down)) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    private int find(int[] parent, int i) {
        if (parent[i] == i) {
            return i;
        }
        return parent[i] = find(parent, parent[i]);
    }

    private boolean union(int[] parent, int i, int j) {
        int rootI = find(parent, i);
        int rootJ = find(parent, j);
        if (rootI == rootJ) {
            return false;
        }
        parent[rootI] = rootJ;
        return true;
    }
}