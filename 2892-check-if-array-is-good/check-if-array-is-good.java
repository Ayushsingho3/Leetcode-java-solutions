import java.util.HashMap;
import java.util.Map;

class Solution {
    public boolean isGood(int[] nums) {
        int N = nums.length;
        int n = N - 1;

        Map<Integer, Integer> count = new HashMap<>();
        for (int num : nums) {
            count.put(num, count.getOrDefault(num, 0) + 1);
        }

        for (int i = 1; i < n; i++) {
            if (count.getOrDefault(i, 0) != 1) {
                return false;
            }
        }

        return count.getOrDefault(n, 0) == 2;
    }
}