import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
    public List<Integer> solveQueries(int[] nums, int[] queries) {
        int n = nums.length;
        int[] minDistForIndex = new int[n];

        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.computeIfAbsent(nums[i], k -> new ArrayList<>()).add(i);
        }

        for (List<Integer> list : map.values()) {
            int k = list.size();
            if (k == 1) {
                minDistForIndex[list.get(0)] = -1;
            } else {
                for (int p = 0; p < k; p++) {
                    int curr = list.get(p);
                    int prev = list.get((p - 1 + k) % k);
                    int next = list.get((p + 1) % k);

                    int distPrev = Math.abs(curr - prev);
                    distPrev = Math.min(distPrev, n - distPrev);

                    int distNext = Math.abs(curr - next);
                    distNext = Math.min(distNext, n - distNext);

                    minDistForIndex[curr] = Math.min(distPrev, distNext);
                }
            }
        }

        List<Integer> ans = new ArrayList<>(queries.length);
        for (int q : queries) {
            ans.add(minDistForIndex[q]);
        }

        return ans;
    }
}