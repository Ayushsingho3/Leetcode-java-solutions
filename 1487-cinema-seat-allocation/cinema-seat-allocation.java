class Solution {
    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {
        java.util.Map<Integer, Integer> map = new java.util.HashMap<>();
        for (int[] seat : reservedSeats) {
            map.put(seat[0], map.getOrDefault(seat[0], 0) | (1 << (seat[1] - 1)));
        }
        
        int maxGroups = (n - map.size()) * 2;
        
        for (int mask : map.values()) {
            boolean left = (mask & 30) == 0;
            boolean right = (mask & 480) == 0;
            boolean middle = (mask & 120) == 0;
            
            if (left && right) {
                maxGroups += 2;
            } else if (left || right || middle) {
                maxGroups += 1;
            }
        }
        
        return maxGroups;
    }
}