import java.util.Arrays;

class Solution {
    public int maxDistance(int side, int[][] points, int k) {
        if (k == 1) {
            return 2 * side;
        }

        int n = points.length;
        Point[] pts = new Point[n];
        for (int i = 0; i < n; i++) {
            long pos = getPerimeterPos(points[i][0], points[i][1], side);
            pts[i] = new Point(points[i][0], points[i][1], pos);
        }

        Arrays.sort(pts, (a, b) -> Long.compare(a.pos, b.pos));

        Point[] ext = new Point[2 * n];
        for (int i = 0; i < n; i++) {
            ext[i] = pts[i];
            ext[i + n] = pts[i];
        }

        int low = 0;
        int high = 2 * side;
        int ans = 0;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (canDo(mid, ext, n, k)) {
                ans = mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return ans;
    }

    private boolean canDo(int D, Point[] ext, int n, int k) {
        int size = 2 * n;
        int[] next = new int[size + 1];
        int j = 0;

        for (int i = 0; i < size; i++) {
            if (j < i + 1) {
                j = i + 1;
            }
            while (j < i + n && j < size && dist(ext[i], ext[j]) < D) {
                j++;
            }
            if (j < i + n && j < size) {
                next[i] = j;
            } else {
                next[i] = size;
            }
        }
        next[size] = size;

        int LOG = 32 - Integer.numberOfLeadingZeros(k);
        int[][] up = new int[LOG][size + 1];

        for (int i = 0; i <= size; i++) {
            up[0][i] = next[i];
        }

        for (int l = 1; l < LOG; l++) {
            for (int i = 0; i <= size; i++) {
                up[l][i] = up[l - 1][up[l - 1][i]];
            }
        }

        for (int i = 0; i < n; i++) {
            int curr = i;
            for (int l = 0; l < LOG; l++) {
                if (((k >> l) & 1) == 1) {
                    curr = up[l][curr];
                }
            }
            if (curr <= i + n) {
                return true;
            }
        }

        return false;
    }

    private long getPerimeterPos(int x, int y, long side) {
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

    private int dist(Point p1, Point p2) {
        return Math.abs(p1.x - p2.x) + Math.abs(p1.y - p2.y);
    }

    private static class Point {
        int x, y;
        long pos;

        Point(int x, int y, long pos) {
            this.x = x;
            this.y = y;
            this.pos = pos;
        }
    }
}