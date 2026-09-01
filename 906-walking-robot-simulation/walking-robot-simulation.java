import java.util.HashSet;
import java.util.Set;

class Solution {
    public int robotSim(int[] commands, int[][] obstacles) {
        Set<Long> obstacleSet = new HashSet<>();
        for (int[] obs : obstacles) {
            long hash = (((long) obs[0]) << 32) | ((long) obs[1] & 0xFFFFFFFFL);
            obstacleSet.add(hash);
        }
        
        // Directions: North (0), East (1), South (2), West (3)
        int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int d = 0;
        
        int x = 0;
        int y = 0;
        int maxDistSquared = 0;
        
        for (int cmd : commands) {
            if (cmd == -1) {
                d = (d + 1) % 4;
            } else if (cmd == -2) {
                d = (d + 3) % 4;
            } else {
                for (int i = 0; i < cmd; i++) {
                    int nx = x + dirs[d][0];
                    int ny = y + dirs[d][1];
                    
                    long nextHash = (((long) nx) << 32) | ((long) ny & 0xFFFFFFFFL);
                    
                    if (obstacleSet.contains(nextHash)) {
                        break;
                    }
                    
                    x = nx;
                    y = ny;
                    maxDistSquared = Math.max(maxDistSquared, x * x + y * y);
                }
            }
        }
        
        return maxDistSquared;
    }
}