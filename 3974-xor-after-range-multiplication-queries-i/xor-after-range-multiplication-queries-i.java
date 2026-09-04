import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    int MOD = 1000000007;

    public int xorAfterQueries(int[] nums, int[][] queries) {
        int n = nums.length;
        int B = Math.min(150, n + 1);
        
        long[] final_mult = new long[n];
        Arrays.fill(final_mult, 1L);
        int[] total_zeros = new int[n];
        int[] total_updates = new int[n];
        
        List<int[]>[] small_queries = new ArrayList[B];
        for (int k = 1; k < B; k++) {
            small_queries[k] = new ArrayList<>();
        }
        
        for (int[] q : queries) {
            int li = q[0], ri = q[1], ki = q[2];
            if (li > ri) continue;
            
            if (ki >= B) {
                long vi = (q[3] % MOD + MOD) % MOD;
                for (int idx = li; idx <= ri; idx += ki) {
                    final_mult[idx] = (final_mult[idx] * vi) % MOD;
                    if (vi == 0) {
                        total_zeros[idx]++;
                    }
                    total_updates[idx]++;
                }
            } else {
                small_queries[ki].add(q);
            }
        }
        
        int[] pref = new int[n];
        int[] zero_pref = new int[n];
        int[] count_pref = new int[n];
        
        for (int k = 1; k < B; k++) {
            if (small_queries[k].isEmpty()) continue;
            
            Arrays.fill(pref, 1);
            Arrays.fill(zero_pref, 0);
            Arrays.fill(count_pref, 0);
            
            for (int[] q : small_queries[k]) {
                int li = q[0], ri = q[1];
                long vi = (q[3] % MOD + MOD) % MOD;
                
                int count = (ri - li) / k;
                int next_idx = li + (count + 1) * k;
                
                count_pref[li]++;
                if (next_idx < n) {
                    count_pref[next_idx]--;
                }
                
                if (vi == 0) {
                    zero_pref[li]++;
                    if (next_idx < n) {
                        zero_pref[next_idx]--;
                    }
                } else {
                    pref[li] = (int)((pref[li] * vi) % MOD);
                    if (next_idx < n) {
                        pref[next_idx] = (int)((pref[next_idx] * inv(vi)) % MOD);
                    }
                }
            }
            
            for (int j = 0; j < n; j++) {
                if (j >= k) {
                    pref[j] = (int)(((long)pref[j] * pref[j - k]) % MOD);
                    zero_pref[j] += zero_pref[j - k];
                    count_pref[j] += count_pref[j - k];
                }
                
                if (count_pref[j] > 0) {
                    final_mult[j] = (final_mult[j] * pref[j]) % MOD;
                    total_zeros[j] += zero_pref[j];
                    total_updates[j] += count_pref[j];
                }
            }
        }
        
        int xorSum = 0;
        for (int j = 0; j < n; j++) {
            if (total_zeros[j] > 0) {
                final_mult[j] = 0;
            }
            
            long newVal = nums[j];
            if (total_updates[j] > 0) {
                newVal = (newVal % MOD + MOD) % MOD;
                newVal = (newVal * final_mult[j]) % MOD;
            }
            
            xorSum ^= (int) newVal;
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