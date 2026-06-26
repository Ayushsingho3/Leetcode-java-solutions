import java.util.Arrays;

class Solution {
    class TrieNode {
        TrieNode[] children = new TrieNode[26];
        int id = -1;
    }

    public long minimumCost(String source, String target, String[] original, String[] changed, int[] cost) {
        TrieNode root = new TrieNode();
        int[] idCounter = {0};
        
        int maxNodes = 205; 
        long[][] dist = new long[maxNodes][maxNodes];
        long INF = Long.MAX_VALUE / 2;
        
        for (int i = 0; i < maxNodes; i++) {
            Arrays.fill(dist[i], INF);
            dist[i][i] = 0;
        }
        
        for (int i = 0; i < original.length; i++) {
            int u = insert(root, original[i], idCounter);
            int v = insert(root, changed[i], idCounter);
            dist[u][v] = Math.min(dist[u][v], cost[i]);
        }
        
        int numNodes = idCounter[0];
        
        for (int k = 0; k < numNodes; k++) {
            for (int i = 0; i < numNodes; i++) {
                if (dist[i][k] == INF) continue; 
                for (int j = 0; j < numNodes; j++) {
                    if (dist[k][j] == INF) continue; 
                    dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                }
            }
        }
        
        int n = source.length();
        long[] dp = new long[n + 1];
        Arrays.fill(dp, INF);
        dp[n] = 0; 
        
        for (int i = n - 1; i >= 0; i--) {
            if (source.charAt(i) == target.charAt(i)) {
                dp[i] = dp[i + 1];
            }
            
            TrieNode nodeSrc = root;
            TrieNode nodeTgt = root;
            
            for (int j = i; j < n; j++) {
                int idxSrc = source.charAt(j) - 'a';
                int idxTgt = target.charAt(j) - 'a';
                
                nodeSrc = nodeSrc.children[idxSrc];
                nodeTgt = nodeTgt.children[idxTgt];
                
                if (nodeSrc == null || nodeTgt == null) {
                    break;
                }
                
                if (nodeSrc.id != -1 && nodeTgt.id != -1 && dist[nodeSrc.id][nodeTgt.id] != INF) {
                    dp[i] = Math.min(dp[i], dist[nodeSrc.id][nodeTgt.id] + dp[j + 1]);
                }
            }
        }
        
        return dp[0] == INF ? -1 : dp[0];
    }
    
    private int insert(TrieNode root, String s, int[] idCounter) {
        TrieNode curr = root;
        for (int i = 0; i < s.length(); i++) {
            int idx = s.charAt(i) - 'a';
            if (curr.children[idx] == null) {
                curr.children[idx] = new TrieNode();
            }
            curr = curr.children[idx];
        }
        if (curr.id == -1) {
            curr.id = idCounter[0]++;
        }
        return curr.id;
    }
}