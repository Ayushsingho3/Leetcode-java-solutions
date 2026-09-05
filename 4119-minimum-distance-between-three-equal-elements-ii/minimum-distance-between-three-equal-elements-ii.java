import java.util.HashMap;
import java.util.Map;

class Solution {
    public int minimumDistance(int[] nums) {
        Map<Integer, int[]> map = new HashMap<>();
        int minDistance = Integer.MAX_VALUE;

        for (int i = 0; i < nums.length; i++) {
            int val = nums[i];
            if (!map.containsKey(val)) {
                map.put(val, new int[]{-1, i});
            } else {
                int[] pos = map.get(val);
                if (pos[0] != -1) {
                    minDistance = Math.min(minDistance, 2 * (i - pos[0]));
                }
                pos[0] = pos[1];
                pos[1] = i;
            }
        }

        return minDistance == Integer.MAX_VALUE ? -1 : minDistance;
    }
}