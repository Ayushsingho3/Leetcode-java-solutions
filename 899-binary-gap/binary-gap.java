class Solution {
    public int binaryGap(int n) {
        int maxDistance = 0;
        int prev = -1;
        int i = 0;
        
        while (n > 0) {
            if ((n & 1) == 1) {
                if (prev != -1) {
                    maxDistance = Math.max(maxDistance, i - prev);
                }
                prev = i;
            }
            n >>= 1;
            i++;
        }
        
        return maxDistance;
    }
}