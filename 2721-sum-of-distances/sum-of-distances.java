import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
    public long[] distance(int[] nums) {
        int n = nums.length;
        long[] arr = new long[n];
        Map<Integer, List<Integer>> map = new HashMap<>();

        for (int i = 0; i < n; i++) {
            map.computeIfAbsent(nums[i], k -> new ArrayList<>()).add(i);
        }

        for (List<Integer> list : map.values()) {
            int k = list.size();
            long rightSum = 0;
            for (int idx : list) {
                rightSum += idx;
            }

            long leftSum = 0;
            for (int p = 0; p < k; p++) {
                long idx = list.get(p);
                rightSum -= idx;
                long rightCount = k - 1 - p;
                long leftCount = p;

                arr[(int) idx] = (leftCount * idx - leftSum) + (rightSum - rightCount * idx);

                leftSum += idx;
            }
        }

        return arr;
    }
}