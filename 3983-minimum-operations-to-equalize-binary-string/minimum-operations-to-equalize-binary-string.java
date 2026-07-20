class Solution {
    int[] parent;
    
    public int minOperations(String s, int k) {
        int n = s.length();
        int initialZeros = 0;
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '0') {
                initialZeros++;
            }
        }
        
        if (initialZeros == 0) return 0;
        
        parent = new int[n + 3];
        for (int i = 0; i < parent.length; i++) {
            parent[i] = i;
        }
        
        int[] dist = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            dist[i] = -1;
        }
        
        int[] q = new int[n + 1];
        int head = 0, tail = 0;
        
        q[tail++] = initialZeros;
        dist[initialZeros] = 0;
        remove(initialZeros);
        
        while (head < tail) {
            int cur = q[head++];
            int d = dist[cur];
            
            int minZerosFlipped = Math.max(0, k - (n - cur));
            int maxZerosFlipped = Math.min(cur, k);
            
            int minNext = cur + k - 2 * maxZerosFlipped;
            int maxNext = cur + k - 2 * minZerosFlipped;
            
            for (int next = find(minNext); next <= maxNext; next = find(next)) {
                dist[next] = d + 1;
                if (next == 0) return dist[next];
                q[tail++] = next;
                remove(next);
            }
        }
        
        return -1;
    }
    
    private int find(int i) {
        int root = i;
        while (root != parent[root]) {
            root = parent[root];
        }
        int curr = i;
        while (curr != root) {
            int nxt = parent[curr];
            parent[curr] = root;
            curr = nxt;
        }
        return root;
    }
    
    private void remove(int i) {
        parent[i] = find(i + 2);
    }
}