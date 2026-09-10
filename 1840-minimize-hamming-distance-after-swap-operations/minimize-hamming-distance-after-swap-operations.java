import java.util.HashMap;
import java.util.Map;

class Solution {
    public int minimumHammingDistance(int[] source, int[] target, int[][] allowedSwaps) {
        int n = source.length;
        DSU dsu = new DSU(n);

        for (int[] swap : allowedSwaps) {
            dsu.union(swap[0], swap[1]);
        }

        Map<Integer, Map<Integer, Integer>> componentCounts = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int root = dsu.find(i);
            componentCounts
                .computeIfAbsent(root, k -> new HashMap<>())
                .put(source[i], componentCounts.get(root).getOrDefault(source[i], 0) + 1);
        }

        int hammingDistance = 0;
        for (int i = 0; i < n; i++) {
            int root = dsu.find(i);
            Map<Integer, Integer> counts = componentCounts.get(root);
            int count = counts.getOrDefault(target[i], 0);

            if (count > 0) {
                counts.put(target[i], count - 1);
            } else {
                hammingDistance++;
            }
        }

        return hammingDistance;
    }

    private static class DSU {
        private final int[] parent;

        public DSU(int n) {
            parent = new int[n];
            for (int i = 0; i < n; i++) {
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