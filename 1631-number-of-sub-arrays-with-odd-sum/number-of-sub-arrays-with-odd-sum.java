class Solution {
    public int numOfSubarrays(int[] arr) {
        long odd = 0, even = 1;
        int sum = 0;
        long ans = 0;
        int MOD = 1_000_000_007;

        for (int x : arr) {
            sum += x;
            if ((sum % 2 + 2) % 2 != 0) {
                ans = (ans + even) % MOD;
                odd++;
            } else {
                ans = (ans + odd) % MOD;
                even++;
            }
        }

        return (int) ans;
    }
}