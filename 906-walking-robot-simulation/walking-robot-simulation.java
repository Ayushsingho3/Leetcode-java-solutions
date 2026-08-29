import java.util.HashSet;
import java.util.Set;

class Solution {
    public int robotSim(int[] commands, int[][] obstacles) {
        int[] dx = {0, 1, 0, -1};
        int[] dy = {1, 0, -1, 0};
        int x = 0;
        int y = 0;
        int direction = 0;
        
        Set<Long> obsSet = new HashSet<>();
        for (int[] obs : obstacles) {
            obsSet.add(((long) obs[0] << 32) | (obs[1] & 0xFFFFFFFFL));
        }
        
        int maxDistSq = 0;
        
        for (int cmd : commands) {
            if (cmd == -2) {
                direction = (direction + 3) % 4;
            } else if (cmd == -1) {
                direction = (direction + 1) % 4;
            } else {
                for (int i = 0; i < cmd; i++) {
                    int nx = x + dx[direction];
                    int ny = y + dy[direction];
                    
                    long hash = ((long) nx << 32) | (ny & 0xFFFFFFFFL);
                    
                    if (obsSet.contains(hash)) {
                        break;
                    }
                    
                    x = nx;
                    y = ny;
                    maxDistSq = Math.max(maxDistSq, x * x + y * y);
                }
            }
        }
        
        return maxDistSq;
    }
}