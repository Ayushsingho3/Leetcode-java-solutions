class Solution {
    public int countBinarySubstrings(String s) {
        int count = 0;
        int prevRunLength = 0;
        int currRunLength = 1;
        
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == s.charAt(i - 1)) {
                currRunLength++;
            } else {
                count += Math.min(prevRunLength, currRunLength);
                prevRunLength = currRunLength;
                currRunLength = 1;
            }
        }
        
        count += Math.min(prevRunLength, currRunLength);
        
        return count;
    }
}