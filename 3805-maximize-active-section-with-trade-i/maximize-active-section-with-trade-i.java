class Solution {
    public int maxActiveSectionsAfterTrade(String s) {
        int initialOnes = 0;
        int maxAdjacentSum = 0;
        int prevZ = 0;
        int zCount = 0;
        
        int n = s.length();
        int i = 0;
        
        while (i < n) {
            if (s.charAt(i) == '1') {
                initialOnes++;
                i++;
            } else {
                int count = 0;
                while (i < n && s.charAt(i) == '0') {
                    count++;
                    i++;
                }
                zCount++;
                if (zCount > 1) {
                    int sum = prevZ + count;
                    if (sum > maxAdjacentSum) {
                        maxAdjacentSum = sum;
                    }
                }
                prevZ = count;
            }
        }
        
        return initialOnes + maxAdjacentSum;
    }
}