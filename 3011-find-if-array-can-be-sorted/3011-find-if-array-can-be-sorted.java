class Solution {
    public boolean canSortArray(int[] nums) {
        int prevMax = Integer.MIN_VALUE;
        int i = 0;
        int n = nums.length;
        
        while (i < n) {
            int currentBits = Integer.bitCount(nums[i]);
            int currentMin = nums[i];
            int currentMax = nums[i];
            
            int j = i + 1;
            while (j < n && Integer.bitCount(nums[j]) == currentBits) {
                currentMin = Math.min(currentMin, nums[j]);
                currentMax = Math.max(currentMax, nums[j]);
                j++;
            }
            
            if (currentMin < prevMax) {
                return false;
            }
            
            prevMax = currentMax;
            i = j;
        }
        
        return true;
    }
}