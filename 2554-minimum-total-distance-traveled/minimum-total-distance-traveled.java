import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

class Solution {
    public long minimumTotalDistance(List<Integer> robot, int[][] factory) {
        Collections.sort(robot);
        Arrays.sort(factory, (a, b) -> Integer.compare(a[0], b[0]));

        List<Integer> slots = new ArrayList<>();
        for (int[] f : factory) {
            int pos = f[0];
            int limit = Math.min(f[1], robot.size());
            for (int i = 0; i < limit; i++) {
                slots.add(pos);
            }
        }

        int n = robot.size();
        int m = slots.size();

        long[][] dp = new long[n + 1][m + 1];
        long INF = 1000000000000000L;

        for (int i = 1; i <= n; i++) {
            dp[i][0] = INF;
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                dp[i][j] = dp[i][j - 1];
                long cost = dp[i - 1][j - 1] + Math.abs((long) robot.get(i - 1) - slots.get(j - 1));
                if (cost < dp[i][j]) {
                    dp[i][j] = cost;
                }
            }
        }

        return dp[n][m];
    }
}