class Solution {
    public int binaryGap(int n) {
        int maxDistance = 0;
        int lastOnePos = -1;
        int currentPos = 0;
        
        while (n > 0) {
            if ((n & 1) == 1) {
                if (lastOnePos != -1) {
                    maxDistance = Math.max(maxDistance, currentPos - lastOnePos);
                }
                lastOnePos = currentPos;
            }
            currentPos++;
            n >>= 1;
        }
        
        return maxDistance;
    }
}