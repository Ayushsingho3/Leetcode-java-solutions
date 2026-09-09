import java.util.HashMap;
import java.util.Map;

class Solution {
    public int minMirrorPairDistance(int[] nums) {
        Map<Integer, Integer> lastSeen = new HashMap<>();
        int minDist = Integer.MAX_VALUE;

        for (int j = 0; j < nums.length; j++) {
            if (lastSeen.containsKey(nums[j])) {
                minDist = Math.min(minDist, j - lastSeen.get(nums[j]));
            }
            
            int rev = reverse(nums[j]);
            lastSeen.put(rev, j);
        }

        return minDist == Integer.MAX_VALUE ? -1 : minDist;
    }

    private int reverse(int x) {
        if (x == 0) {
            return 0;
        }
        int rev = 0;
        while (x > 0) {
            rev = rev * 10 + (x % 10);
            x /= 10;
        }
        return rev;
    }
}