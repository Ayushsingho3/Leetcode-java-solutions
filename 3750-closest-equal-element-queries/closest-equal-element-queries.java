import java.util.*;

class Solution {
    public List<Integer> solveQueries(int[] nums, int[] queries) {
        int n = nums.length;
        Map<Integer, List<Integer>> pos = new HashMap<>();
        
        for (int i = 0; i < n; i++) {
            pos.computeIfAbsent(nums[i], k -> new ArrayList<>()).add(i);
        }
        
        int[] minDist = new int[n];
        Arrays.fill(minDist, -1);
        
        for (List<Integer> list : pos.values()) {
            int k = list.size();
            if (k > 1) {
                for (int m = 0; m < k; m++) {
                    int current = list.get(m);
                    int prev = list.get((m - 1 + k) % k);
                    int next = list.get((m + 1) % k);
                    
                    int d1 = Math.abs(current - prev);
                    d1 = Math.min(d1, n - d1);
                    
                    int d2 = Math.abs(current - next);
                    d2 = Math.min(d2, n - d2);
                    
                    minDist[current] = Math.min(d1, d2);
                }
            }
        }
        
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < queries.length; i++) {
            ans.add(minDist[queries[i]]);
        }
        
        return ans;
    }
}