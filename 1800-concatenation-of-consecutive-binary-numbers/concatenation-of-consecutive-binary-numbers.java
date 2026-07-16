class Solution {
    public int concatenatedBinary(int n) {
        long result = 0;
        long MOD = 1000000007;
        int length = 0; 
        
        for (int i = 1; i <= n; i++) {
            if ((i & (i - 1)) == 0) {
                length++;
            }
            
            result = ((result << length) | i) % MOD;
        }
        
        return (int) result;
    }
}