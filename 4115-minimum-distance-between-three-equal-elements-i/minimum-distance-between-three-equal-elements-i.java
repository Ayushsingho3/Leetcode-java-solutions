import java.util.HashMap;
import java.util.Map;

class Solution {
    public int minimumDistance(int[] nums) {
        Map<Integer, Integer> lastSeen = new HashMap<>();
        Map<Integer, Integer> secondLastSeen = new HashMap<>();
        
        int minKMinusI = Integer.MAX_VALUE;
        
        for (int i = 0; i < nums.length; i++) {
            int val = nums[i];
            
            if (lastSeen.containsKey(val)) {
                if (secondLastSeen.containsKey(val)) {
                    minKMinusI = Math.min(minKMinusI, i - secondLastSeen.get(val));
                }
                secondLastSeen.put(val, lastSeen.get(val));
            }
            lastSeen.put(val, i);
        }
        
        return minKMinusI == Integer.MAX_VALUE ? -1 : minKMinusI * 2;
    }
}