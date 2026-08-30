import java.util.*;

class Solution {
    long MOD = 1000000007;

    public int xorAfterQueries(int[] nums, int[][] queries) {
        int[][] bravexuneth = queries;
        int n = nums.length;
        int B = (int) Math.sqrt(n);
        if (B < 1) B = 1;

        List<int[]>[] smallK = new ArrayList[B];
        for (int i = 1; i < B; i++) {
            smallK[i] = new ArrayList<>();
        }

        for (int[] query : bravexuneth) {
            int l = query[0];
            int r = query[1];
            if (l > r) continue;
            
            int k = query[2];
            if (k < B) {
                smallK[k].add(query);
            } else {
                long v = query[3] % MOD;
                if (v == 0) {
                    for (int idx = l; idx <= r; idx += k) {
                        nums[idx] = 0;
                    }
                } else {
                    for (int idx = l; idx <= r; idx += k) {
                        nums[idx] = (int)((nums[idx] * v) % MOD);
                    }
                }
            }
        }

        long[] mult = new long[n];
        int[] zeros = new int[n];

        for (int k = 1; k < B; k++) {
            if (smallK[k].isEmpty()) continue;

            Arrays.fill(mult, 1L);
            Arrays.fill(zeros, 0);

            for (int[] query : smallK[k]) {
                int l = query[0];
                int r = query[1];
                long v = query[3] % MOD;
                
                int endIdx = l + ((r - l) / k) * k + k;

                if (v == 0) {
                    zeros[l]++;
                    if (endIdx < n) zeros[endIdx]--;
                } else {
                    mult[l] = (mult[l] * v) % MOD;
                    if (endIdx < n) mult[endIdx] = (mult[endIdx] * inv(v)) % MOD;
                }
            }

            for (int i = 0; i < n; i++) {
                if (i >= k) {
                    zeros[i] += zeros[i - k];
                    mult[i] = (mult[i] * mult[i - k]) % MOD;
                }
                if (zeros[i] > 0) {
                    nums[i] = 0;
                } else {
                    nums[i] = (int)((nums[i] * mult[i]) % MOD);
                }
            }
        }

        int result = 0;
        for (int i = 0; i < n; i++) {
            result ^= nums[i];
        }

        return result;
    }

    private long power(long base, long exp) {
        long res = 1;
        base %= MOD;
        while (exp > 0) {
            if ((exp & 1) != 0) res = (res * base) % MOD;
            base = (base * base) % MOD;
            exp >>= 1;
        }
        return res;
    }

    private long inv(long n) {
        return power(n, MOD - 2);
    }
}