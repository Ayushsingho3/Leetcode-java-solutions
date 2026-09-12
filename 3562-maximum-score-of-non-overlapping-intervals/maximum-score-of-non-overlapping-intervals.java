import java.util.Arrays;
import java.util.List;

class Solution {
    public int[] maximumWeight(List<List<Integer>> intervals) {
        int n = intervals.size();
        Interval[] arr = new Interval[n];
        for (int i = 0; i < n; i++) {
            List<Integer> interval = intervals.get(i);
            arr[i] = new Interval(interval.get(0), interval.get(1), interval.get(2), i);
        }

        Arrays.sort(arr, (a, b) -> {
            if (a.l != b.l) return Integer.compare(a.l, b.l);
            if (a.r != b.r) return Integer.compare(a.r, b.r);
            return Integer.compare(a.origIdx, b.origIdx);
        });

        int[] nextPos = new int[n];
        for (int i = 0; i < n; i++) {
            nextPos[i] = findNext(arr, arr[i].r);
        }

        State[][] dp = new State[n + 1][5];
        for (int i = 0; i <= n; i++) {
            dp[i][0] = new State(0, new int[0]);
        }
        for (int c = 1; c <= 4; c++) {
            dp[n][c] = new State(0, new int[0]);
        }

        for (int i = n - 1; i >= 0; i--) {
            for (int c = 1; c <= 4; c++) {
                State skip = dp[i + 1][c];

                int next = nextPos[i];
                State rest = dp[next][c - 1];
                long takeWeight = (long) arr[i].weight + rest.weight;
                int[] takeIndices = combine(arr[i].origIdx, rest.indices);
                State take = new State(takeWeight, takeIndices);

                if (isBetter(take, skip)) {
                    dp[i][c] = take;
                } else {
                    dp[i][c] = skip;
                }
            }
        }

        return dp[0][4].indices;
    }

    private int findNext(Interval[] arr, int targetR) {
        int low = 0, high = arr.length;
        while (low < high) {
            int mid = (low + high) >>> 1;
            if (arr[mid].l > targetR) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }

    private boolean isBetter(State s1, State s2) {
        if (s1.weight != s2.weight) {
            return s1.weight > s2.weight;
        }
        int len1 = s1.indices.length;
        int len2 = s2.indices.length;
        int minLen = Math.min(len1, len2);
        for (int i = 0; i < minLen; i++) {
            if (s1.indices[i] != s2.indices[i]) {
                return s1.indices[i] < s2.indices[i];
            }
        }
        return len1 < len2;
    }

    private int[] combine(int origIdx, int[] rest) {
        int n = rest.length;
        int[] res = new int[n + 1];
        int pos = 0;
        while (pos < n && rest[pos] < origIdx) {
            res[pos] = rest[pos];
            pos++;
        }
        res[pos] = origIdx;
        while (pos < n) {
            res[pos + 1] = rest[pos];
            pos++;
        }
        return res;
    }

    private static class Interval {
        int l, r, weight, origIdx;

        Interval(int l, int r, int weight, int origIdx) {
            this.l = l;
            this.r = r;
            this.weight = weight;
            this.origIdx = origIdx;
        }
    }

    private static class State {
        long weight;
        int[] indices;

        State(long weight, int[] indices) {
            this.weight = weight;
            this.indices = indices;
        }
    }
}