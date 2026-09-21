import java.util.ArrayList;
import java.util.List;

class Solution {
    public boolean containsCycle(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int numCells = m * n;

        UnionFind uf = new UnionFind(numCells);
        boolean[] visited = new boolean[numCells];

        int[][] directions = {{0, 1}, {1, 0}};

        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                int currId = r * n + c;
                visited[currId] = true;

                for (int[] dir : directions) {
                    int nr = r + dir[0];
                    int nc = c + dir[1];

                    if (nr < m && nc < n && grid[r][c] == grid[nr][nc]) {
                        int nextId = nr * n + nc;
                        if (uf.union(currId, nextId)) {
                            return true;
                        }
                    }
                }
            }
        }

        return false;
    }

    private static class UnionFind {
        private final int[] parent;
        private final int[] rank;

        public UnionFind(int size) {
            parent = new int[size];
            rank = new int[size];
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
                return true;
            }

            if (rank[rootI] < rank[rootJ]) {
                parent[rootI] = rootJ;
            } else if (rank[rootI] > rank[rootJ]) {
                parent[rootJ] = rootI;
            } else {
                parent[rootJ] = rootI;
                rank[rootI]++;
            }

            return false;
        }
    }
}