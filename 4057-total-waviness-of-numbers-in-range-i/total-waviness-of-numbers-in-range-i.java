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
        return dfs(0, 10, 10, 0, true)[1];
    }

    private long[] dfs(int pos, int prev2, int prev1, int started, boolean limit) {
        if (pos == cs.length) {
            return new long[] { started == 1 ? 1 : 0, 0 };
        }
        int limIdx = limit ? 1 : 0;
        if (memoCnt[pos][prev2][prev1][started][limIdx] != -1) {
            return new long[] { memoCnt[pos][prev2][prev1][started][limIdx], memoWav[pos][prev2][prev1][started][limIdx] };
        }

        int up = limit ? cs[pos] - '0' : 9;
        long totalC = 0;
        long totalW = 0;

        for (int d = 0; d <= up; ++d) {
            boolean nlimit = limit && (d == up);
            int ns, np2, np1, add = 0;
            if (started == 0) {
                if (d == 0) {
                    ns = 0;
                    np2 = 10;
                    np1 = 10;
                } else {
                    ns = 1;
                    np2 = 10;
                    np1 = d;
                }
            } else {
                ns = 1;
                np2 = prev1;
                np1 = d;
                if (prev2 != 10 && ((prev1 > prev2 && prev1 > d) || (prev1 < prev2 && prev1 < d))) {
                    add = 1;
                }
            }
            long[] res = dfs(pos + 1, np2, np1, ns, nlimit);
            long c = res[0];
            long w = res[1];
            totalC += c;
            totalW += w + (long) add * c;
        }

        memoCnt[pos][prev2][prev1][started][limIdx] = totalC;
        memoWav[pos][prev2][prev1][started][limIdx] = totalW;
        return new long[] { totalC, totalW };
    }
}