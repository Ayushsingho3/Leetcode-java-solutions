class Solution {
    public boolean isTrionic(int[] nums) {
        if (nums.length < 4) {
            return false;
        }
        
        int phase = 0;
        
        for (int i = 1; i < nums.length; i++) {
            if (phase == 0) {
                if (nums[i] > nums[i - 1]) {
                    continue;
                } else if (nums[i] < nums[i - 1]) {
                    if (i == 1) {
                        return false;
                    }
                    phase = 1;
                } else {
                    return false;
                }
            } else if (phase == 1) {
                if (nums[i] < nums[i - 1]) {
                    continue;
                } else if (nums[i] > nums[i - 1]) {
                    phase = 2;
                } else {
                    return false;
                }
            } else if (phase == 2) {
                if (nums[i] > nums[i - 1]) {
                    continue;
                } else {
                    return false;
                }
            }
        }
        
        return phase == 2;
    }
}