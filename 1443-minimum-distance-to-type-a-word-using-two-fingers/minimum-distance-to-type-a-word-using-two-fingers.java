import java.util.Arrays;

class Solution {
    public int minimumDistance(String word) {
        int[] dp = new int[27];
        Arrays.fill(dp, 1000000);
        dp[26] = 0;

        for (int i = 1; i < word.length(); i++) {
            int prev = word.charAt(i - 1) - 'A';
            int curr = word.charAt(i) - 'A';
            int[] nextDp = new int[27];
            Arrays.fill(nextDp, 1000000);

            int dPrevCurr = dist(prev, curr);

            for (int j = 0; j <= 26; j++) {
                if (dp[j] >= 1000000) {
                    continue;
                }
                nextDp[j] = Math.min(nextDp[j], dp[j] + dPrevCurr);
                nextDp[prev] = Math.min(nextDp[prev], dp[j] + dist(j, curr));
            }

            dp = nextDp;
        }

        int min = Integer.MAX_VALUE;
        for (int val : dp) {
            min = Math.min(min, val);
        }

        return min;
    }

    private int dist(int a, int b) {
        if (a == 26 || b == 26) {
            return 0;
        }
        return Math.abs(a / 6 - b / 6) + Math.abs(a % 6 - b % 6);
    }
}