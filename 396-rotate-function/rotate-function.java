class Solution {
    public int maxRotateFunction(int[] nums) {
        int n = nums.length;
        long sum = 0;
        long f = 0;

        for (int i = 0; i < n; i++) {
            sum += nums[i];
            f += (long) i * nums[i];
        }

        long maxF = f;

        for (int k = 1; k < n; k++) {
            f = f + sum - (long) n * nums[n - k];
            maxF = Math.max(maxF, f);
        }

        return (int) maxF;
    }
}