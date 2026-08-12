class Solution {
    public int maxSubarrayLength(int[] nums, int k) {
        int left = 0;
        int maxLen = 0;
        java.util.Map<Integer, Integer> map = new java.util.HashMap<>();
        
        for (int right = 0; right < nums.length; right++) {
            map.put(nums[right], map.getOrDefault(nums[right], 0) + 1);
            
            while (map.get(nums[right]) > k) {
                map.put(nums[left], map.get(nums[left]) - 1);
                left++;
            }
            
            maxLen = Math.max(maxLen, right - left + 1);
        }
        
        return maxLen;
    }
}