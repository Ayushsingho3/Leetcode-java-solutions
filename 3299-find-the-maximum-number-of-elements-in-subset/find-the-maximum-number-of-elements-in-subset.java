import java.util.*;

class Solution {
    public int maximumLength(int[] nums) {
        Map<Long, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put((long) num, map.getOrDefault((long) num, 0) + 1);
        }
        
        int maxLen = 1;
        
        if (map.containsKey(1L)) {
            int ones = map.get(1L);
            if (ones % 2 == 0) {
                ones--;
            }
            maxLen = Math.max(maxLen, ones);
        }
        
        for (long x : map.keySet()) {
            if (x == 1L) continue;
            
            long curr = x;
            int currentLength = 0;
            
            while (map.getOrDefault(curr, 0) > 1) {
                currentLength += 2;
                curr *= curr;
            }
            
            if (map.containsKey(curr)) {
                currentLength += 1;
            } else {
                currentLength -= 1;
            }
            
            maxLen = Math.max(maxLen, currentLength);
        }
        
        return maxLen;
    }
}