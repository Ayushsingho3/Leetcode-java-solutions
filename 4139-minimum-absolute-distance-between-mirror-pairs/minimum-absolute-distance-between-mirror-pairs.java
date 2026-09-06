import java.util.HashMap;
import java.util.Map;

class Solution {
    public int minMirrorPairDistance(int[] nums) {
        Map<Long, Integer> map = new HashMap<>();
        int minDist = Integer.MAX_VALUE;

        for (int j = 0; j < nums.length; j++) {
            if (map.containsKey((long) nums[j])) {
                minDist = Math.min(minDist, j - map.get((long) nums[j]));
            }
            map.put(reverse(nums[j]), j);
        }

        return minDist == Integer.MAX_VALUE ? -1 : minDist;
    }

    private long reverse(int n) {
        long rev = 0;
        while (n > 0) {
            rev = rev * 10 + n % 10;
            n /= 10;
        }
        return rev;
    }
}