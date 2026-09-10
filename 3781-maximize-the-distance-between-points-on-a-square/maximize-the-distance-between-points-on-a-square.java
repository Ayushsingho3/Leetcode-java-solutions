import java.util.Arrays;

class Solution {
    public int maxDistance(int side, int[][] points, int k) {
        int n = points.length;

        Arrays.sort(points, (a, b) -> Long.compare(getPerimeter(a[0], a[1], side), getPerimeter(b[0], b[1], side)));

        int LOG = 0;
        while ((1 << LOG) <= k) {
            LOG++;
        }

        int low = 1;
        int high = 2 * side;
        int ans = 0;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (isValid(mid, k, n, points, side, LOG)) {
                ans = mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return ans;
    }

    private boolean isValid(int D, int k, int n, int[][] points, int side, int LOG) {
        int maxBound = 3 * n;
        int[] nextIdx = new int[maxBound + 1];
        int[][] up = new int[LOG][maxBound + 1];

        int j = 0;
        for (int i = 0; i < 2 * n; i++) {
            j = Math.max(j, i + 1);
            while (j < i + n && dist(points[i % n], points[j % n]) < D) {
                j++;
            }
            if (j < i + n) {
                nextIdx[i] = j;
            } else {
                nextIdx[i] = maxBound;
            }
        }

        for (int i = 2 * n; i <= maxBound; i++) {
            nextIdx[i] = maxBound;
        }

        for (int i = 0; i <= maxBound; i++) {
            up[0][i] = nextIdx[i];
        }

        for (int s = 1; s < LOG; s++) {
            for (int i = 0; i <= maxBound; i++) {
                up[s][i] = up[s - 1][up[s - 1][i]];
            }
        }

        int rem = k - 1;
        for (int i = 0; i < n; i++) {
            int curr = i;
            for (int s = LOG - 1; s >= 0; s--) {
                if (((rem >> s) & 1) == 1) {
                    curr = up[s][curr];
                }
            }

            if (curr < i + n && dist(points[i], points[curr % n]) >= D) {
                return true;
            }
        }

        return false;
    }

    private int dist(int[] p1, int[] p2) {
        return Math.abs(p1[0] - p2[0]) + Math.abs(p1[1] - p2[1]);
    }

    private long getPerimeter(long x, long y, long side) {
        if (y == 0) {
            return x;
        } else if (x == side) {
            return side + y;
        } else if (y == side) {
            return 3 * side - x;
        } else {
            return 4 * side - y;
        }
    }
}