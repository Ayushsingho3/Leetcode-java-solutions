class Solution {
    public long maxSumTrionic(int[] nums) {
        long MIN_INF = -1_000_000_000_000_000_000L; 
        
        long dp0 = MIN_INF;
        long dp1 = MIN_INF;
        long dp2 = MIN_INF;
        
        long maxSum = MIN_INF;
        
        for (int i = 1; i < nums.length; i++) {
            long next_dp0 = MIN_INF;
            long next_dp1 = MIN_INF;
            long next_dp2 = MIN_INF;
            
            long val = nums[i];
            
            if (nums[i] > nums[i - 1]) {
                next_dp0 = Math.max(dp0 + val, (long) nums[i - 1] + val);
                
                if (dp1 != MIN_INF) {
                    next_dp2 = Math.max(next_dp2, dp1 + val);
                }
                if (dp2 != MIN_INF) {
                    next_dp2 = Math.max(next_dp2, dp2 + val);
                }
                
            } else if (nums[i] < nums[i - 1]) {
                if (dp0 != MIN_INF) {
                    next_dp1 = Math.max(next_dp1, dp0 + val);
                }
                if (dp1 != MIN_INF) {
                    next_dp1 = Math.max(next_dp1, dp1 + val);
                }
            }
            
            dp0 = next_dp0;
            dp1 = next_dp1;
            dp2 = next_dp2;
            
            if (dp2 != MIN_INF) {
                maxSum = Math.max(maxSum, dp2);
            }
        }
        
        return maxSum == MIN_INF ? 0 : maxSum;
    }
}