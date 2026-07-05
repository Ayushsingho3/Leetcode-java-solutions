import java.util.Arrays;

class Solution {
    public int[] subarrayMajority(int[] nums, int[][] queries) {
        int n = nums.length;
        int q = queries.length;
        
        int[] unique = nums.clone();
        Arrays.sort(unique);
        int m = 0;
        for (int i = 0; i < n; i++) {
            if (i == 0 || unique[i] != unique[i - 1]) {
                unique[m++] = unique[i];
            }
        }
        
        int[] mappedNums = new int[n];
        for (int i = 0; i < n; i++) {
            mappedNums[i] = Arrays.binarySearch(unique, 0, m, nums[i]);
        }
        
        int U = 1;
        while (U < m) {
            U <<= 1;
        }
        
        int[] maxF = new int[2 * U];
        int[] bestVal = new int[2 * U];
        
        for (int i = 0; i < m; i++) {
            bestVal[U + i] = unique[i];
        }
        for (int i = m; i < U; i++) {
            bestVal[U + i] = Integer.MAX_VALUE;
        }
        for (int i = U - 1; i > 0; i--) {
            bestVal[i] = Math.min(bestVal[i << 1], bestVal[(i << 1) | 1]);
        }
        
        int B = (int) Math.max(1, n / Math.sqrt(q));
        
        long[] qs = new long[q];
        for (int i = 0; i < q; i++) {
            long b = queries[i][0] / B;
            long r_val = queries[i][1];
            if ((b & 1) == 1) {
                r_val = 200000L - r_val;
            }
            qs[i] = (b << 44) | (r_val << 24) | (long) i;
        }
        
        Arrays.sort(qs);
        
        int[] ans = new int[q];
        int curL = 0;
        int curR = -1;
        
        for (int i = 0; i < q; i++) {
            int id = (int) (qs[i] & 0xFFFFFFL);
            int L = queries[id][0];
            int R = queries[id][1];
            int threshold = queries[id][2];
            
            while (curL > L) {
                curL--;
                int p = mappedNums[curL];
                int idx = U + p;
                maxF[idx]++;
                idx >>= 1;
                while (idx > 0) {
                    int left = idx << 1;
                    int right = left | 1;
                    int mFL = maxF[left];
                    int mFR = maxF[right];
                    if (mFL > mFR) { maxF[idx] = mFL; bestVal[idx] = bestVal[left]; }
                    else if (mFL < mFR) { maxF[idx] = mFR; bestVal[idx] = bestVal[right]; }
                    else { maxF[idx] = mFL; int bL = bestVal[left]; int bR = bestVal[right]; bestVal[idx] = bL < bR ? bL : bR; }
                    idx >>= 1;
                }
            }
            while (curR < R) {
                curR++;
                int p = mappedNums[curR];
                int idx = U + p;
                maxF[idx]++;
                idx >>= 1;
                while (idx > 0) {
                    int left = idx << 1;
                    int right = left | 1;
                    int mFL = maxF[left];
                    int mFR = maxF[right];
                    if (mFL > mFR) { maxF[idx] = mFL; bestVal[idx] = bestVal[left]; }
                    else if (mFL < mFR) { maxF[idx] = mFR; bestVal[idx] = bestVal[right]; }
                    else { maxF[idx] = mFL; int bL = bestVal[left]; int bR = bestVal[right]; bestVal[idx] = bL < bR ? bL : bR; }
                    idx >>= 1;
                }
            }
            while (curL < L) {
                int p = mappedNums[curL];
                int idx = U + p;
                maxF[idx]--;
                idx >>= 1;
                while (idx > 0) {
                    int left = idx << 1;
                    int right = left | 1;
                    int mFL = maxF[left];
                    int mFR = maxF[right];
                    if (mFL > mFR) { maxF[idx] = mFL; bestVal[idx] = bestVal[left]; }
                    else if (mFL < mFR) { maxF[idx] = mFR; bestVal[idx] = bestVal[right]; }
                    else { maxF[idx] = mFL; int bL = bestVal[left]; int bR = bestVal[right]; bestVal[idx] = bL < bR ? bL : bR; }
                    idx >>= 1;
                }
                curL++;
            }
            while (curR > R) {
                int p = mappedNums[curR];
                int idx = U + p;
                maxF[idx]--;
                idx >>= 1;
                while (idx > 0) {
                    int left = idx << 1;
                    int right = left | 1;
                    int mFL = maxF[left];
                    int mFR = maxF[right];
                    if (mFL > mFR) { maxF[idx] = mFL; bestVal[idx] = bestVal[left]; }
                    else if (mFL < mFR) { maxF[idx] = mFR; bestVal[idx] = bestVal[right]; }
                    else { maxF[idx] = mFL; int bL = bestVal[left]; int bR = bestVal[right]; bestVal[idx] = bL < bR ? bL : bR; }
                    idx >>= 1;
                }
                curR--;
            }
            
            if (maxF[1] >= threshold) {
                ans[id] = bestVal[1];
            } else {
                ans[id] = -1;
            }
        }
        
        return ans;
    }
}