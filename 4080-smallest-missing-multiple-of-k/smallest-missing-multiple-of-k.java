class Solution {
    public int missingMultiple(int[] nums, int k) {
        int n = nums.length;
        boolean[] present = new boolean[n + 2];
        
        for (int num : nums) {
            if (num > 0 && num % k == 0) {
                int mult = num / k;
                if (mult <= n + 1) {
                    present[mult] = true;
                }
            }
        }
        
        for (int i = 1; i <= n + 1; i++) {
            if (!present[i]) {
                return i * k;
            }
        }
        
        return k;
    }
}