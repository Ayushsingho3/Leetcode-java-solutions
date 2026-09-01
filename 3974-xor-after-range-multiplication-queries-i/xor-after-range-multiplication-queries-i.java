import java.util.Arrays;

class Solution {
    int MOD = 1000000007;

    public int xorAfterQueries(int[] nums, int[][] queries) {
        int n = nums.length;
        int B = Math.min(100, n + 1);
        
        long[] mult = new long[n];
        Arrays.fill(mult, 1L);
        
        int[][] pref = new int[B][n];
        int[][] zero_pref = new int[B][n];
        for(int i = 0; i < B; i++) {
            Arrays.fill(pref[i], 1);
        }
        
        for (int[] q : queries) {
            int li = q[0], ri = q[1], ki = q[2];
            long vi = (q[3] % MOD + MOD) % MOD;
            
            if (ki >= B) {
                for (int idx = li; idx <= ri; idx += ki) {
                    mult[idx] = (mult[idx] * vi) % MOD;
                }
            } else {
                int count = (ri - li) / ki;
                int next_idx = li + (count + 1) * ki;
                
                if (vi == 0) {
                    zero_pref[ki][li] += 1;
                    if (next_idx < n) {
                        zero_pref[ki][next_idx] -= 1;
                    }
                } else {
                    pref[ki][li] = (int)((pref[ki][li] * vi) % MOD);
                    if (next_idx < n) {
                        pref[ki][next_idx] = (int)((pref[ki][next_idx] * inv(vi)) % MOD);
                    }
                }
            }
        }
        
        for (int k = 1; k < B; k++) {
            for (int j = 0; j < n; j++) {
                if (j >= k) {
                    pref[k][j] = (int)(((long)pref[k][j] * pref[k][j - k]) % MOD);
                    zero_pref[k][j] += zero_pref[k][j - k];
                }
            }
        }
        
        int xorSum = 0;
        for (int j = 0; j < n; j++) {
            long finalMult = mult[j];
            long zeros = 0;
            
            for (int k = 1; k < B; k++) {
                finalMult = (finalMult * pref[k][j]) % MOD;
                zeros += zero_pref[k][j];
            }
            
            if (zeros > 0) {
                finalMult = 0;
            }
            
            long newVal = (nums[j] * finalMult) % MOD;
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