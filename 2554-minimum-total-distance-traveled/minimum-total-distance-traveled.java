import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

class Solution {
    public long minimumTotalDistance(List<Integer> robot, int[][] factory) {
        Collections.sort(robot);
        Arrays.sort(factory, (a, b) -> Integer.compare(a[0], b[0]));
        
        List<Integer> factories = new ArrayList<>();
        int n = robot.size();
        for (int[] f : factory) {
            int pos = f[0];
            int limit = Math.min(f[1], n);
            for (int i = 0; i < limit; i++) {
                factories.add(pos);
            }
        }
        
        long INF = 1_000_000_000_000_000_000L;
        long[] dp = new long[n + 1];
        Arrays.fill(dp, INF);
        dp[0] = 0;
        
        for (int fPos : factories) {
            for (int i = n; i > 0; i--) {
                if (dp[i - 1] != INF) {
                    long costToAssign = Math.abs((long) robot.get(i - 1) - fPos);
                    dp[i] = Math.min(dp[i], dp[i - 1] + costToAssign);
                }
            }
        }
        
        return dp[n];
    }
    
    public long minimumTotalDistance(int[] robot, int[][] factory) {
        List<Integer> robotList = new ArrayList<>();
        for (int r : robot) {
            robotList.add(r);
        }
        return minimumTotalDistance(robotList, factory);
    }
}