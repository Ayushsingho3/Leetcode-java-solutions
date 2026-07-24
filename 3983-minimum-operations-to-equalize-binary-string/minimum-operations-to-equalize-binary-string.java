import java.util.*;

class Solution {
    public int minOperations(String s, int k) {
        int n = s.length();
        int initialZeros = 0;
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '0') {
                initialZeros++;
            }
        }
        
        if (initialZeros == 0) {
            return 0;
        }
        
        @SuppressWarnings("unchecked")
        TreeSet<Integer>[] unvisited = new TreeSet[2];
        unvisited[0] = new TreeSet<>();
        unvisited[1] = new TreeSet<>();
        
        for (int i = 0; i <= n; i++) {
            unvisited[i & 1].add(i);
        }
        
        unvisited[initialZeros & 1].remove(initialZeros);
        
        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(initialZeros);
        
        int steps = 0;
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int q = 0; q < size; q++) {
                int z = queue.poll();
                
                int min_i = Math.max(0, k - (n - z));
                int max_i = Math.min(k, z);
                
                int z_max = z + k - 2 * min_i;
                int z_min = z + k - 2 * max_i;
                
                int parity = (z + k) & 1;
                TreeSet<Integer> targetSet = unvisited[parity];
                
                Integer next = targetSet.ceiling(z_min);
                while (next != null && next <= z_max) {
                    if (next == 0) {
                        return steps + 1;
                    }
                    queue.offer(next);
                    targetSet.remove(next);
                    next = targetSet.ceiling(z_min);
                }
            }
            steps++;
        }
        
        return -1;
    }
}