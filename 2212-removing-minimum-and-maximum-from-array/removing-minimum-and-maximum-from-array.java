class Solution {
    public int minimumDeletions(int[] nums) {
        int n = nums.length;
        if (n <= 2) {
            return n;
        }
        
        int minIdx = 0;
        int maxIdx = 0;
        
        for (int i = 1; i < n; i++) {
            if (nums[i] < nums[minIdx]) {
                minIdx = i;
            }
            if (nums[i] > nums[maxIdx]) {
                maxIdx = i;
            }
        }
        
        int i = Math.min(minIdx, maxIdx);
        int j = Math.max(minIdx, maxIdx);
        
        int option1 = j + 1;
        int option2 = n - i;
        int option3 = i + 1 + n - j;
        
        return Math.min(option1, Math.min(option2, option3));
    }
}