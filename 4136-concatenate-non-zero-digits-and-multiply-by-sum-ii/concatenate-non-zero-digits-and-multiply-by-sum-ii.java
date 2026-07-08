class Solution {
    public int[] sumAndMultiply(String s, int[][] queries) {
        int m = s.length();
        int q = queries.length;
        int MOD = 1000000007;

        int nonZeroCount = 0;
        for (int i = 0; i < m; i++) {
            if (s.charAt(i) != '0') {
                nonZeroCount++;
            }
        }

        int[] prefCount = new int[m + 1];
        long[] prefSum = new long[nonZeroCount + 1];
        long[] prefVal = new long[nonZeroCount + 1];
        long[] power10 = new long[nonZeroCount + 1];
        
        power10[0] = 1;
        
        int idx = 0;
        for (int i = 0; i < m; i++) {
            char c = s.charAt(i);
            prefCount[i + 1] = prefCount[i];
            
            if (c != '0') {
                int digit = c - '0';
                prefSum[idx + 1] = prefSum[idx] + digit;
                prefVal[idx + 1] = (prefVal[idx] * 10 + digit) % MOD;
                power10[idx + 1] = (power10[idx] * 10) % MOD;
                
                prefCount[i + 1]++;
                idx++;
            }
        }

        int[] ans = new int[q];
        for (int i = 0; i < q; i++) {
            int l = queries[i][0];
            int r = queries[i][1];

            int L = prefCount[l];
            int R = prefCount[r + 1] - 1;

            if (L > R) {
                ans[i] = 0;
            } else {
                int len = R - L + 1;
                
                long sum = prefSum[R + 1] - prefSum[L];
                
                long val = (prefVal[R + 1] - (prefVal[L] * power10[len]) % MOD) % MOD;
                if (val < 0) {
                    val += MOD;
                }
                
                long sumMod = sum % MOD;
                ans[i] = (int) ((val * sumMod) % MOD);
            }
        }

        return ans;
    }
}