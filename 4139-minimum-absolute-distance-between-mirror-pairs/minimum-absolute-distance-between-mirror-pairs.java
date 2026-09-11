import java.util.HashMap;
import java.util.Map;

class Solution {
    public int minMirrorPairDistance(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        int minDistance = Integer.MAX_VALUE;

        for (int j = 0; j < nums.length; j++) {
            if (map.containsKey(nums[j])) {
                minDistance = Math.min(minDistance, j - map.get(nums[j]));
            }
            int rev = reverse(nums[j]);
            map.put(rev, j);
        }

        return minDistance == Integer.MAX_VALUE ? -1 : minDistance;
    }

    private int reverse(int n) {
        int rev = 0;
        while (n > 0) {
            rev = rev * 10 + n % 10;
            n /= 10;
        }
        return rev;
    }
}