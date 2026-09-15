class Solution {
    public boolean containsCycle(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        DSU dsu = new DSU(m * n);

        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                int curr = r * n + c;

                if (c + 1 < n && grid[r][c] == grid[r][c + 1]) {
                    int right = r * n + (c + 1);
                    if (dsu.find(curr) == dsu.find(right)) {
                        return true;
                    }
                    dsu.union(curr, right);
                }

                if (r + 1 < m && grid[r][c] == grid[r + 1][c]) {
                    int down = (r + 1) * n + c;
                    if (dsu.find(curr) == dsu.find(down)) {
                        return true;
                    }
                    dsu.union(curr, down);
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

        public void union(int i, int j) {
            int rootI = find(i);
            int rootJ = find(j);
            if (rootI != rootJ) {
                parent[rootI] = rootJ;
            }
        }
    }
}