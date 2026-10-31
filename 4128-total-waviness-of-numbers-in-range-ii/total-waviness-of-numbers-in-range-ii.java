import java.util.Arrays;

class Solution {
    private String s;

    public int totalWaviness(int num1, int num2) {
        return (int) (count(num2) - count(num1 - 1));
    }

    public long totalWaviness(long num1, long num2) {
        return count(num2) - count(num1 - 1);
    }

    private long count(long x) {
        if (x < 0) return 0;
        s = Long.toString(x);
        int n = s.length();
        long[][][][][] cnt = new long[n][11][11][2][2];
        long[][][][][] wav = new long[n][11][11][2][2];
        
        for (int i = 0; i < n; i++) {
            for (int a = 0; a < 11; a++) {
                for (int b = 0; b < 11; b++) {
                    Arrays.fill(cnt[i][a][b][0], -1);
                    Arrays.fill(cnt[i][a][b][1], -1);
                    Arrays.fill(wav[i][a][b][0], -1);
                    Arrays.fill(wav[i][a][b][1], -1);
                }
            }
        }
        return dp(0, -1, -1, true, true, cnt, wav)[1];
    }

    private long[] dp(int i, int prev, int prev2, boolean zero, boolean tight, 
                      long[][][][][] cnt, long[][][][][] wav) {
        if (i == s.length()) {
            return new long[] {1, 0};
        }
        int pIdx = prev + 1;
        int p2Idx = prev2 + 1;
        int zIdx = zero ? 1 : 0;
        int tIdx = tight ? 1 : 0;

        if (cnt[i][pIdx][p2Idx][zIdx][tIdx] != -1) {
            return new long[] {cnt[i][pIdx][p2Idx][zIdx][tIdx], wav[i][pIdx][p2Idx][zIdx][tIdx]};
        }

        long totalCnt = 0;
        long totalW = 0;
        int mx = tight ? s.charAt(i) - '0' : 9;

        for (int d = 0; d <= mx; ++d) {
            boolean newTight = tight && (d == mx);
            boolean newZero = zero && (d == 0);
            int newPrev2 = prev2;
            int newPrev = prev;

            if (zero) {
                if (d != 0) {
                    newPrev = d;
                    newPrev2 = -1;
                }
            } else {
                newPrev2 = prev;
                newPrev = d;
            }

            long[] res = dp(i + 1, newPrev, newPrev2, newZero, newTight, cnt, wav);
            long newCnt = res[0];
            long nw = res[1];

            totalCnt += newCnt;
            if (!zero && prev2 != -1 && ((prev2 < prev && prev > d) || (prev2 > prev && prev < d))) {
                totalW += newCnt;
            }
            totalW += nw;
        }

        cnt[i][pIdx][p2Idx][zIdx][tIdx] = totalCnt;
        wav[i][pIdx][p2Idx][zIdx][tIdx] = totalW;
        return new long[] {totalCnt, totalW};
    }
}