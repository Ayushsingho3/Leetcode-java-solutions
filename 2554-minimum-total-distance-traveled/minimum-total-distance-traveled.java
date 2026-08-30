import java.util.*;

class Solution {
    public long minimumTotalDistance(List<Integer> robot, int[][] factory) {
        Collections.sort(robot);
        Arrays.sort(factory, (a, b) -> Integer.compare(a[0], b[0]));
        
        List<Integer> factoryPositions = new ArrayList<>();
        for (int[] f : factory) {
            for (int i = 0; i < f[1]; i++) {
                factoryPositions.add(f[0]);
            }
        }
        
        int n = robot.size();
        long[] dp = new long[n + 1];
        long INF = (long) 1e18; 
        
        Arrays.fill(dp, INF);
        dp[0] = 0;
        
        for (int fPos : factoryPositions) {
            for (int i = n; i >= 1; i--) {
                if (dp[i - 1] != INF) {
                    long distance = Math.abs((long) robot.get(i - 1) - fPos);
                    dp[i] = Math.min(dp[i], dp[i - 1] + distance);
                }
            }
        }
        
        return dp[n];
    }
}