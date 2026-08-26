class Solution {
    public int minimumDistance(int[] nums) {
        java.util.Map<Integer, int[]> map = new java.util.HashMap<>();
        int minDistance = Integer.MAX_VALUE;
        
        for (int i = 0; i < nums.length; i++) {
            int v = nums[i];
            if (!map.containsKey(v)) {
                map.put(v, new int[]{-1, i});
            } else {
                int[] indices = map.get(v);
                if (indices[0] != -1) {
                    int dist = 2 * (i - indices[0]);
                    if (dist < minDistance) {
                        minDistance = dist;
                    }
                }
                indices[0] = indices[1];
                indices[1] = i;
            }
        }
        
        return minDistance == Integer.MAX_VALUE ? -1 : minDistance;
    }
}