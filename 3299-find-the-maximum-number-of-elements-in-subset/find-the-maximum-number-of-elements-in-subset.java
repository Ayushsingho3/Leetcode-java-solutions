import java.util.HashMap;
import java.util.Map;

class Solution {
    public int maximumLength(int[] nums) {
        Map<Long, Integer> freq = new HashMap<>();
        for (int num : nums) {
            freq.merge((long) num, 1, Integer::sum);
        }

        int maxLen = 1;
        if (freq.containsKey(1L)) {
            int ones = freq.get(1L);
            maxLen = (ones % 2 == 1) ? ones : ones - 1;
        }

        for (long x : freq.keySet()) {
            if (x == 1L) {
                continue;
            }

            long curr = x;
            int len = 0;

            while (curr <= 1_000_000_000L && freq.containsKey(curr)) {
                int count = freq.get(curr);
                if (count >= 2) {
                    len += 2;
                    curr *= curr;
                } else {
                    len += 1;
                    break;
                }
            }

            if (len % 2 == 0) {
                len -= 1;
            }

            maxLen = Math.max(maxLen, len);
        }

        return maxLen;
    }
}