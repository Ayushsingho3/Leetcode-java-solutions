class Solution {
    public int robotSim(int[] commands, int[][] obstacles) {
        int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int x = 0, y = 0, dir = 0;
        int maxDistSq = 0;
        
        java.util.Set<Long> obstacleSet = new java.util.HashSet<>();
        for (int[] obs : obstacles) {
            long hash = (((long) obs[0]) << 32) | (((long) obs[1]) & 0xFFFFFFFFL);
            obstacleSet.add(hash);
        }
        
        for (int cmd : commands) {
            if (cmd == -2) {
                dir = (dir + 3) % 4;
            } else if (cmd == -1) {
                dir = (dir + 1) % 4;
            } else {
                for (int k = 0; k < cmd; k++) {
                    int nextX = x + dirs[dir][0];
                    int nextY = y + dirs[dir][1];
                    long hash = (((long) nextX) << 32) | (((long) nextY) & 0xFFFFFFFFL);
                    
                    if (obstacleSet.contains(hash)) {
                        break;
                    }
                    
                    x = nextX;
                    y = nextY;
                    maxDistSq = Math.max(maxDistSq, x * x + y * y);
                }
            }
        }
        
        return maxDistSq;
    }
}