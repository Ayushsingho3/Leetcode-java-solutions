class Solution {
    public java.util.List<Integer> remainingMethods(int n, int k, int[][] invocations) {
        java.util.List<Integer>[] adj = new java.util.ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new java.util.ArrayList<>();
        }
        for (int[] inv : invocations) {
            adj[inv[0]].add(inv[1]);
        }
        
        boolean[] suspicious = new boolean[n];
        java.util.Queue<Integer> q = new java.util.LinkedList<>();
        q.add(k);
        suspicious[k] = true;
        
        while (!q.isEmpty()) {
            int u = q.poll();
            for (int v : adj[u]) {
                if (!suspicious[v]) {
                    suspicious[v] = true;
                    q.add(v);
                }
            }
        }
        
        boolean canRemove = true;
        for (int[] inv : invocations) {
            if (!suspicious[inv[0]] && suspicious[inv[1]]) {
                canRemove = false;
                break;
            }
        }
        
        java.util.List<Integer> res = new java.util.ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (!canRemove || !suspicious[i]) {
                res.add(i);
            }
        }
        
        return res;
    }
}