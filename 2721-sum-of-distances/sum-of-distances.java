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
            if (k <= 1) {
                continue;
            }

            long totalSum = 0;
            for (int idx : list) {
                totalSum += idx;
            }

            long prefixSum = 0;
            for (int p = 0; p < k; p++) {
                long idx = list.get(p);
                long left = (long) p * idx - prefixSum;
                long right = (totalSum - prefixSum - idx) - (long) (k - 1 - p) * idx;
                arr[(int) idx] = left + right;
                prefixSum += idx;
            }
        }

        return arr;
    }
}