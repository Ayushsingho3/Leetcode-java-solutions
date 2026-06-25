class Solution {
    public int countMajoritySubarrays(int[] nums, int target) {
        int n = nums.length;
        int[] bit = new int[2 * n + 2];
        int count = 0; 
        int prefixSum = 0;
        int shift = n + 1;
        
        update(bit, shift, 1);
        
        for (int num : nums) {
            prefixSum += (num == target) ? 1 : -1;
            count += query(bit, prefixSum - 1 + shift);
            update(bit, prefixSum + shift, 1);
        }
        
        return count;
    }
    
    private void update(int[] bit, int index, int val) {
        for (; index < bit.length; index += index & (-index)) {
            bit[index] += val;
        }
    }
    
    private int query(int[] bit, int index) {
        int sum = 0;
        for (; index > 0; index -= index & (-index)) {
            sum += bit[index];
        }
        return sum;
    }
}