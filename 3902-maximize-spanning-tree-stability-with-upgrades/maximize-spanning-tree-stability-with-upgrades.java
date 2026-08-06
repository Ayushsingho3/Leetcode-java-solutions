class Solution {
    class DSU {
        int[] parent;
        public DSU(int n) {
            parent = new int[n];
            for (int i = 0; i < n; i++) parent[i] = i;
        }
        public int find(int i) {
            if (parent[i] == i)
                return i;
            return parent[i] = find(parent[i]);
        }
        public boolean union(int i, int j) {
            int root_i = find(i);
            int root_j = find(j);
            if (root_i != root_j) {
                parent[root_i] = root_j;
                return true;
            }
            return false;
        }
    }
    
    public int maxStability(int n, int[][] edges, int k) {
        int minMust = Integer.MAX_VALUE;
        DSU initialDsu = new DSU(n);
        int comp = n;
        
        for (int[] e : edges) {
            if (e[3] == 1) {
                minMust = Math.min(minMust, e[2]);
                if (initialDsu.union(e[0], e[1])) {
                    comp--;
                } else {
                    return -1;
                }
            }
        }
        
        DSU allDsu = new DSU(n);
        int allComp = n;
        for (int[] e : edges) {
            if (allDsu.union(e[0], e[1])) {
                allComp--;
            }
        }
        
        if (allComp > 1) {
            return -1;
        }
        
        long low = 0, high = 0;
        for(int[] e : edges) {
            if (e[3] == 1) {
                high = Math.max(high, e[2]);
            } else {
                high = Math.max(high, (long)e[2] * 2);
            }
        }
        
        if (minMust != Integer.MAX_VALUE) {
            high = Math.min(high, minMust);
        }
        
        long ans = -1;
        while (low <= high) {
            long mid = low + (high - low) / 2;
            if (check(n, edges, k, mid)) {
                ans = mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        
        return (int)ans;
    }
    
    private boolean check(int n, int[][] edges, int k, long S) {
        DSU dsu = new DSU(n);
        int edgesAdded = 0;
        
        for (int[] e : edges) {
            if (e[3] == 1) {
                if (e[2] < S) return false;
                if (dsu.union(e[0], e[1])) {
                    edgesAdded++;
                }
            }
        }
        
        for (int[] e : edges) {
            if (e[3] == 0 && e[2] >= S) {
                if (dsu.union(e[0], e[1])) {
                    edgesAdded++;
                }
            }
        }
        int cost = 0;
        for (int[] e : edges) {
            if (e[3] == 0 && e[2] < S && (long)e[2] * 2 >= S) {
                if (dsu.union(e[0], e[1])) {
                    edgesAdded++;
                    cost++;
                }
            }
        }
        
        return edgesAdded == n - 1 && cost <= k;
    }
}