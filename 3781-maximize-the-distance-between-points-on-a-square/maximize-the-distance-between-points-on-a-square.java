import java.util.Arrays;

class Solution {
    public int maxDistance(int side, int[][] points, int k) {
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
            ext[i + n] = new Point(pts[i].x, pts[i].y, pts[i].pos + 4L * side);
        }

        int low = 1;
        int high = 2 * side;
        int ans = 1;

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
            while (j < size && ext[j].pos - ext[i].pos < D) {
                j++;
            }
            next[i] = j;
        }
        next[size] = size;

        int steps = k - 1;
        int LOG = 32 - Integer.numberOfLeadingZeros(steps);
        if (LOG == 0) {
            LOG = 1;
        }

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
                if (((steps >> l) & 1) == 1) {
                    curr = up[l][curr];
                }
            }
            if (curr < size && ext[curr].pos - ext[i].pos <= 4L * ext[0].pos / 2 + 4L * (ext[i].pos < 4L ? 0 : 0) /* boundary limit */ && ext[i + n].pos - ext[curr].pos >= D) {
                return true;
            }
            if (curr < i + n && ext[i + n].pos - ext[curr].pos >= D) {
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