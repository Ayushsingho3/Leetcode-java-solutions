class Solution {
    public long countMajoritySubarrays(int[] nums, int target) {
        int n = nums.length;
        int offset = n + 1; 
        
        int[] bit = new int[2 * n + 2];
        
        long totalSubarrays = 0;
        int currentSum = 0;
        
        update(bit, currentSum + offset, 1);
        
        for (int num : nums) {
            if (num == target) {
                currentSum++;
            } else {
                currentSum--;
            }
            
            totalSubarrays += query(bit, currentSum - 1 + offset);
            update(bit, currentSum + offset, 1);
        }
        
        return totalSubarrays;
    }
    
    private void update(int[] bit, int index, int delta) {
        for (; index < bit.length; index += index & (-index)) {
            bit[index] += delta;
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