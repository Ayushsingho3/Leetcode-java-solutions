import java.util.Arrays;

class Solution {
    public int xorAfterQueries(int[] nums, int[][] queries) {
        int n = nums.length;
        int q = queries.length;
        long MOD = 1_000_000_007;
        
        int B = (int) Math.sqrt(n);
        if (B < 1) B = 1;
        
        int[] head = new int[B + 1];
        Arrays.fill(head, -1);
        int[] next = new int[q];
        
        for (int i = 0; i < q; i++) {
            int k = queries[i][2];
            if (k <= B) {
                next[i] = head[k];
                head[k] = i;
            }
        }
        
        long[] ans_mult = new long[n];
        Arrays.fill(ans_mult, 1);
        int[] zero_count = new int[n];
        
        long[] cur_mult = new long[n];
        int[] cur_zeros = new int[n];
        
        for (int k = 1; k <= B; k++) {
            if (head[k] == -1) continue;
            
            Arrays.fill(cur_mult, 1);
            Arrays.fill(cur_zeros, 0);
            
            int p = head[k];
            while (p != -1) {
                int l = queries[p][0];
                int r = queries[p][1];
                long v = (queries[p][3] % MOD + MOD) % MOD;
                
                int steps = (r - l) / k;
                int last_idx = l + steps * k;
                
                if (v == 0) {
                    cur_zeros[l]++;
                    if (last_idx + k < n) {
                        cur_zeros[last_idx + k]--;
                    }
                } else {
                    cur_mult[l] = (cur_mult[l] * v) % MOD;
                    if (last_idx + k < n) {
                        cur_mult[last_idx + k] = (cur_mult[last_idx + k] * inv(v, MOD)) % MOD;
                    }
                }
                p = next[p];
            }
            
            for (int i = 0; i < n; i++) {
                if (i >= k) {
                    cur_zeros[i] += cur_zeros[i - k];
                    cur_mult[i] = (cur_mult[i] * cur_mult[i - k]) % MOD;
                }
                zero_count[i] += cur_zeros[i];
                ans_mult[i] = (ans_mult[i] * cur_mult[i]) % MOD;
            }
        }
        
        for (int i = 0; i < q; i++) {
            int k = queries[i][2];
            if (k > B) {
                int l = queries[i][0];
                int r = queries[i][1];
                long v = (queries[i][3] % MOD + MOD) % MOD;
                
                for (int idx = l; idx <= r; idx += k) {
                    if (v == 0) {
                        zero_count[idx]++;
                    } else {
                        ans_mult[idx] = (ans_mult[idx] * v) % MOD;
                    }
                }
            }
        }
        
        int xor_sum = 0;
        for (int i = 0; i < n; i++) {
            long final_mult = zero_count[i] > 0 ? 0 : ans_mult[i];
            long final_val = (nums[i] * final_mult) % MOD;
            xor_sum ^= (int) final_val;
        }
        
        return xor_sum;
    }
    
    private long inv(long a, long mod) {
        long res = 1;
        long exp = mod - 2;
        while (exp > 0) {
            if ((exp & 1) != 0) res = (res * a) % mod;
            a = (a * a) % mod;
            exp >>= 1;
        }
        return res;
    }
}