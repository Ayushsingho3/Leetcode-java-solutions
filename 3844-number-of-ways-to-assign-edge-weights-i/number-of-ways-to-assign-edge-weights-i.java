import java.util.*;

class Solution {
    private static final int MOD = 1_000_000_007;

    public int assignEdgeWeights(int[][] edges) {
        int n = edges.length + 1;
        if (n == 1) return 0;
        
        List<Integer>[] g = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            g[i] = new ArrayList<>();
        }
        for (int[] e : edges) {
            g[e[0]].add(e[1]);
            g[e[1]].add(e[0]);
        }

        int maxDepth = dfs(1, 0, g);
        if (maxDepth == 0) return 0;
        
        return (int) power(2, maxDepth - 1);
    }

    private int dfs(int u, int parent, List<Integer>[] g) {
        int depth = 0;
        for (int v : g[u]) {
            if (v != parent) {
                depth = Math.max(depth, 1 + dfs(v, u, g));
            }
        }
        return depth;
    }

    private long power(long base, int exp) {
        long res = 1;
        base %= MOD;
        while (exp > 0) {
            if ((exp & 1) == 1) {
                res = (res * base) % MOD;
            }
            base = (base * base) % MOD;
            exp >>= 1;
        }
        return res;
    }
}