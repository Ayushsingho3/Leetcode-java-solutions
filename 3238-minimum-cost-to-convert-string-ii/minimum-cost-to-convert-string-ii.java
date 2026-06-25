import java.util.*;

class Solution {
    public long minimumCost(String source, String target, String[] original, String[] changed, int[] cost) {
        int n = source.length();
        Map<String, Integer> idMap = new HashMap<>();
        Set<Integer> lengths = new HashSet<>();
        
        int numIds = 0;
        for (int i = 0; i < original.length; i++) {
            if (!idMap.containsKey(original[i])) {
                idMap.put(original[i], numIds++);
            }
            if (!idMap.containsKey(changed[i])) {
                idMap.put(changed[i], numIds++);
            }
            lengths.add(original[i].length());
        }
        
        List<int[]>[] adj = new ArrayList[numIds];
        for (int i = 0; i < numIds; i++) {
            adj[i] = new ArrayList<>();
        }
        
        for (int i = 0; i < original.length; i++) {
            int u = idMap.get(original[i]);
            int v = idMap.get(changed[i]);
            adj[u].add(new int[]{v, cost[i]});
        }
        
        long INF = Long.MAX_VALUE / 3;
        long[][] dist = new long[numIds][numIds];
        for (int i = 0; i < numIds; i++) {
            Arrays.fill(dist[i], INF);
            dist[i][i] = 0;
            
            PriorityQueue<long[]> pq = new PriorityQueue<>((a, b) -> Long.compare(a[1], b[1]));
            pq.add(new long[]{i, 0});
            
            while (!pq.isEmpty()) {
                long[] curr = pq.poll();
                int u = (int) curr[0];
                long d = curr[1];
                
                if (d > dist[i][u]) continue;
                
                for (int[] edge : adj[u]) {
                    int v = edge[0];
                    long w = edge[1];
                    
                    if (dist[i][u] + w < dist[i][v]) {
                        dist[i][v] = dist[i][u] + w;
                        pq.add(new long[]{v, dist[i][v]});
                    }
                }
            }
        }
        
        long[] dp = new long[n + 1];
        Arrays.fill(dp, INF);
        dp[n] = 0;
        
        for (int i = n - 1; i >= 0; i--) {
            if (source.charAt(i) == target.charAt(i)) {
                dp[i] = dp[i + 1];
            }
            
            for (int len : lengths) {
                if (i + len <= n) {
                    String subSrc = source.substring(i, i + len);
                    String subTgt = target.substring(i, i + len);
                    
                    Integer u = idMap.get(subSrc);
                    Integer v = idMap.get(subTgt);
                    
                    if (u != null && v != null && dist[u][v] != INF) {
                        dp[i] = Math.min(dp[i], dp[i + len] + dist[u][v]);
                    }
                }
            }
        }
        
        return dp[0] >= INF ? -1 : dp[0];
    }
}
