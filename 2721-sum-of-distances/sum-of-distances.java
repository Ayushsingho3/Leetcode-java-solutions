import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
    public long[] distance(int[] arr) {
        int n = arr.length;
        long[] result = new long[n];
        Map<Integer, List<Integer>> map = new HashMap<>();

        for (int i = 0; i < n; i++) {
            map.computeIfAbsent(arr[i], k -> new ArrayList<>()).add(i);
        }

        for (List<Integer> list : map.values()) {
            int k = list.size();
            long totalSum = 0;
            for (int idx : list) {
                totalSum += idx;
            }

            long prefixSum = 0;
            for (int i = 0; i < k; i++) {
                long curr = list.get(i);
                long left = (long) i * curr - prefixSum;
                long right = (totalSum - prefixSum - curr) - (long) (k - 1 - i) * curr;

                result[(int) curr] = left + right;
                prefixSum += curr;
            }
        }

        return result;
    }
}