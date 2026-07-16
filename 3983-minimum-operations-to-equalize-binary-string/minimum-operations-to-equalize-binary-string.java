import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

class Solution {
    public int minOperations(String s, int k) {
        int n = s.length();
        int start = 0;
        
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '0') {
                start++;
            }
        }
        
        if (start == 0) return 0;
        
        int[] nextUnvisited = new int[n + 3];
        for (int i = 0; i < nextUnvisited.length; i++) {
            nextUnvisited[i] = i;
        }
        
        Queue<Integer> q = new LinkedList<>();
        q.offer(start);
        
        int[] dist = new int[n + 1];
        Arrays.fill(dist, -1);
        dist[start] = 0;
        
        nextUnvisited[start] = start + 2;
        
        while (!q.isEmpty()) {
            int cur = q.poll();
            
            int minX = Math.max(0, k - n + cur);
            int maxX = Math.min(cur, k);
            
            int minZ = cur + k - 2 * maxX;
            int maxZ = cur + k - 2 * minX;
            
            int currZ = find(minZ, nextUnvisited);
            while (currZ <= maxZ) {
                dist[currZ] = dist[cur] + 1;
                
                if (currZ == 0) {
                    return dist[currZ];
                }
                
                q.offer(currZ);
                nextUnvisited[currZ] = currZ + 2;
                currZ = find(currZ, nextUnvisited);
            }
        }
        
        return -1;
    }
    
    private int find(int i, int[] nextUnvisited) {
        if (nextUnvisited[i] == i) {
            return i;
        }
        return nextUnvisited[i] = find(nextUnvisited[i], nextUnvisited);
    }
}