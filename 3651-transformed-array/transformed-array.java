class Solution {
    public int[] constructTransformedArray(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];
        
        for (int i = 0; i < n; i++) {
            int move = nums[i] % n;
            int landingIndex = (i + move + n) % n;
            result[i] = nums[landingIndex];
        }
        
        return result;
    }
}