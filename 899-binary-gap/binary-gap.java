class Solution {
    public int binaryGap(int n) {
        int maxDist = 0;
        int lastPos = -1;
        int currentPos = 0;
        
        while (n > 0) {
            if ((n & 1) == 1) {
                if (lastPos != -1) {
                    maxDist = Math.max(maxDist, currentPos - lastPos);
                }
                lastPos = currentPos;
            }
            n >>= 1;
            currentPos++;
        }
        
        return maxDist;
    }
}