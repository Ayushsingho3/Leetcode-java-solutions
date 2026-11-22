class Solution {
    public int maximumElementAfterDecrementingAndRearranging(int[] arr) {
        int n = arr.length;
        int[] count = new int[n + 1];

        for (int num : arr) {
            count[Math.min(num, n)]++;
        }

        int ans = 1;
        for (int v = 2; v <= n; v++) {
            ans = Math.min(ans + count[v], v);
        }

        return ans;
    }
}