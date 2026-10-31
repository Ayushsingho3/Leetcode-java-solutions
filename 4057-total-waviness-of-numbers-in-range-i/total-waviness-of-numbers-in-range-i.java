import java.util.Arrays;

class Solution {
    private char[] cs;
    private long[][][][][] memoCnt;
    private long[][][][][] memoWav;

    public int totalWaviness(long num1, long num2) {
        return (int) (calc(num2) - calc(num1 - 1));
    }

    private long calc(long x) {
        if (x < 0) return 0;
        cs = Long.toString(x).toCharArray();
        int n = cs.length;
        memoCnt = new long[n][11][11][2][2];
        memoWav = new long[n][11][11][2][2];
        for (int i = 0; i < n; i++) {
            for (int a = 0; a < 11; a++) {
                for (int b = 0; b < 11; b++) {
                    for (int s = 0; s < 2; s++) {
                        Arrays.fill(memoCnt[i][a][b][s], -1);
                        Arrays.fill(memoWav[i][a][b][s], -1);
                    }
                }
            }
        }
        return dfs(0, -1, -1, true, true)[1];
    }

    private long[] dfs(int idx, int twobefore, int onebefore, boolean leadingZero, boolean isBound) {
        if (idx == cs.length) {
            return leadingZero ? new long[] {0, 0} : new long[] {1, 0};
        }
        int tbIdx = twobefore + 1;
        int obIdx = onebefore + 1;
        int lzIdx = leadingZero ? 1 : 0;
        int boundIdx = isBound ? 1 : 0;

        if (memoCnt[idx][tbIdx][obIdx][lzIdx][boundIdx] != -1) {
            return new long[] {
                memoCnt[idx][tbIdx][obIdx][lzIdx][boundIdx],
                memoWav[idx][tbIdx][obIdx][lzIdx][boundIdx]
            };
        }

        int upper = isBound ? cs[idx] - '0' : 9;
        long totalCount = 0;
        long totalWaves = 0;

        for (int i = 0; i <= upper; i++) {
            long wave = 0;
            int nextTwoBefore, nextOneBefore;
            if (leadingZero) {
                nextTwoBefore = -1;
                nextOneBefore = (i == 0) ? -1 : i;
            } else {
                nextTwoBefore = onebefore;
                nextOneBefore = i;
                if (twobefore >= 0 && onebefore >= 0) {
                    if ((onebefore > twobefore && onebefore > i) || (onebefore < twobefore && onebefore < i)) {
                        wave = 1;
                    }
                }
            }
            boolean nextBound = isBound && (i == upper);
            boolean nextLeadingZero = leadingZero && (i == 0);

            long[] sub = dfs(idx + 1, nextTwoBefore, nextOneBefore, nextLeadingZero, nextBound);
            totalCount += sub[0];
            totalWaves += sub[1] + sub[0] * wave;
        }

        memoCnt[idx][tbIdx][obIdx][lzIdx][boundIdx] = totalCount;
        memoWav[idx][tbIdx][obIdx][lzIdx][boundIdx] = totalWaves;
        return new long[] {totalCount, totalWaves};
    }
}