import java.util.HashMap;
import java.util.Map;

class Solution {
    public int minimumHammingDistance(int[] source, int[] target, int[][] allowedSwaps) {
        int n = source.length;
        DSU dsu = new DSU(n);

        for (int[] swap : allowedSwaps) {
            dsu.union(swap[0], swap[1]);
        }

        Map<Integer, Map<Integer, Integer>> componentMaps = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int root = dsu.find(i);
            componentMaps.putIfAbsent(root, new HashMap<>());
            Map<Integer, Integer> countMap = componentMaps.get(root);
            countMap.put(source[i], countMap.getOrDefault(source[i], 0) + 1);
        }

        int hammingDistance = 0;
        for (int i = 0; i < n; i++) {
            int root = dsu.find(i);
            Map<Integer, Integer> countMap = componentMaps.get(root);
            int val = target[i];

            if (countMap.containsKey(val) && countMap.get(val) > 0) {
                countMap.put(val, countMap.get(val) - 1);
            } else {
                hammingDistance++;
            }
        }

        return hammingDistance;
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