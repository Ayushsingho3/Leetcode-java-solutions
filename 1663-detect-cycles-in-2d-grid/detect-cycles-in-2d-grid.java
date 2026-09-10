class Solution {
    public boolean containsCycle(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        DSU dsu = new DSU(m * n);

        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                int u = r * n + c;

                if (c + 1 < n && grid[r][c] == grid[r][c + 1]) {
                    int v = r * n + (c + 1);
                    if (!dsu.union(u, v)) {
                        return true;
                    }
                }

                if (r + 1 < m && grid[r][c] == grid[r + 1][c]) {
                    int v = (r + 1) * n + c;
                    if (!dsu.union(u, v)) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    private static class DSU {
        private final int[] parent;

        public DSU(int size) {
            parent = new int[size];
            for (int i = 0; i < size; i++) {
                parent[i] = i;
            }
        }

        public int find(int i) {
            if (parent[i] == i) {
                return i;
            }
            return parent[i] = find(parent[i]);
        }

        public boolean union(int i, int j) {
            int rootI = find(i);
            int rootJ = find(j);
            if (rootI == rootJ) {
                return false;
            }
            parent[rootI] = rootJ;
            return true;
        }
    }
}