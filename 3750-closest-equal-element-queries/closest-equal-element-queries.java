import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
    public List<Integer> solveQueries(int[] nums, int[] queries) {
        int n = nums.length;
        Map<Integer, List<Integer>> map = new HashMap<>();
        int[] posInList = new int[n];

        for (int i = 0; i < n; i++) {
            List<Integer> list = map.computeIfAbsent(nums[i], k -> new ArrayList<>());
            posInList[i] = list.size();
            list.add(i);
        }

        List<Integer> answer = new ArrayList<>(queries.length);
        for (int q : queries) {
            List<Integer> list = map.get(nums[q]);
            int sz = list.size();

            if (sz <= 1) {
                answer.add(-1);
            } else {
                int pos = posInList[q];
                int prevIdx = list.get((pos - 1 + sz) % sz);
                int nextIdx = list.get((pos + 1) % sz);

                int distPrev = Math.abs(q - prevIdx);
                distPrev = Math.min(distPrev, n - distPrev);

                int distNext = Math.abs(q - nextIdx);
                distNext = Math.min(distNext, n - distNext);

                answer.add(Math.min(distPrev, distNext));
            }
        }

        return answer;
    }
}