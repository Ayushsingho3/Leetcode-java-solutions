class Solution {
    public String findTheString(int[][] lcp) {
        int n = lcp.length;
        char[] word = new char[n];
        char currentChar = 'a';
        
        for (int i = 0; i < n; i++) {
            if (word[i] == 0) {
                if (currentChar > 'z') {
                    return "";
                }
                for (int j = i; j < n; j++) {
                    if (lcp[i][j] > 0) {
                        if (word[j] == 0) {
                            word[j] = currentChar;
                        }
                    }
                }
                currentChar++;
            }
        }
        
        for (int i = n - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                int expected = 0;
                
                if (word[i] == word[j]) {
                    expected = 1 + ((i + 1 < n && j + 1 < n) ? lcp[i + 1][j + 1] : 0);
                }
                
                if (lcp[i][j] != expected) {
                    return "";
                }
            }
        }
        
        return new String(word);
    }
}