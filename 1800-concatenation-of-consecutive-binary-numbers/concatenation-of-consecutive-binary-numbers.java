class Solution {
    public int concatenatedBinary(int n) {
        long MOD = 1_000_000_007;
        long ans = 0;
        int length = 0;
        
        for (int i = 1; i <= n; i++) {
            if ((i & (i - 1)) == 0) {
                length++;
            }
            ans = ((ans << length) | i) % MOD;
        }
        
        return (int) ans;
    }
}