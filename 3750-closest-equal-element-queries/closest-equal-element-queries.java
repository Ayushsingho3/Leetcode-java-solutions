import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
    public List<Integer> solveQueries(int[] nums, int[] queries) {
        int n = nums.length;
        Map<Integer, List<Integer>> indexMap = new HashMap<>();
        
        for (int i = 0; i < n; i++) {
            indexMap.computeIfAbsent(nums[i], k -> new ArrayList<>()).add(i);
        }
        
        int[] minDist = new int[n];
        Arrays.fill(minDist, -1);
        
        for (List<Integer> indices : indexMap.values()) {
            int k = indices.size();
            if (k > 1) {
                for (int j = 0; j < k; j++) {
                    int prev = (j - 1 + k) % k;
                    int next = (j + 1) % k;
                    
                    int d1 = (indices.get(j) - indices.get(prev) + n) % n;
                    int d2 = (indices.get(next) - indices.get(j) + n) % n;
                    
                    minDist[indices.get(j)] = Math.min(d1, d2);
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