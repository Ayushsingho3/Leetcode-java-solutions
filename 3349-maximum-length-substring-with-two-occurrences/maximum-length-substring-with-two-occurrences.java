class Solution {
    public int maximumLengthSubstring(String s) {
        int[] count = new int[128];
        int left = 0;
        int maxLen = 0;
        
        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);
            count[c]++;
            
            while (count[c] > 2) {
                count[s.charAt(left)]--;
                left++;
            }
            
            maxLen = Math.max(maxLen, right - left + 1);
        }
        
        return maxLen;
    }
}