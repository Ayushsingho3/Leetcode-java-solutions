class Solution {
    public int[] resultArray(int[] nums) {
        int n = nums.length;
        if (n == 0) return new int[0];
        if (n == 1) return nums;
        
        java.util.List<Integer> arr1 = new java.util.ArrayList<>();
        java.util.List<Integer> arr2 = new java.util.ArrayList<>();
        
        arr1.add(nums[0]);
        arr2.add(nums[1]);
        
        for (int i = 2; i < n; i++) {
            if (arr1.get(arr1.size() - 1) > arr2.get(arr2.size() - 1)) {
                arr1.add(nums[i]);
            } else {
                arr2.add(nums[i]);
            }
        }
        
        int[] result = new int[n];
        int idx = 0;
        for (int num : arr1) {
            result[idx++] = num;
        }
        for (int num : arr2) {
            result[idx++] = num;
        }
        
        return result;
    }
}