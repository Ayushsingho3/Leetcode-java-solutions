class Solution {
    public int zigZagArrays(int n, int l, int r) {
        int MOD = 1_000_000_007;
        int m = r - l + 1;

        if (n == 1) {
            return m % MOD;
        }
        if (m == 1) {
            return 0;
        }

        long[] up = new long[m];
        long[] down = new long[m];

        for (int v = 0; v < m; v++) {
            up[v] = v;
            down[v] = m - 1 - v;
        }

        for (int step = 3; step <= n; step++) {
            long[] nextUp = new long[m];
            long[] nextDown = new long[m];

            long prefixDown = 0;
            for (int v = 0; v < m; v++) {
                nextUp[v] = prefixDown;
                prefixDown = (prefixDown + down[v]) % MOD;
            }

            long suffixUp = 0;
            for (int v = m - 1; v >= 0; v--) {
                nextDown[v] = suffixUp;
                suffixUp = (suffixUp + up[v]) % MOD;
            }

            up = nextUp;
            down = nextDown;
        }

        long total = 0;
        for (int v = 0; v < m; v++) {
            total = (total + up[v] + down[v]) % MOD;
        }

        return (int) total;
    }
}