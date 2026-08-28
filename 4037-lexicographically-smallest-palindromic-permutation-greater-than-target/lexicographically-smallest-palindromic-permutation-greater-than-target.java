class Solution {
    public String lexPalindromicPermutation(String s, String target) {
        int n = s.length();
        int[] freq = new int[26];
        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
        }

        int oddCount = 0;
        String midChar = "";
        int[] halfFreq = new int[26];

        for (int i = 0; i < 26; i++) {
            if (freq[i] % 2 != 0) {
                oddCount++;
                midChar = String.valueOf((char) (i + 'a'));
            }
            halfFreq[i] = freq[i] / 2;
        }

        if (oddCount > 1) {
            return "";
        }

        int m = n / 2;

        boolean canMatchHalf = true;
        int[] tempFreq = halfFreq.clone();
        StringBuilder hMatch = new StringBuilder();
        
        for (int i = 0; i < m; i++) {
            char c = target.charAt(i);
            if (tempFreq[c - 'a'] > 0) {
                tempFreq[c - 'a']--;
                hMatch.append(c);
            } else {
                canMatchHalf = false;
                break;
            }
        }

        if (canMatchHalf) {
            String pMatch = hMatch.toString() + midChar + new StringBuilder(hMatch.toString()).reverse().toString();
            if (pMatch.compareTo(target) > 0) {
                return pMatch;
            }
        }

        int max_i = hMatch.length(); 
        for (int i = Math.min(m - 1, max_i); i >= 0; i--) {
            int[] rem = halfFreq.clone();
            
            for (int j = 0; j < i; j++) {
                rem[target.charAt(j) - 'a']--;
            }

            char tChar = target.charAt(i);
            char bestC = 0;
            
            for (int c = tChar - 'a' + 1; c < 26; c++) {
                if (rem[c] > 0) {
                    bestC = (char) (c + 'a');
                    break;
                }
            }

            if (bestC != 0) {
                StringBuilder h = new StringBuilder();
                for (int j = 0; j < i; j++) {
                    h.append(target.charAt(j));
                }
                
                h.append(bestC);
                rem[bestC - 'a']--;

                for (int c = 0; c < 26; c++) {
                    while (rem[c] > 0) {
                        h.append((char) (c + 'a'));
                        rem[c]--;
                    }
                }

                return h.toString() + midChar + new StringBuilder(h.toString()).reverse().toString();
            }
        }

        return "";
    }
}