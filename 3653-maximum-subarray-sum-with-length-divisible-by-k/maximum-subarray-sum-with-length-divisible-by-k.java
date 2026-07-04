class Solution {
    public long maxSubarraySum(int[] nums, int k) {
        int n = nums.length;
        long[] minPref = new long[k];
        long INF = Long.MAX_VALUE / 2; 
        
        for (int i = 0; i < k; i++) {
            minPref[i] = INF;
        }
        minPref[0] = 0;
        
        long currentPref = 0;
        long maxSum = -INF;
        
        for (int i = 1; i <= n; i++) {
            currentPref += nums[i - 1];
            int rem = i % k;
            
            if (minPref[rem] != INF) {
                maxSum = Math.max(maxSum, currentPref - minPref[rem]);
            }
            
            minPref[rem] = Math.min(minPref[rem], currentPref);
        }
        
        return maxSum;
    }
}