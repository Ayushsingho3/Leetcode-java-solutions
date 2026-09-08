class Solution {
    public int countCommas(int n) {
        int ans = 0;
        for (long base = 1000; base <= n; base *= 1000) {
            ans += n - base + 1;
        }
        return ans;
    }
}