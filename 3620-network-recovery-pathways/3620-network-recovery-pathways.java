import java.util.*;

class Solution {
    public int findMaxPathScore(int[][] edges, boolean[] online, long k) {
        int n = online.length;
        List<int[]>[] adj = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }
        
        int[] inDegree = new int[n];
        int maxCost = -1;
        
        for (int[] e : edges) {
            int u = e[0];
            int v = e[1];
            int cost = e[2];
            adj[u].add(new int[]{v, cost});
            inDegree[v]++;
            maxCost = Math.max(maxCost, cost);
        }
        
        int[] topo = new int[n];
        int idx = 0;
        Queue<Integer> q = new LinkedList<>();
        
        for (int i = 0; i < n; i++) {
            if (inDegree[i] == 0) {
                q.offer(i);
            }
        }
        
        while (!q.isEmpty()) {
            int u = q.poll();
            topo[idx++] = u;
            for (int[] edge : adj[u]) {
                int v = edge[0];
                if (--inDegree[v] == 0) {
                    q.offer(v);
                }
            }
        }
        
        int low = 0;
        int high = maxCost;
        int ans = -1;
        
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (isValid(mid, n, adj, topo, online, k)) {
                ans = mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        
        return ans;
    }
    
    private boolean isValid(int mid, int n, List<int[]>[] adj, int[] topo, boolean[] online, long k) {
        long[] dist = new long[n];
        Arrays.fill(dist, Long.MAX_VALUE);
        dist[0] = 0;
        
        for (int u : topo) {
            if (dist[u] == Long.MAX_VALUE) {
                continue;
            }
            
            for (int[] edge : adj[u]) {
                int v = edge[0];
                int cost = edge[1];
                
                if (cost < mid) {
                    continue;
                }
                if (v != n - 1 && v != 0 && !online[v]) {
                    continue;
                }
                
                if (dist[u] + cost < dist[v]) {
                    dist[v] = dist[u] + cost;
                }
            }
        }
        
        return dist[n - 1] <= k;
    }
}