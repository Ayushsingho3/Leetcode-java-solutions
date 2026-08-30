import java.util.HashMap;
import java.util.Map;

class Solution {
    public int minMirrorPairDistance(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        int minDistance = Integer.MAX_VALUE;
        
        for (int i = 0; i < nums.length; i++) {
            int current = nums[i];
            
            if (map.containsKey(current)) {
                minDistance = Math.min(minDistance, i - map.get(current));
            }
            
            int rev = reverse(nums[i]);
            map.put(rev, i);
        }
        
        return minDistance == Integer.MAX_VALUE ? -1 : minDistance;
    }
    
    private int reverse(int x) {
        int rev = 0;
        int temp = Math.abs(x);
        
        while (temp > 0) {
            rev = rev * 10 + temp % 10;
            temp /= 10;
        }
        
        return x < 0 ? -rev : rev;
    }
}