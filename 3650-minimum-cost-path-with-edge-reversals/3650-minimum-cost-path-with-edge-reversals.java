import java.util.*;

class Solution {
    public int minCost(int n, int[][] edges) {
        List<int[]>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int w = edge[2];
            
            graph[u].add(new int[]{v, w});
            graph[v].add(new int[]{u, 2 * w});
        }
        
        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[0] = 0;
        
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));
        pq.offer(new int[]{0, 0});
        
        while (!pq.isEmpty()) {
            int[] current = pq.poll();
            int currentCost = current[0];
            int u = current[1];
            
            if (u == n - 1) {
                return currentCost;
            }
            
            if (currentCost > dist[u]) {
                continue;
            }
            
            for (int[] neighbor : graph[u]) {
                int v = neighbor[0];
                int weight = neighbor[1];
                
                int newCost = currentCost + weight;
                if (newCost < dist[v]) {
                    dist[v] = newCost;
                    pq.offer(new int[]{newCost, v});
                }
            }
        }
        
        return -1;
    }
}