import java.util.Arrays;
import java.util.Collections;
import java.util.List;

class Solution {
    public long minimumTotalDistance(List<Integer> robot, int[][] factory) {
        Collections.sort(robot);
        Arrays.sort(factory, (a, b) -> Integer.compare(a[0], b[0]));

        int m = robot.size();
        int n = factory.length;

        long[][] dp = new long[m + 1][n + 1];
        long INF = (long) 1e16;

        for (int i = 1; i <= m; i++) {
            Arrays.fill(dp[i], INF);
        }

        for (int j = 1; j <= n; j++) {
            int pos = factory[j - 1][0];
            int limit = factory[j - 1][1];

            for (int i = 0; i <= m; i++) {
                dp[i][j] = dp[i][j - 1];
                long distSum = 0;

                for (int k = 1; k <= Math.min(i, limit); k++) {
                    distSum += Math.abs((long) robot.get(i - k) - pos);
                    if (dp[i - k][j - 1] != INF) {
                        dp[i][j] = Math.min(dp[i][j], dp[i - k][j - 1] + distSum);
                    }
                }
            }
        }

        return dp[m][n];
    }
}