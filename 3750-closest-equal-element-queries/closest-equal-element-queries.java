import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
    public List<Integer> solveQueries(int[] nums, int[] queries) {
        int n = nums.length;
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.computeIfAbsent(nums[i], k -> new ArrayList<>()).add(i);
        }

        int[] res = new int[n];
        for (List<Integer> list : map.values()) {
            int sz = list.size();
            if (sz == 1) {
                res[list.get(0)] = -1;
            } else {
                for (int p = 0; p < sz; p++) {
                    int curr = list.get(p);
                    int prev = list.get((p - 1 + sz) % sz);
                    int next = list.get((p + 1) % sz);

                    int d1 = Math.abs(curr - prev);
                    d1 = Math.min(d1, n - d1);

                    int d2 = Math.abs(curr - next);
                    d2 = Math.min(d2, n - d2);

                    res[curr] = Math.min(d1, d2);
                }
            }
        }

        List<Integer> ans = new ArrayList<>();
        for (int q : queries) {
            ans.add(res[q]);
        }

        return ans;
    }
}