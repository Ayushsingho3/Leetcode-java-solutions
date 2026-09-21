import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

class Solution {
    private static class Sequence {
        int startX, startY;
        int endX, endY;
        int length;

        Sequence(int startX, int startY, int endX, int endY, int length) {
            this.startX = startX;
            this.startY = startY;
            this.endX = endX;
            this.endY = endY;
            this.length = length;
        }
    }

    public int maxDistance(int side, int[][] points, int k) {
        List<int[]> ordered = getOrderedPoints(side, points);

        int l = 0;
        int r = (int) Math.min(2L * side, Integer.MAX_VALUE);
        int ans = 0;

        while (l <= r) {
            int m = l + (r - l) / 2;
            if (isValidDistance(ordered, k, m)) {
                ans = m;
                l = m + 1;
            } else {
                r = m - 1;
            }
        }

        return ans;
    }

    private boolean isValidDistance(List<int[]> ordered, int k, int d) {
        Deque<Sequence> dq = new ArrayDeque<>();
        int[] first = ordered.get(0);
        dq.add(new Sequence(first[0], first[1], first[0], first[1], 1));

        int maxLength = 1;

        for (int i = 1; i < ordered.size(); i++) {
            int x = ordered.get(i)[0];
            int y = ordered.get(i)[1];
            int startX = x;
            int startY = y;
            int length = 1;

            while (!dq.isEmpty() && dist(x, y, dq.peekFirst().endX, dq.peekFirst().endY) >= d) {
                Sequence seq = dq.peekFirst();
                if (dist(x, y, seq.startX, seq.startY) >= d && seq.length + 1 >= length) {
                    startX = seq.startX;
                    startY = seq.startY;
                    length = seq.length + 1;
                    maxLength = Math.max(maxLength, length);
                }
                dq.pollFirst();
            }

            dq.addLast(new Sequence(startX, startY, x, y, length));
        }

        return maxLength >= k;
    }

    private long dist(int x1, int y1, int x2, int y2) {
        return Math.abs((long) x1 - x2) + Math.abs((long) y1 - y2);
    }

    private List<int[]> getOrderedPoints(int side, int[][] points) {
        List<int[]> left = new ArrayList<>();
        List<int[]> top = new ArrayList<>();
        List<int[]> right = new ArrayList<>();
        List<int[]> bottom = new ArrayList<>();

        for (int[] point : points) {
            int x = point[0];
            int y = point[1];
            if (x == 0 && y > 0) {
                left.add(point);
            } else if (x > 0 && y == side) {
                top.add(point);
            } else if (x == side && y < side) {
                right.add(point);
            } else {
                bottom.add(point);
            }
        }

        left.sort((a, b) -> Integer.compare(a[1], b[1]));
        top.sort((a, b) -> Integer.compare(a[0], b[0]));
        right.sort((a, b) -> Integer.compare(b[1], a[1]));
        bottom.sort((a, b) -> Integer.compare(b[0], a[0]));

        List<int[]> ordered = new ArrayList<>(points.length);
        ordered.addAll(left);
        ordered.addAll(top);
        ordered.addAll(right);
        ordered.addAll(bottom);

        return ordered;
    }
}