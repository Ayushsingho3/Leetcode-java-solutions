class Solution {
    public int[] constructTransformedArray(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];
        
        for (int i = 0; i < n; i++) {
            int step = nums[i] % n;
            int targetIndex = (i + step + n) % n;
            result[i] = nums[targetIndex];
        }
        
        return result;
    }
}