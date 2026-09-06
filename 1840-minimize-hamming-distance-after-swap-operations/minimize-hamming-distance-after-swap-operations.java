import java.util.HashMap;
import java.util.Map;

class Solution {
    public int minimumHammingDistance(int[] source, int[] target, int[][] allowedSwaps) {
        int n = source.length;
        int[] parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }

        for (int[] swap : allowedSwaps) {
            int rootA = find(parent, swap[0]);
            int rootB = find(parent, swap[1]);
            if (rootA != rootB) {
                parent[rootA] = rootB;
            }
        }

        Map<Integer, Map<Integer, Integer>> componentMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int root = find(parent, i);
            componentMap.computeIfAbsent(root, k -> new HashMap<>())
                        .put(source[i], componentMap.get(root).getOrDefault(source[i], 0) + 1);
        }

        int hammingDistance = 0;
        for (int i = 0; i < n; i++) {
            int root = find(parent, i);
            Map<Integer, Integer> countMap = componentMap.get(root);
            int count = countMap.getOrDefault(target[i], 0);
            if (count > 0) {
                countMap.put(target[i], count - 1);
            } else {
                hammingDistance++;
            }
        }

        return hammingDistance;
    }

    private int find(int[] parent, int i) {
        if (parent[i] == i) {
            return i;
        }
        return parent[i] = find(parent, parent[i]);
    }
}