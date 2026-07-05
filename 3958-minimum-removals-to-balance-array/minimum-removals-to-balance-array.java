import java.util.Arrays;

class Solution {
    public int minRemoval(int[] nums, int k) {
        Arrays.sort(nums);
        
        int maxKept = 0;
        int left = 0;
        
        for (int right = 0; right < nums.length; right++) {
            while (nums[right] > (long) k * nums[left]) {
                left++;
            }
            
            maxKept = Math.max(maxKept, right - left + 1);
        }
        
        return nums.length - maxKept;
    }
}