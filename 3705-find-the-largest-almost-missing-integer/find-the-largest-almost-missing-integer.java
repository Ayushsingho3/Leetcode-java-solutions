class Solution {
    public int largestInteger(int[] nums, int k) {
        java.util.Map<Integer, java.util.List<Integer>> pos = new java.util.HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            pos.computeIfAbsent(nums[i], x -> new java.util.ArrayList<>()).add(i);
        }
        
        int ans = -1;
        int maxJ = nums.length - k;
        
        for (java.util.Map.Entry<Integer, java.util.List<Integer>> entry : pos.entrySet()) {
            int val = entry.getKey();
            java.util.List<Integer> indices = entry.getValue();
            
            int total = 0;
            int currL = -1;
            int currR = -2;
            
            for (int i : indices) {
                int L = Math.max(0, i - k + 1);
                int R = Math.min(maxJ, i);
                
                if (currR < L) {
                    if (currL != -1) {
                        total += currR - currL + 1;
                    }
                    currL = L;
                    currR = R;
                } else {
                    currR = Math.max(currR, R);
                }
            }
            
            if (currL != -1) {
                total += currR - currL + 1;
            }
            
            if (total == 1) {
                ans = Math.max(ans, val);
            }
        }
        
        return ans;
    }
}