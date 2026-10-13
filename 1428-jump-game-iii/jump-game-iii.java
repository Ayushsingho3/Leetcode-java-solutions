import java.util.LinkedList;
import java.util.Queue;

class Solution {
    public boolean canReach(int[] arr, int start) {
        int n = arr.length;
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[n];
        
        queue.add(start);
        visited[start] = true;
        
        while (!queue.isEmpty()) {
            int curr = queue.poll();
            
            if (arr[curr] == 0) {
                return true;
            }
            
            int next1 = curr + arr[curr];
            int next2 = curr - arr[curr];
            
            if (next1 < n && !visited[next1]) {
                visited[next1] = true;
                queue.add(next1);
            }
            
            if (next2 >= 0 && !visited[next2]) {
                visited[next2] = true;
                queue.add(next2);
            }
        }
        
        return false;
    }
}