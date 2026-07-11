class Solution {
    public int longestBalanced(String s) {
        int n = s.length();
        int maxLen = 0;
        char[] sArr = s.toCharArray();

        for (int i = 0; i < n; i++) {
            if (n - i <= maxLen) break;
            
            int[] counts = new int[26];
            int distinct = 0;
            int maxF = 0;
            
            for (int j = i; j < n; j++) {
                int c = sArr[j] - 'a';
                if (counts[c] == 0) {
                    distinct++;
                }
                counts[c]++;
                
                if (counts[c] > maxF) {
                    maxF = counts[c];
                }
                
                if (maxF * distinct == j - i + 1) {
                    if (j - i + 1 > maxLen) {
                        maxLen = j - i + 1;
                    }
                }
            }
        }
        return maxLen;
    }
}