class Solution {
    public long maxSumTrionic(int[] nums) {
        long prev_s1 = -1_000_000_000_000_000_000L;
        long prev_s2 = -1_000_000_000_000_000_000L;
        long prev_s3 = -1_000_000_000_000_000_000L;
        long ans = -1_000_000_000_000_000_000L;
        
        for (int i = 1; i < nums.length; i++) {
            long s1 = -1_000_000_000_000_000_000L;
            long s2 = -1_000_000_000_000_000_000L;
            long s3 = -1_000_000_000_000_000_000L;
            
            if (nums[i] > nums[i - 1]) {
                long start_s1 = (long) nums[i - 1] + nums[i];
                long extend_s1 = prev_s1 + nums[i];
                s1 = Math.max(start_s1, extend_s1);
                
                long start_s3 = prev_s2 + nums[i];
                long extend_s3 = prev_s3 + nums[i];
                s3 = Math.max(start_s3, extend_s3);
            } else if (nums[i] < nums[i - 1]) {
                long start_s2 = prev_s1 + nums[i];
                long extend_s2 = prev_s2 + nums[i];
                s2 = Math.max(start_s2, extend_s2);
            }
            
            ans = Math.max(ans, s3);
            
            prev_s1 = s1;
            prev_s2 = s2;
            prev_s3 = s3;
        }
        
        return ans;
    }
}