import java.util.*;

class Solution {
    public long minimumCost(String source, String target, String[] original, String[] changed, int[] cost) {
        Map<String, Integer> idMap = new HashMap<>();
        int idCounter = 0;
        
        for (String s : original) {
            if (!idMap.containsKey(s)) idMap.put(s, idCounter++);
        }
        for (String s : changed) {
            if (!idMap.containsKey(s)) idMap.put(s, idCounter++);
        }

        int numNodes = idCounter;
        List<int[]>[] adj = new ArrayList[numNodes];
        for (int i = 0; i < numNodes; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int i = 0; i < original.length; i++) {
            int u = idMap.get(original[i]);
            int v = idMap.get(changed[i]);
            adj[u].add(new int[]{v, cost[i]});
        }

        long[][] dist = new long[numNodes][numNodes];
        for (int i = 0; i < numNodes; i++) {
            Arrays.fill(dist[i], Long.MAX_VALUE);
        }

        for (int i = 0; i < numNodes; i++) {
            dist[i][i] = 0;
            PriorityQueue<long[]> pq = new PriorityQueue<>(Comparator.comparingLong(a -> a[1]));
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

        Set<Integer> lengthSet = new HashSet<>();
        for (String s : original) {
            lengthSet.add(s.length());
        }
        int[] lengths = new int[lengthSet.size()];
        int idx = 0;
        for (int l : lengthSet) {
            lengths[idx++] = l;
        }

        int n = source.length();
        long[] dp = new long[n + 1];
        Arrays.fill(dp, Long.MAX_VALUE);
        dp[n] = 0;

        for (int i = n - 1; i >= 0; i--) {
            if (source.charAt(i) == target.charAt(i) && dp[i + 1] != Long.MAX_VALUE) {
                dp[i] = dp[i + 1];
            }
            
            for (int L : lengths) {
                if (i + L <= n) {
                    String sSub = source.substring(i, i + L);
                    String tSub = target.substring(i, i + L);
                    
                    Integer idS = idMap.get(sSub);
                    Integer idT = idMap.get(tSub);
                    
                    if (idS != null && idT != null && dist[idS][idT] != Long.MAX_VALUE && dp[i + L] != Long.MAX_VALUE) {
                        dp[i] = Math.min(dp[i], dp[i + L] + dist[idS][idT]);
                    }
                }
            }
        }

        return dp[0] == Long.MAX_VALUE ? -1 : dp[0];
    }
}