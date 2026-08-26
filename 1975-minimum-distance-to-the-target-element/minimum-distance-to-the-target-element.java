class Solution {
    public int getMinDistance(int[] nums, int target, int start) {
        int minDistance = Integer.MAX_VALUE;
        
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == target) {
                int diff = Math.abs(i - start);
                if (diff < minDistance) {
                    minDistance = diff;
                }
            }
        }
        
        return minDistance;
    }
}