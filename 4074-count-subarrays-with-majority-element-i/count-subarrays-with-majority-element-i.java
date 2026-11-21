class Solution {
    public int countMajoritySubarrays(int[] nums, int target) {
        int n = nums.length;
        int[] freq = new int[2 * n + 1];
        
        int curr = n;
        freq[curr] = 1;
        
        long ans = 0;
        long lessCount = 0;
        
        for (int num : nums) {
            if (num == target) {
                lessCount += freq[curr];
                curr++;
            } else {
                curr--;
                lessCount -= freq[curr];
            }
            ans += lessCount;
            freq[curr]++;
        }
        
        return (int) ans;
    }
}