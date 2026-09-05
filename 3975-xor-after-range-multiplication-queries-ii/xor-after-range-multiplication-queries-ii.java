import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    private static final int MOD = 1000000007;

    public int xorAfterQueries(int[] nums, int[][] queries) {
        int[][] bravexuneth = queries;
        int n = nums.length;
        int B = 80;

        long[] final_mult = new long[n];
        Arrays.fill(final_mult, 1L);

        List<int[]>[] small_queries = new ArrayList[B];
        for (int k = 1; k < B; k++) {
            small_queries[k] = new ArrayList<>();
        }

        for (int[] q : bravexuneth) {
            int li = q[0];
            int ri = q[1];
            int ki = q[2];
            long vi = (q[3] % MOD + MOD) % MOD;

            if (li > ri) continue;

            if (ki >= B) {
                for (int idx = li; idx <= ri; idx += ki) {
                    final_mult[idx] = (final_mult[idx] * vi) % MOD;
                }
            } else {
                small_queries[ki].add(q);
            }
        }

        long[] pref = new long[n];
        int[] zero_pref = new int[n];

        for (int k = 1; k < B; k++) {
            if (small_queries[k].isEmpty()) continue;

            Arrays.fill(pref, 1L);
            Arrays.fill(zero_pref, 0);

            for (int[] q : small_queries[k]) {
                int li = q[0];
                int ri = q[1];
                long vi = (q[3] % MOD + MOD) % MOD;

                int count = (ri - li) / k;
                int next_idx = li + (count + 1) * k;

                if (vi == 0) {
                    zero_pref[li]++;
                    if (next_idx < n) {
                        zero_pref[next_idx]--;
                    }
                } else {
                    pref[li] = (pref[li] * vi) % MOD;
                    if (next_idx < n) {
                        pref[next_idx] = (pref[next_idx] * inv(vi)) % MOD;
                    }
                }
            }

            for (int j = 0; j < n; j++) {
                if (j >= k) {
                    pref[j] = (pref[j] * pref[j - k]) % MOD;
                    zero_pref[j] += zero_pref[j - k];
                }

                if (zero_pref[j] > 0) {
                    final_mult[j] = 0;
                } else if (pref[j] != 1) {
                    final_mult[j] = (final_mult[j] * pref[j]) % MOD;
                }
            }
        }

        int xorSum = 0;
        for (int j = 0; j < n; j++) {
            long val = ((long) nums[j] * final_mult[j]) % MOD;
            xorSum ^= (int) val;
        }

        return xorSum;
    }

    private long inv(long a) {
        return power(a, MOD - 2);
    }

    private long power(long a, long b) {
        long res = 1;
        a %= MOD;
        while (b > 0) {
            if ((b & 1) != 0) {
                res = (res * a) % MOD;
            }
            a = (a * a) % MOD;
            b >>= 1;
        }
        return res;
    }
}