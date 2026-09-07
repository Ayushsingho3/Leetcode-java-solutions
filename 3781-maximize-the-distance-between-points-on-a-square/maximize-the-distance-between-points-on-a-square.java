import java.util.Arrays;

class Solution {
    public int maxDistance(int side, int[][] points, int k) {
        int n = points.length;
        long L = 4L * side;
        long[] pos = new long[n];

        for (int i = 0; i < n; i++) {
            int x = points[i][0];
            int y = points[i][1];
            if (y == 0) {
                pos[i] = x;
            } else if (x == side) {
                pos[i] = (long) side + y;
            } else if (y == side) {
                pos[i] = 3L * side - x;
            } else {
                pos[i] = 4L * side - y;
            }
        }

        Arrays.sort(pos);

        long[] d = new long[2 * n];
        for (int i = 0; i < n; i++) {
            d[i] = pos[i];
            d[i + n] = pos[i] + L;
        }

        long low = 1;
        long high = 2L * side;
        long ans = 0;

        while (low <= high) {
            long mid = low + (high - low) / 2;
            if (check(mid, d, n, k, L)) {
                ans = mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return (int) ans;
    }

    private boolean check(long D, long[] d, int n, int k, long L) {
        int[] nxt = new int[2 * n];
        int r = 0;
        for (int l = 0; l < 2 * n; l++) {
            while (r < 2 * n && d[r] - d[l] < D) {
                r++;
            }
            nxt[l] = r;
        }

        int LOG = 32 - Integer.numberOfLeadingZeros(k);
        int[][] up = new int[LOG][2 * n + 1];
        for (int l = 0; l <= 2 * n; l++) {
            up[0][l] = (l < 2 * n) ? nxt[l] : 2 * n;
        }

        for (int p = 1; p < LOG; p++) {
            for (int l = 0; l <= 2 * n; l++) {
                up[p][l] = up[p - 1][up[p - 1][l]];
            }
        }

        int steps = k - 1;
        for (int i = 0; i < n; i++) {
            int curr = i;
            for (int p = 0; p < LOG; p++) {
                if (((steps >> p) & 1) == 1) {
                    curr = up[p][curr];
                }
            }
            if (curr < 2 * n && d[i] + L - d[curr] >= D) {
                return true;
            }
        }

        return false;
    }
}