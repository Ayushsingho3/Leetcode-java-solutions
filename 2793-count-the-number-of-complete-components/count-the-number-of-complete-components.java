import java.util.*;

class Solution {
    public int countCompleteComponents(int n, int[][] edges) {
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) adj.add(new ArrayList<>());
        for (int[] edge : edges) {
            adj.get(edge[0]).add(edge[1]);
            adj.get(edge[1]).add(edge[0]);
        }

        boolean[] visited = new boolean[n];
        int completeComponents = 0;

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                List<Integer> component = new ArrayList<>();
                Queue<Integer> q = new LinkedList<>();
                q.add(i);
                visited[i] = true;
                
                while (!q.isEmpty()) {
                    int curr = q.poll();
                    component.add(curr);
                    for (int neighbor : adj.get(curr)) {
                        if (!visited[neighbor]) {
                            visited[neighbor] = true;
                            q.add(neighbor);
                        }
                    }
                }

                if (isComplete(component, adj)) {
                    completeComponents++;
                }
            }
        }
        return completeComponents;
    }

    private boolean isComplete(List<Integer> component, List<List<Integer>> adj) {
        long V = component.size();
        long E = 0;
        for (int node : component) {
            E += adj.get(node).size();
        }
        return (E / 2) == (V * (V - 1) / 2);
    }
}