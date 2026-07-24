class Solution {
    public int uniqueXorTriplets(int[] nums) {
        int maxEl = 0;
        for (int num : nums) {
            maxEl = Math.max(maxEl, num);
        }
        
        int limit = 1;
        while (limit <= maxEl) {
            limit <<= 1;
        }
        
        boolean[] pairXor = new boolean[limit];
        boolean[] tripletXor = new boolean[limit];
        
        for (int i = 0; i < nums.length; i++) {
            for (int j = i; j < nums.length; j++) {
                pairXor[nums[i] ^ nums[j]] = true;
            }
        }
        
        for (int i = 0; i < limit; i++) {
            if (pairXor[i]) {
                for (int num : nums) {
                    tripletXor[i ^ num] = true;
                }
            }
        }
        
        int count = 0;
        for (int i = 0; i < limit; i++) {
            if (tripletXor[i]) {
                count++;
            }
        }
        
        return count;
    }
}